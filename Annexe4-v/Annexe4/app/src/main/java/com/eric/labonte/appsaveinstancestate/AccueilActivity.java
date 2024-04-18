package com.eric.labonte.appsaveinstancestate;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

public class AccueilActivity extends AppCompatActivity {
    TextView texte;
    Button bouton;

    Ecouteur ec;
    Utilisateur u;

    ActivityResultLauncher<Intent> lanceur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ec = new Ecouteur();

        texte = findViewById(R.id.texteSalutations);
        bouton = findViewById(R.id.boutonStartActivityForResult);
        bouton.setOnClickListener(ec);
        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallBackNom());
//        try {
//            u = (Utilisateur) savedInstanceState.getSerializable("nomComplet");
//            if (u != null) {
//                texte.setText("Bonjour " + u.getPrenom() + " " + u.getNom());
//            }
//        }
//        catch (NullPointerException npe){
//            npe.printStackTrace();
//            texte.setText("Bonjour!");
//        }
    }

//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("nomComplet",u);
//
//    }

    private class CallBackNom implements ActivityResultCallback<ActivityResult> {

        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK){
                u = (Utilisateur) result.getData().getSerializableExtra("nomComplet");
                texte.setText("Bonjour " + u.getPrenom() + " "+ u.getNom());
            }

        }
    }



    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if (source == bouton){
                lanceur.launch(new Intent (AccueilActivity.this, IdentificationActivity.class) );

            }
        }
    }

    public void rechercherFichiers() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("audio/*");
        lanceur.launch(intent);
    }
}