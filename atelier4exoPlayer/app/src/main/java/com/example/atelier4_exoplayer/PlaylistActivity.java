package com.example.atelier4_exoplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.common.MediaItem;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class PlaylistActivity extends AppCompatActivity implements JSONObserver {

    Vector<HashMap<String, Object>> vecteur;
    Modele modele;
    List<Chanson> liste;

    ListView playlist;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        playlist = findViewById(R.id.playlist);

        vecteur = new Vector<>();

        intent = new Intent(PlaylistActivity.this, MainActivity.class);

        modele = Modele.getInstance(this, this);
        modele.openJSON();

    }

    @Override
    public void changement() {

        liste = modele.getListeChansons().getMusic();

        for (int i = 0; i < liste.size(); i++) {
            HashMap<String, Object> tempHash = new HashMap<>();
            tempHash.put("auteurChanson", liste.get(i).getArtist());
            tempHash.put("titreChanson", liste.get(i).getTitle());
            tempHash.put("imageChanson", liste.get(i).getImage());
            vecteur.add(tempHash);
        }

        String[] from = {"auteurChanson", "titreChanson", "imageChanson"};

        int[] to = {R.id.auteurChanson, R.id.titreChanson, R.id.imageChanson};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, vecteur, R.layout.chanson_playlist, from, to) {

            @Override
            public void setViewImage(ImageView v, String value) {
                Glide.with(PlaylistActivity.this).load(value).into(v);
            }
        };

        playlist.setAdapter(simpleAdapter);

        playlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                intent.putExtra("chanson", position);
                PlaylistActivity.this.startActivity(intent);
            }

        });

    }
}