package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.bersihnesia.R;
import com.wang.avi.AVLoadingIndicatorView;

public class SplashActivity extends AppCompatActivity {
    private LinearLayout lv_loading;
    SharedPreferences sharedPreferences;
    String cek;
    private AVLoadingIndicatorView avi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences = getApplicationContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        String sNama = sharedPreferences.getString("name", "0");
        cek = sharedPreferences.getString("id_personal", "id");
        lv_loading = (LinearLayout) findViewById(R.id.lv_loading);
        avi= (AVLoadingIndicatorView) findViewById(R.id.avi);
        avi.setIndicator("BallClipRotateMultipleIndicator");
        //membuat sebuah proses yang ter delay
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (cek.equals("0")){
                    Intent i = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(i);
                } else {
                    Intent i = new Intent(SplashActivity.this,HomeActivity.class);
                    startActivity(i);
                }

                //finish berguna untuk mengakhiri activity
                //disini saya menggunakan finish,supaya ketika menekan tombol back
                //tidak kembali pada activity SplashScreen nya
                finish();
            }
            //disini maksud 3000 nya itu adalah lama screen ini terdelay 3 detik,dalam satuan mili second
        }, 3000);
    }
}
