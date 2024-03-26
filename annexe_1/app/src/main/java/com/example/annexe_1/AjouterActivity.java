package com.example.annexe_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class AjouterActivity extends AppCompatActivity {
    Button ajouter;
    EditText champ;

    Ecouteur ec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        ajouter = findViewById(R.id.memoAjouter);
        champ = findViewById(R.id.memoText);

        Ecouteur ec = new Ecouteur();
        ajouter.setOnClickListener(ec);

    }

    private class Ecouteur implements View.OnClickListener{

        @Override
        public void onClick(View source) {

            String memo = champ.getText().toString();
            //mode:facon d'ecriture
            //averti pcq une erreur controlé
            //try avec ressources: try() pi le vrai try commence apres {}
            try (
                FileOutputStream fos = openFileOutput("memos.txt", MODE_APPEND);
                //1er flux dans 2eme flux: de STREAM a WRITER, donc de binaire a CHAR
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                //création buffer avec le flux char
                BufferedWriter bw = new BufferedWriter(osw))

            {
                bw.write(memo);
                bw.newLine(); //changer ligne
                finish(); //revient a activité de base
                // bw.close();
            }

            catch (FileNotFoundException e) {
                //si on sai pas quoi faire avec l'erreur, au moins print l'erreur
                e.printStackTrace();
            }

            catch (IOException e) {
                e.printStackTrace();
            }


        }
    }
}