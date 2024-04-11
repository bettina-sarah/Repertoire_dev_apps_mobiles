package com.example.annexe5_listecomplexe;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    TextView titre;

    Ecouteur ec;
    Vector<HashMap<String,Object>> vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HashMap<String,Object> map1 = new HashMap<>();
        map1.put("numero", 34);
        map1.put("titre", "Touch Me");
        map1.put("date", "22/03/86");
        map1.put("imageView", R.drawable.touchme);

        HashMap<String,Object> map2 = new HashMap<>();
        map2.put("numero", 8);
        map2.put("titre", "Nothings gonna stop me now");
        map2.put("date", "22/03/86");
        map2.put("imageView", R.drawable.nothing);

        HashMap<String,Object> map3 = new HashMap<>();
        map3.put("numero", 3);
        map3.put("titre", "Santa Maria");
        map3.put("date", "28/03/1998");
        map3.put("imageView", R.drawable.santamaria);

        HashMap<String,Object> map4 = new HashMap<>();
        map4.put("numero", 108);
        map4.put("titre", "Hot Boy");
        map4.put("date", "10/04/2018");
        map4.put("imageView", R.drawable.hotboy);

        vh = new Vector<>();

        vh.add(map1);
        vh.add(map2);
        vh.add(map3);
        vh.add(map4);

        //resource:R.layout.list_item_layout is the layout resource ID for each item in the ListView,- XML !!


        //from: String: A list of column names that will be added to the Map associated with each item.
        String[] from = {"numero", "titre", "date", "imageView"};
        //alternatif:
        //String[]{"numero", "titre", "date", "imageView"};

        //to:int: The views that should display column in the "from" parameter. These should all be TextViews.
        // The first N views in this list are given the values of the first N columns in the from parameter.
        int[] to = {R.id.numero, R.id.titre, R.id.date, R.id.imageView};

        SimpleAdapter sa = new SimpleAdapter(this, vh, R.layout.new_layout, from, to);

        listView = findViewById(R.id.listView);

        listView.setAdapter(sa);

        //gestion devenement
        ec = new Ecouteur();
        listView.setOnItemClickListener(ec);

    }

    private class Ecouteur implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            LinearLayout itemChoisi = (LinearLayout) view;
            TextView nomchanson = itemChoisi.findViewById(R.id.titre);
           // Toast.makeText(MainActivity.this, nomchanson.getText().toString(), Toast.LENGTH_SHORT).show();


            Toast.makeText(MainActivity.this, vh.get(position).get("titre").toString(),
                    Toast.LENGTH_SHORT).show();

        }
    }




}