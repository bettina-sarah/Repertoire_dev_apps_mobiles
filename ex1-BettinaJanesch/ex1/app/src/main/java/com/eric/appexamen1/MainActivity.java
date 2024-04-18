package com.eric.appexamen1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Vector;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ListView listeVisuelle;
    TextView texteCommentaires;

    ImageView groupeSelect;

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

    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            texteCommentaires.setText(liste[position].getCommentaires());
            groupeSelect.setImageResource(liste[position].getAdresseImage());
        }
    }


}