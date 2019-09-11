package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bersihnesia.R;

public class JumlahBarangActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
TextView name;
Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_barang);
        sharedPreferences = this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        intent = getIntent();
        name=findViewById(R.id.name);
        name.setText(intent.getStringExtra("nama"));


    }
}
