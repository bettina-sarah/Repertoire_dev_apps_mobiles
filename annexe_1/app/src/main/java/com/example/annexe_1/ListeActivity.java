package com.example.annexe_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Vector;

public class ListeActivity extends AppCompatActivity {

    ListView liste;

    Vector<String> listeMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        liste = findViewById(R.id.listeMemos);

        ArrayAdapter a = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, recupererMemos());
        liste.setAdapter(a);

    }

    public ArrayList<String>recupererMemos(){
        ArrayList<String> listeArrayMemo = new ArrayList<>();

        try (
                //ATTN: pas new OpenFileInput !!!
        FileInputStream fos = openFileInput("memos.txt");
        InputStreamReader isr = new InputStreamReader(fos);
        BufferedReader br = new BufferedReader(isr))
        {
            //bw.ready: return true si prochaine ligne est bonne a lire
            //br.read() un char a la fois - unicode - pt transtyper en char ?
            //while br.readline() !=null ..
            while(br.ready()){ //tant qu'il y a des donnees...
                listeArrayMemo.add(br.readLine());
            }
        }

        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        catch (IOException e) {
            e.printStackTrace();
        }

        return listeArrayMemo;


    }
}