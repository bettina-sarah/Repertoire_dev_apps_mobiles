package com.eric.labonte.appsaveinstancestate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AccueilActivity extends AppCompatActivity {

    TextView texteSalutations;
    Button boutonStartActivityForResult;

    ActivityResultLauncher<Intent> lanceur; //au lieu de creéer des intent
    //DECLArer utilisateur
    Utilisateur util;

    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texteSalutations = findViewById(R.id.texteSalutations);
        boutonStartActivityForResult = findViewById(R.id.boutonStartActivityForResult);
        ec = new Ecouteur();
        boutonStartActivityForResult.setOnClickListener(ec);

        // création du lanceur de boomerang, objet sera appelé au retour du boomerang dans cette classe
        //2eme param:	Un objet d'une classe mettant en œuvre l'interface ActivityResultCallback<ActivityResult>
        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallBackNom());
        //methode qui se rapelle elle meme: implements ActivityResultCallback

//        try{
//            util = (Utilisateur) savedInstanceState.getSerializable("util");
//            if (util != null){
//                String nouveau = "Bonjour," + util.getNom() + " " + util.getPrenom();
//
//                texteSalutations.setText(nouveau);
//            }
//        }
//        catch (NullPointerException e) {
//            e.printStackTrace();
//            texteSalutations.setText("Bonjour!");
//        }



    }

    //deactivé a cause de manifest.xml

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) { //appelé juste avant qu'on quitte lappli (qu'on tourne!)
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("util", util);
//    }

    private class CallBackNom implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            //lorsqu'on a le nom & prenom choisi, c'est la qu'on arrive lorsque le boomerang revient
            //3:
            if(result.getResultCode() == RESULT_OK){
                //getData 1x retourne intent, 2eme retourne uri
                Intent intentRetour = result.getData();
                util = (Utilisateur) intentRetour.getSerializableExtra("util");

                String nouveau = "Bonjour," + util.getNom() + " " + util.getPrenom();

                texteSalutations.setText(nouveau);

                // !! on a pas fait de serialisation; on a juste utilisé le fait d'etre serialisable pour transferer entre activités
            }


        }
    }

    //startActivityForResult: demarrer activité et ramener results
    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            //1. launch
            lanceur.launch(new Intent(AccueilActivity.this,IdentificationActivity.class));
        }
    }

}