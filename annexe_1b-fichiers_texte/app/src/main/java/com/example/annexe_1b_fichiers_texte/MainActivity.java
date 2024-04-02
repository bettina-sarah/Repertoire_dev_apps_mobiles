package com.example.annexe_1b_fichiers_texte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    TextView reponse;
    Button boutonLignes;
    Button boutonChars;
    Button boutonCs;
    Button boutonEcrireNom;
    Button nombreMotsScanner;
    EditText champsNom;
    Ecouteur ec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        System.out.println("nb lignes" +  getNbLignes());
//        System.out.println("nb chars" + getNbChars());
//        System.out.println("nb de c" + getNbLettreC());
//        ecrireNom("bettina");
//        System.out.println(getNbLignes());

        reponse = findViewById(R.id.reponse);
        boutonLignes = findViewById(R.id.nbrLignes);
        boutonChars = findViewById(R.id.nbrChars);
        boutonCs = findViewById(R.id.nbrC);
        boutonEcrireNom = findViewById(R.id.ecrireNom);
        champsNom = findViewById(R.id.champsNom);
        nombreMotsScanner = findViewById(R.id.motsScanner);

        ec = new Ecouteur();
        boutonLignes.setOnClickListener(ec);
        boutonChars.setOnClickListener(ec);
        boutonCs.setOnClickListener(ec);
        boutonEcrireNom.setOnClickListener(ec);
        nombreMotsScanner.setOnClickListener(ec);

    }
    private class Ecouteur implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if(v==boutonLignes){
                reponse.setText(String.valueOf(getNbLignes()));
            }
            else if(v==boutonChars){
                reponse.setText(String.valueOf(getNbChars()));
            }
            else if(v==boutonCs){
                reponse.setText(String.valueOf(getNbLettreC()));
            }
            else if(v==boutonEcrireNom){
                ecrireNom(champsNom.getText().toString());
            }
            else if(v==nombreMotsScanner){
                int mots =0;
                try {
                    FileInputStream fis = openFileInput("memos.txt");
                    InputStreamReader isr = new InputStreamReader(fis);
                    BufferedReader br = new BufferedReader(isr);
                    //!! ATTN: br.close(); si pas try with ressource !!

                    Scanner sc = new Scanner(openFileInput("memos.txt"));
                    while (sc.hasNext()) {
                        sc.next();
                        mots++;
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                reponse.setText(String.valueOf(mots));
            }




            }

        }


    //Une méthode retournant le nombre de lignes que compte votre fichier texte
    public int getNbLignes(){
        int lignes = 0;
        try {
            FileInputStream fos = openFileInput("memos.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);

            while(br.ready()){  //ou br.readline() !=null
                br.readLine();
                lignes++;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lignes;
    }

    //Une méthode retournant le nombre de caractères que compte votre fichier texte
    public int getNbChars(){
        int chars = 0;
        try{
            FileInputStream fos = openFileInput("memos.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            while(br.ready()){
                br.read();
                chars++; //chars
//                String line = br.readLine();
//                chars += line.length();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return chars;
    }

    // Une méthode retournant le nombre de « c » que comprend votre fichier texte
    public int getNbLettreC(){
        int c = 0;
        try{
            FileInputStream fis = openFileInput("memos.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            while(br.ready()){
                if(br.read() == 'c'){
                    c++;
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return c;

    }

    // Une méthode permettant d’écrire votre nom sur une ligne située à la fin du fichier texte
    public void ecrireNom(String nom){
        try (
                FileOutputStream fos = openFileOutput("memos.txt", MODE_APPEND);
                //1er flux dans 2eme flux: de STREAM a WRITER, donc de binaire a CHAR
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                //création buffer avec le flux char
                BufferedWriter bw = new BufferedWriter(osw))

        {
            bw.write(nom);
            bw.newLine();
        }

     catch (FileNotFoundException e) {
        e.printStackTrace();
    }
        catch (IOException e) {
        e.printStackTrace();
    }

    }
}