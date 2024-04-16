package com.example.atelier2_json_volley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    StringRequest mRequest;

    JsonObjectRequest jsonRequest;

    JSONArray jsonArray;
    ListView listView;

    TextView nom;
    TextView prix;

    Vector<HashMap<String, Object>> vecteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "https://api.jsonbin.io/v3/b/637056232b3499323bfe110a?meta=false";
        //meta false:

        //****************VERSION 1:

//        mRequestQueue = Volley.newRequestQueue(this);
//        //3eme param: listener de maniere anonyme de reponse, et de erreur
//        mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("erreur", error.toString());
//            }
//        });
//
//        //!!! IMPORTANT
//        mRequestQueue.add(mRequest);


        //************ VERSION 2 - LISTE COMPLEXE
//        listView = findViewById(R.id.listView);
//        vecteur = new Vector<>();
//
//
//        mRequestQueue = Volley.newRequestQueue(this);
//        //!! attn json a pas besoin de http.
//        jsonRequest = new JsonObjectRequest(url, new Response.Listener<JSONObject>() {
//            @Override
//            public void onResponse(JSONObject response) {
//                try {
//                    jsonArray = response.getJSONArray("articles");
//                    for(int i=0; i<jsonArray.length(); i++){
//
//                        HashMap<String,Object> map1 = new HashMap<>();
//                        map1.put("nom", jsonArray.getJSONObject(i).getString("nom"));
//                        map1.put("prix", jsonArray.getJSONObject(i).getString("prix"));
//                        vecteur.add(map1);
//
//
//                    }
//                    String[] from = {"nom", "prix"};
//                    int[] to = {R.id.nom, R.id.prix};
//
//                    SimpleAdapter sa = new SimpleAdapter(MainActivity.this, vecteur, R.layout.list, from, to);
//                    listView.setAdapter(sa);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.i("erreur", error.toString());
//
//            }});
//
//        //1ere chose executé !!!
//        mRequestQueue.add(jsonRequest);

        //************VERSION 3 GSON

        mRequestQueue = Volley.newRequestQueue(this);
        mRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {
                Gson gson = new GsonBuilder().create();
                ListeProduits lp = gson.fromJson(response, ListeProduits.class);
                List<Produit> liste = lp.getArticles();
                Vector<Produit> v = new Vector<>(liste);
                for (Produit p : v) {
                    System.out.println(p.getNom() + p.getPrix());
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("erreur", error.toString());

            }
        });

        mRequestQueue.add(mRequest);


    }
}