package com.example.annexe_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AjouterActivity extends AppCompatActivity {
    Button ajouter;
    Button choisirDate;
    TextView texteDateChoisi;
    EditText champ;

    Ecouteur ec;

    DatePickerDialog datePickerDialog;
    LocalDate temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter);

        ajouter = findViewById(R.id.memoAjouter);
        champ = findViewById(R.id.memoText);

        choisirDate = findViewById(R.id.choisirDate);
        texteDateChoisi = findViewById(R.id.texteDateChoisi);

        ec = new Ecouteur();
        ajouter.setOnClickListener(ec);
        choisirDate.setOnClickListener(ec);

        datePickerDialog = new DatePickerDialog(this, ec,1,0,1);


        }

    @Override
    protected void onStop(){
        super.onStop();
        try{
            SingletonMemo.getInstance(getApplicationContext()).serialiserListe();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class Ecouteur implements View.OnClickListener,DatePickerDialog.OnDateSetListener{

        @Override
        public void onClick(View source) {
            if(source==ajouter){
                String memo = champ.getText().toString();
                SingletonMemo.getInstance(getApplicationContext()).ajouterMemo(new Memo(memo, temp));



                finish();
            }
            else if(source==choisirDate){
                datePickerDialog.show();
            }

//            String memo = champ.getText().toString();
//            //mode:facon d'ecriture
//            //averti pcq une erreur controlé
//            //try avec ressources: try() pi le vrai try commence apres {}
//            try (
//                FileOutputStream fos = openFileOutput("memos.txt", MODE_APPEND);
//                //1er flux dans 2eme flux: de STREAM a WRITER, donc de binaire a CHAR
//                OutputStreamWriter osw = new OutputStreamWriter(fos);
//                //création buffer avec le flux char
//                BufferedWriter bw = new BufferedWriter(osw))
//
//            {
//                bw.write(memo);
//                bw.newLine(); //changer ligne
//                finish(); //revient a activité de base
//                // bw.close();
//            }
//
//            catch (FileNotFoundException e) {
//                //si on sai pas quoi faire avec l'erreur, au moins print l'erreur
//                e.printStackTrace();
//            }
//
//            catch (IOException e) {
//                e.printStackTrace();
//            }


        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            temp = LocalDate.of(year,month+1,dayOfMonth);
            texteDateChoisi.setText(temp.format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));
            //attn: mm = minute;
        }
    }
}