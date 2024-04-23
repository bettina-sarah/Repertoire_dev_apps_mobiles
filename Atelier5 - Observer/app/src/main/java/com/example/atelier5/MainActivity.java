package com.example.atelier5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ChangementObservable {


    Modele m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        m = new Modele(this);


    }

    @Override
    public void changement(int valeur) {
        System.out.println("la nouvelle valeur : " + valeur);
        System.out.println(m.getValeur()+1);
    }
}