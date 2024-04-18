package com.example.annexe3b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.SeekBar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    SeekBar seek1;
    SeekBar seek2;
    SeekBar seek3;

    Ecouteur ec;

//    ArrayList<Integer> listeValeurs;

    Context contexte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contexte = getApplicationContext();
        seek1 = findViewById(R.id.seekBar1);
        seek2 = findViewById(R.id.seekBar2);
        seek3 = findViewById(R.id.seekBar3);
        ec = new Ecouteur();
        seek1.setOnSeekBarChangeListener(ec);
        seek2.setOnSeekBarChangeListener(ec);
        seek3.setOnSeekBarChangeListener(ec);


    }



    public void serializeSeekBarValues() throws IOException {
        try (FileOutputStream fos = contexte.openFileOutput("seekBarsValues.ser", Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) { // un ObjectOutput qui prend le FileOutputStream
            // Write each seekbar's progress value individually
            oos.writeInt(seek1.getProgress());
            oos.writeInt(seek2.getProgress());
            oos.writeInt(seek3.getProgress());
        }
    }
    public void deserializeSeekBarValues() {
        try (FileInputStream fis = contexte.openFileInput("seekBarsValues.ser");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // Read the individual progress values for each seekbar
            int seek1Value = ois.readInt(); // va lire le premier objet
            int seek2Value = ois.readInt(); // le curseur est maintenant rendu au deuxieme objet
            int seek3Value = ois.readInt();

            // Set the seekbar progress
            seek1.setProgress(seek1Value);
            seek2.setProgress(seek2Value);
            seek3.setProgress(seek3Value);
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, it's likely the first app launch, so you could set default values or log this event.
            // No need to print stack trace here. You might want to set default values or do nothing.
            // Setting default progress if needed:
             seek1.setProgress(0);
             seek2.setProgress(0);
             seek3.setProgress(0);
        } catch (IOException e) {
            e.printStackTrace(); // Handle other IO exceptions here.
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            serializeSeekBarValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        deserializeSeekBarValues();
    }


    public class Ecouteur implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // This method is correctly opened and closed here
            if (fromUser) { // Check if the change was initiated by the user
                // Since you're serializing values individually, you might want to directly serialize
                // the new value here, or call a method that determines which seekBar was adjusted
                // and then serialize that value.

                try {
                    serializeSeekBarValues();
                } catch (IOException e) {
                    e.printStackTrace(); // Proper error handling should be implemented
                }
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // Handle start of touch event
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // Handle end of touch event
        }

        // You might consider removing updateListWithSeekBarValue if you're serializing directly
    }

//    private void serializeSeekBarValue(int seekBarId, int progress) throws IOException {
//        // Assuming a separate file for each seekBar for simplicity
//        String filename = getFileNameForSeekBar(seekBarId);
//        try (FileOutputStream fos = contexte.openFileOutput(filename, Context.MODE_PRIVATE);
//             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
//            oos.writeInt(progress);
//        }
//    }

    private String getFileNameForSeekBar(int seekBarId) {
        // This method returns a unique filename for each seekBar
        switch (seekBarId) {
            case R.id.seekBar1:
                return "seekBar1.ser"; // ca pourrait etre .bin, meme peut-etre .txt
            case R.id.seekBar2:
                return "seekBar2.ser";
            case R.id.seekBar3:
                return "seekBar3.ser";
            default:
                throw new IllegalArgumentException("Unknown SeekBar ID");
        }
    }
}