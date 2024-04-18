package com.eric.appexamen1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ListView listeVisuelle;
    TextView texteCommentaires;

    ImageView groupeSelect;

    String serialiserGroupeString;
    int serialiserGroupeImage;

    Groupe[] liste = {new Groupe ("c23", R.drawable.c23, "Excellent groupe"),new Groupe("c34", R.drawable.c34, "superbe cohorte"),new Groupe("c44", R.drawable.c44, "groupe travaillant")  };
    Vector<HashMap<String,Object>> vecteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //créer hashmaps correspondant aux groupes & populer vecteur

        imageView = findViewById(R.id.groupeSelect);
        listeVisuelle = findViewById(R.id.liste);
        texteCommentaires = findViewById(R.id.texteCommentaires);

        groupeSelect = findViewById(R.id.groupeSelect);

        HashMap<String,Object> hash1 = new HashMap<>();
        hash1.put("texteGroupe", liste[0].getNomCours());
        hash1.put("imageGroupe", liste[0].getAdresseImage());

        HashMap<String,Object> hash2 = new HashMap<>();
        hash2.put("texteGroupe", liste[1].getNomCours());
        hash2.put("imageGroupe", liste[1].getAdresseImage());

        HashMap<String,Object> hash3 = new HashMap<>();
        hash3.put("texteGroupe", liste[2].getNomCours());
        hash3.put("imageGroupe", liste[2].getAdresseImage());

        vecteur = new Vector<>();

        vecteur.add(hash1);
        vecteur.add(hash2);
        vecteur.add(hash3);

        //créer simple adapter pour la liste complexe

        String[] from = {"texteGroupe", "imageGroupe"};

        int[] to = {R.id.texteGroupe, R.id.imageGroupe};

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, vecteur, R.layout.item_liste, from, to);

        listeVisuelle.setAdapter(simpleAdapter);


        Ecouteur ec = new Ecouteur();
        listeVisuelle.setOnItemClickListener(ec);
    }

    @Override
    protected void onStart() {
        super.onStart();
        deserialiserInfoGroupe();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            serialiserInfoGroupe(serialiserGroupeString, serialiserGroupeImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //on veut se rappeler globalement pour on stop !
            serialiserGroupeString = liste[position].getCommentaires();
            serialiserGroupeImage = (liste[position].getAdresseImage());

            texteCommentaires.setText(serialiserGroupeString);
            groupeSelect.setImageResource(serialiserGroupeImage);


        }
    }

    public void serialiserInfoGroupe(String commentaire, int image) throws IOException {
        try (FileOutputStream fos = MainActivity.this.openFileOutput("groupeInfo.ser", Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(commentaire);
            oos.writeObject(image);
        }
    }

    public void deserialiserInfoGroupe() {
        try (FileInputStream fis = MainActivity.this.openFileInput("groupeInfo.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            String commentaire = (String) ois.readObject();
            int image = (int) ois.readObject();
            // remettre les memes infos
            texteCommentaires.setText(commentaire);
            groupeSelect.setImageResource(image);

    } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}