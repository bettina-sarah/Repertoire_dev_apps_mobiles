package com.example.annexe_1;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SingletonMemo {

    private static SingletonMemo instance;
    private ArrayList<Memo> listeMemos;
    private Context context;


    private SingletonMemo(Context context) {
        listeMemos = new ArrayList<>();
        this.context = context;

    }

    public static SingletonMemo getInstance(Context context){
        if(instance==null){
            instance = new SingletonMemo(context);
        }
        return instance;
    }

    public ArrayList<Memo> getListeMemos() {
        return listeMemos;
    }

    public void ajouterMemo(Memo memo){
        this.listeMemos.add(memo);
    }

    public void setListeMemos(ArrayList<Memo> listeMemos) {
        this.listeMemos = listeMemos;
    }

    //sauver dans signleton,
    public void serialiserListe() throws Exception {

        try(FileOutputStream fos = context.openFileOutput("fichier.ser", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos)){

            oos.writeObject(listeMemos);

        }
        //flux de données. - ecrire dans le fichier

        //pas append; car  on veut ecrire par dessus l'ancienne liste.
    }
    public ArrayList<Memo> recupererListe() throws Exception {
        ArrayList<Memo> temp = null;

        try(FileInputStream fis = context.openFileInput("fichier.ser");
            ObjectInputStream ois = new ObjectInputStream(fis)){

            temp = ((ArrayList<Memo>)ois.readObject());
        }
        return temp;
    }




}
