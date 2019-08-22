package com.example.bersihnesia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.example.bersihnesia.R;

public class InformationActivity extends AppCompatActivity {
CardView tentangsampah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);
        tentangsampah = findViewById(R.id.c_sampah);
        tentangsampah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent g = new Intent(InformationActivity.this, InformationSampahActivity.class);
            }
        });
    }
}
