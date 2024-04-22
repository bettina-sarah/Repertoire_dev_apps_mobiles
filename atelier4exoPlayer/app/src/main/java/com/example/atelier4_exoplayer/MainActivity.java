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
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExoPlayer player;
    PlayerView vue;

    TextView titre;

    ImageButton play;
    ImageButton next;
    ImageButton previous;

    SeekBar seekBar;

    Ecouteur ec;

    boolean playerOn;

    List<Chanson> liste;
    Modele modele;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vue = findViewById(R.id.vue);
        titre = findViewById(R.id.titre);
        play = findViewById(R.id.play);
        next = findViewById(R.id.next);
        previous = findViewById(R.id.previous);
        ec = new Ecouteur();

        seekBar = findViewById(R.id.seekBar);

        //permet la musique de continuer quand l'app est en background

        modele = Modele.getInstance(this);
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

    }

    public class Ecouteur implements View.OnClickListener{
        @Override
        public void onClick(View source) {
            if(source==play){
                if(!playerOn){
                    preparePlayer();
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
            }
            else if (source==previous){
                player.seekToPreviousMediaItem();
            }

        }
    }

    private void preparePlayer() {
        liste = modele.getListeChansons().getMusic();

        for(int i=0; i<liste.size(); i++)

        String mp3url = liste.get(0).getSource();
        titre.setText(liste.get(0).getTitle());
        MediaItem mediaItem = MediaItem.fromUri(mp3url);

        player.addMediaItem(mediaItem); //lier chanson au player
        player.prepare();

        // faire boucle et add media item !!!

    }
    private void play() {
        player.play();
    }


    @Override
    protected void onStart() {
        super.onStart();
        player = new ExoPlayer.Builder(this).build();
        vue.setPlayer(player);
        vue.setUseController(false);
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.release();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //garder en memoire ou on est rendu
    }
}