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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ExoPlayer player;
    PlayerView vue;

    ImageView image;
    TextView titre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vue = findViewById(R.id.vue);

        player = new ExoPlayer.Builder(this).build();
        //permet la musique de continuer quand l'app est en background
        vue.setUseController(false);
        Modele modele = new Modele(this);
        modele.openJSON();

        List<Chanson> liste = modele.getListeChansons().getMusic();
        titre = findViewById(R.id.titre);
        image = findViewById(R.id.imageChanson);
        titre.setText(liste.get(2).getTitle());
        //image.setImageURI(liste.get(2).getImage());



        //PlayerControlView:
//        This component encapsulates a PlayerControlView for playback controls, SubtitleView for displaying
//        subtitles, and Surface for rendering video.

//        play() and pause() to start and pause playback
//        seekTo() to seek to a position within the current media item
//        seekToNextMediaItem() and seekToPreviousMediaItem() to navigate through the playlist


    }

    @Override
    protected void onStart() {
        super.onStart();
        vue.setPlayer(player);
        String mp3url = "https://storage.googleapis.com/uamp/The_Kyoto_Connection_-_Wake_Up/01_-_Intro_-_The_Way_Of_Waking_Up_feat_Alan_Watts.mp3";
        //media item de media3
        MediaItem mediaItem = MediaItem.fromUri(mp3url);
        player.setMediaItem(mediaItem); //lier chanson au player
        player.prepare();
        player.play();
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