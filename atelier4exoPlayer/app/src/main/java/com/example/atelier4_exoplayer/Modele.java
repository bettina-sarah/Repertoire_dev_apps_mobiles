package com.example.atelier4_exoplayer;

import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

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

    private JSONObserver jsonObserver;

    public static Modele getInstance(AppCompatActivity context, JSONObserver obs) {
        if (instance == null)
            instance = new Modele(context, obs);
        return instance;
    }

    public void avertitObservateurs(JSONObserver observer) {
        observer.changement();
    }

    private Modele(Context context, JSONObserver observer) {
        this.requestQueue = Volley.newRequestQueue(context);
        this.jsonUrl = "https://api.npoint.io/d4c29479e010376e6847";
        this.jsonObserver = observer;
    }

    public void openJSON(){
        this.listeChansons = new ListeChansons();

        this.request = new StringRequest(Request.Method.GET, jsonUrl,
                response -> {
                    Gson gson = new GsonBuilder().create();
                    this.listeChansons = gson.fromJson(response, ListeChansons.class);
                    //List<Chanson> music = this.listeChansons.getMusic();
                    avertitObservateurs(this.jsonObserver);

        }, error -> { Log.i("******* ERREUR ********", error.toString());} );

        this.requestQueue.add(this.request);
    }

    public ListeChansons getListeChansons() {
        return listeChansons;
    }
}
