package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("*********** ON CREATE ****************");
    }

    //•	onRestart





    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("*********** ON START ****************");

    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("*********** ON STOP ****************");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("*********** ON DESTROY ****************");
    }

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("*********** ON PAUSE ****************");
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("*********** ON RESUME ****************");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        System.out.println("*********** ON restart ****************");
    }
}