package com.example.atelier4_exoplayer;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class Modele {

    private ListeChansons listeChansons;

    private RequestQueue requestQueue;

    private StringRequest request;

    private String jsonUrl;

    public Modele(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
        this.jsonUrl = "https://api.jsonbin.io/v3/b/661ab8b1acd3cb34a837f284?meta=false";
    }

    public void openJSON(){
        this.listeChansons = new ListeChansons();
       // listeChansons.getMusic().add(int i, Chanson e); dans une boucle !
        this.request = new StringRequest(Request.Method.GET, jsonUrl,
                response -> {
                    Gson gson = new GsonBuilder().create();
                    this.listeChansons = gson.fromJson(response, ListeChansons.class);
                    List<Chanson> music = this.listeChansons.getMusic();

        }, error -> { Log.i("******* ERREUR ********", error.toString());} );

        this.requestQueue.add(this.request);
    }

    public ListeChansons getListeChansons() {
        return listeChansons;
    }
}
