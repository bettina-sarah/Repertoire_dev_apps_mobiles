package com.eric.labonte.appsaveinstancestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IdentificationActivity extends AppCompatActivity {

    EditText champNom, champPrenom;
    Button boutonConfirmer;

    Utilisateur u;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        champNom = findViewById(R.id.champNom);
        champPrenom = findViewById(R.id.champPrenom);

        boutonConfirmer = findViewById(R.id.boutonConfirmer);
        Ecouteur ec = new Ecouteur();
        boutonConfirmer.setOnClickListener(ec);


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View source) {
            //2.
            u = new Utilisateur(champNom.getText().toString(), champPrenom.getText().toString());
            Intent i = new Intent();
            i.putExtra("util", u); // objet utilisateur serializable - envoyer extra qui est serialisable.
            setResult(RESULT_OK, i);
            finish(); // sentiment de revenir dans accueil activity


        }
    }
}