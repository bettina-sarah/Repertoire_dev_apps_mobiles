package com.example.annexe_7_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    RequestQueue mRequestQueue;
    StringRequest mRequest;

    JsonObjectRequest jsonRequestA;
    JsonObjectRequest jsonRequestB;

    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);

        String requestUrl = "https://api.jsonbin.io/v3/b/6374dac065b57a31e6b93755?meta=false";

//        A)	Affichez le contenu du header du menu ( rép : SVG Viewer )


        jsonRequestA = new JsonObjectRequest(Request.Method.GET,requestUrl,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject s = response.getJSONObject("menu");
                    String neww = s.getString("header");
                    System.out.println("*****A. svg viewer: ************\n\n"+neww);
//                    jsonArray = response.getJSONArray("articles");
//                    for(int i=0; i<jsonArray.length(); i++){

                    }
                catch (JSONException e) {
                    e.printStackTrace();
                }
        }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("erreur", error.toString());

            }});

        //        B)	Affichez le nombre d’éléments du tableau Items ( rép. : 22 )
        //        C)	Afficher le nombre d’éléments du tableau Items n’ayant pas d’attributs ( seulement null ) ( rép. : 4 )
        //        D)	Afficher le nombre d’éléments du tableau Items n’ayant pas d’attribut «label »

        jsonRequestB = new JsonObjectRequest(Request.Method.GET,requestUrl,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    int compteur = 0;
                    int compteur2 = 0;
                    JSONObject s = response.getJSONObject("menu");
                    JSONArray tab = s.getJSONArray("items");

                    System.out.println("******B. longueur items *********\n"+tab.length());
                    for (int i=0; i<tab.length(); i++){
                        Object o = tab.get(i);
                        if(!(o instanceof JSONObject)){
                            compteur++;
                        }
                        else{ // pas null:
                            try{
                                ((JSONObject) o).getString("label");


                            }
                            catch(Exception e){
                                compteur2++;
                                e.printStackTrace();
                            }
                        }
                    }
                    System.out.println("******C. instances de null*********\n"+compteur);
                    System.out.println("******D. instances de label*********\n"+compteur2);



                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }}, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("erreur", error.toString());

            }});





        mRequestQueue.add(jsonRequestA);
        mRequestQueue.add(jsonRequestB);

    }
}