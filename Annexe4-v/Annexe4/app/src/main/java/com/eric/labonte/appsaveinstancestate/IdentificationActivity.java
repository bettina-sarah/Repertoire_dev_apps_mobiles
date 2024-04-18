package com.eric.labonte.appsaveinstancestate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IdentificationActivity extends AppCompatActivity {

    EditText entrerPrenom;

    EditText entrerNom;

    Button boutonConfirm;

    Utilisateur user;

    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        ec = new Ecouteur();
        boutonConfirm = findViewById(R.id.boutonConfirmer);
        entrerPrenom = findViewById(R.id.champPrenom);
        entrerNom = findViewById(R.id.champNom);
        boutonConfirm.setOnClickListener(ec);
    }

    public class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {
            if (source == boutonConfirm){
                user = new Utilisateur(entrerNom.getText().toString(), entrerPrenom.getText().toString());
                Intent i = new Intent();
                i.putExtra("nomComplet", user);
                setResult(RESULT_OK, i);
                finish();
            }
        }
    }
}