package com.example.annexe_6_boomerang_activityresult;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button boutonImage;

    ImageView imageView;

    Ecouteur ec;

    ActivityResultLauncher<Intent> lanceur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boutonImage = findViewById(R.id.boutonImage);

        imageView = findViewById(R.id.imageView);


        ec = new Ecouteur();
        boutonImage.setOnClickListener(ec);

        // création du lanceur de boomerang, objet sera appelé au retour du boomerang dans cette classe
        lanceur = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new CallBackPictures());

    }

    private class CallBackPictures implements ActivityResultCallback<ActivityResult> {


        @Override
        public void onActivityResult(ActivityResult result) {

            if (result.getData() != null) {

                Uri uri = result.getData().getData();
                imageView.setImageURI(uri);
            }



        }
    }


    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Intent i = new Intent(Intent.ACTION_PICK);
            i.setType("image/*");
            lanceur.launch(i);

        }
    }



}