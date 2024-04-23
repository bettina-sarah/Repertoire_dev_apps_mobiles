package com.example.atelier4_exoplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import androidx.media3.ui.PlayerView;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements JSONObserver, Runnable {

    ExoPlayer player;
    PlayerView vue;

    TextView titre;

    ImageButton rewind;
    ImageButton play;
    ImageButton fastForward;
    ImageButton next;
    ImageButton previous;
    ImageButton shuffle;

    SeekBar seekBar;

    Ecouteur ec;

    boolean playerOn;

    List<Chanson> liste;
    Modele modele;

    int chansonCourant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vue = findViewById(R.id.vue);
        titre = findViewById(R.id.titre);
        rewind = findViewById(R.id.rewind);
        play = findViewById(R.id.play);
        fastForward = findViewById(R.id.fastForward);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        shuffle = findViewById(R.id.shuffle);
        ec = new Ecouteur();
        chansonCourant = 0;

        seekBar = findViewById(R.id.seekBar);
        player = new ExoPlayer.Builder(this).build();
        vue.setPlayer(player);
        vue.setUseController(false);

        //permet la musique de continuer quand l'app est en background

        modele = Modele.getInstance(this,this);
        modele.openJSON();
        play.setOnClickListener(ec);
        next.setOnClickListener(ec);
        previous.setOnClickListener(ec);
        playerOn = false;

        //PlayerControlView:
//        This component encapsulates a PlayerControlView for playback controls, SubtitleView for displaying
//        subtitles, and Surface for rendering video.

//        play() and pause() to start and pause playback
//        seekTo() to seek to a position within the current media item
//        seekToNextMediaItem() and seekToPreviousMediaItem() to navigate through the playlist

        //seekback seekforward
    }

    @Override
    public void changement() {
        preparePlayer();
    }

    //thread pour le seekBar
    @Override
    public void run() {
        //seekbar setprogress;
        // actions à faire
        Handler handler = new Handler();
        handler.postDelayed(MainActivity.this, 1000);
    }


    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {
            if(source==play){
                if(!playerOn){
                    play();
                    play.setImageResource(R.drawable.pause);
                    playerOn=true;
                }
                else{
                    player.pause();
                    play.setImageResource(R.drawable.play);
                    playerOn=false;
                }
            }
            else if (source==next){
                player.seekToNextMediaItem();
                // player.getMediaItemAt()
                String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                        liste.get(player.getCurrentMediaItemIndex()).getTitle();
                titre.setText(chanson);

            }
            else if (source==previous){
                player.seekToPreviousMediaItem();
                String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                        liste.get(player.getCurrentMediaItemIndex()).getTitle();
                titre.setText(chanson);
            }
            else if(source==rewind){
                player.seekTo(-15000);
            }
            else if(source==fastForward){
                player.seekTo(15000);
            }

            else if (source==shuffle){
                player.setShuffleModeEnabled(true);
                //marche pas - faire collections peut-etre
            }

        }
    }

    private void preparePlayer() {


        liste = modele.getListeChansons().getMusic();

        for(int i=0; i<liste.size(); i++){
            player.addMediaItem(MediaItem.fromUri(liste.get(i).getSource()));
        }

        String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                liste.get(player.getCurrentMediaItemIndex()).getTitle();
        titre.setText(chanson);
        player.prepare();


    }
    private void play() {
        player.play();
        String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                liste.get(player.getCurrentMediaItemIndex()).getTitle();
        titre.setText(chanson);
    }



    @Override
    protected void onStart() {
        super.onStart();
        player.seekTo(chansonCourant, 0);
        player.prepare();

    }

    @Override
    protected void onStop() {
        super.onStop();
        chansonCourant = player.getCurrentMediaItemIndex();
        player.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        chansonCourant = player.getCurrentMediaItemIndex();
        player.release();
    }

    @Override
    protected void onPause() {
        super.onPause();
        player.seekTo(chansonCourant, 0);
        player.prepare();
        //garder en memoire ou on est rendu
    }
    @Override
    protected void onResume() {
        super.onResume();
        player.seekTo(chansonCourant, 0);
        player.prepare();
    }
}