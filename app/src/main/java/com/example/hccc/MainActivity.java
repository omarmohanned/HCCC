package com.example.hccc;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView splash;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        splash=findViewById(R.id.splash);

        final ProgressDialog progressDialogspalsh=new ProgressDialog(MainActivity.this);
        progressDialogspalsh.setMessage("welcome to our HCcC");

        Handler sec =new Handler();

        sec.postDelayed(new Runnable() {
            @Override
            public void run() {
                CountDownTimer a=new CountDownTimer(3000,1000) {
                    @Override
                    public void onTick(long l) {
                     progressDialogspalsh.show();
                    }

                    @Override
                    public void onFinish() {
                        progressDialogspalsh.cancel();
                        startActivity(new Intent(getApplicationContext(),log_in.class));
                        finish();
                    }
                }.start();
            }
        },3000);




    }
}