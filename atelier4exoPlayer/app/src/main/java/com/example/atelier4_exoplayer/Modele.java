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

    private static Modele instance;

    private ListeChansons listeChansons;

    private RequestQueue requestQueue;

    private StringRequest request;

    private String jsonUrl;

    public static Modele getInstance(Context context) {
        if (instance == null)
            instance = new Modele(context);
        return instance;
    }




    private Modele(Context context) {
        this.requestQueue = Volley.newRequestQueue(context);
        this.jsonUrl = "https://api.npoint.io/d4c29479e010376e6847";
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
