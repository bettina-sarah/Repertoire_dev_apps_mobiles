package com.example.atelier4_exoplayer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.session.MediaSession;
import androidx.media3.session.MediaSessionService;
import androidx.media3.ui.PlayerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements JSONObserver {

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
    Handler handler;
    SeekBarThread seekBarThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chansonCourant = 0;
        Intent intent = getIntent();
        chansonCourant = intent.getIntExtra("chanson", 0);

        vue = findViewById(R.id.vue);
        titre = findViewById(R.id.titre);
        rewind = findViewById(R.id.rewind);
        play = findViewById(R.id.play);
        fastForward = findViewById(R.id.fastForward);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        shuffle = findViewById(R.id.shuffle);
        ec = new Ecouteur();

        handler = new Handler();
        seekBarThread = new SeekBarThread();

        seekBar = findViewById(R.id.seekBar);
        player = new ExoPlayer.Builder(this).build();
        vue.setPlayer(player);
        vue.setUseController(false);

        //permet la musique de continuer quand l'app est en background

        modele = Modele.getInstance(this,this);
        //modele.openJSON();
        play.setOnClickListener(ec);
        next.setOnClickListener(ec);
        previous.setOnClickListener(ec);
        playerOn = false;
        preparePlayer();

    }

    @Override
    public void changement() {


    }


    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {
            if(source==play){
                if(!playerOn){
                    play();
                    play.setImageResource(R.drawable.pause);
                    playerOn=true;
                    handler.post(seekBarThread);
                }
                else{
                    player.pause();
                    play.setImageResource(R.drawable.play);
                    playerOn=false;
                    handler.removeCallbacks(seekBarThread);
                }
            }
            else if (source==next){
                player.seekToNextMediaItem();
                // player.getMediaItemAt()
                //reset le seekbar
                //player.getCurrentMediaItem()
                //handler.post(seekBarThread);
                String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                        liste.get(player.getCurrentMediaItemIndex()).getTitle();
                titre.setText(chanson);

            }
            else if (source==previous){
                player.seekToPreviousMediaItem();
                //handler.post(seekBarThread);
                String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                        liste.get(player.getCurrentMediaItemIndex()).getTitle();
                titre.setText(chanson);
            }
            else if(source==rewind){
                player.seekTo(-10000);
                handler.post(seekBarThread);
            }
            else if(source==fastForward){
                player.seekTo(10000);
                handler.post(seekBarThread);
            }

            else if (source==shuffle){
                player.setShuffleModeEnabled(true);
                //marche pas - faire collections peut-etre
            }

        }
    }

    private class SeekBarThread implements Runnable{
        public void run() {
           afficher();
        }
    }

    public void afficher() {

        seekBar.setMax(liste.get(player.getCurrentMediaItemIndex()).getDuration());
        seekBar.setProgress((int)player.getCurrentPosition()/1000);
        handler.postDelayed(seekBarThread, 1000);
    }



    private void preparePlayer() {


        liste = modele.getListeChansons().getMusic();

        for(int i=0; i<liste.size(); i++){
            player.addMediaItem(MediaItem.fromUri(liste.get(i).getSource()));
        }

        player.seekTo(chansonCourant);

        String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                liste.get(player.getCurrentMediaItemIndex()).getTitle();
        titre.setText(chanson);
        player.prepare();
        seekBar.setMax((int)player.getCurrentPosition()/1000);
        seekBar.setProgress(0);


    }
    private void play() {
        player.play();
        String chanson = liste.get(player.getCurrentMediaItemIndex()).getArtist() + " - " +
                liste.get(player.getCurrentMediaItemIndex()).getTitle();
        titre.setText(chanson);
        handler.post(seekBarThread);
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