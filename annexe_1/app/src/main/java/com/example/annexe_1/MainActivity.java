package com.example.annexe_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ajouter;
    Button afficher;
    Button quitter;

    Ecouteur ec;

    Intent i_ajouter;
    Intent i_afficher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ajouter = findViewById(R.id.ajouter);
        afficher = findViewById(R.id.afficher);
        quitter = findViewById(R.id.quitter);

        ec = new Ecouteur();

        ajouter.setOnClickListener(ec);
        afficher.setOnClickListener(ec);
        quitter.setOnClickListener(ec);

        i_ajouter = new Intent(MainActivity.this, AjouterActivity.class);
        i_afficher = new Intent(MainActivity.this, ListeActivity.class);


    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {

            if(source == ajouter){
                startActivity(i_ajouter);
            }
            else if(source == afficher){
                startActivity(i_afficher);
            }
            else{
                finish();
            }

        }
    }





}