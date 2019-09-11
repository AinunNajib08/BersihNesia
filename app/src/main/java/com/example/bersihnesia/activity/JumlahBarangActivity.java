package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.bersihnesia.R;

public class JumlahBarangActivity extends AppCompatActivity {
SharedPreferences sharedPreferences;
TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_barang);
        sharedPreferences = this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        String sNama = sharedPreferences.getString("nama_da", "0");
        String sId = sharedPreferences.getString("id_da", "id");
        name=findViewById(R.id.name);
        name.setText(sNama);

    }
}
