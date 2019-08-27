package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.bersihnesia.R;

public class ReedemPointActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView name_item,my_point,item_point;
    EditText nama,address,no_hp;
    Button batal,tukar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_reedem);
        sharedPreferences = this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        String sNamaItem = sharedPreferences.getString("name_item","name");
        String sPointItem = sharedPreferences.getString("jmlh","jum_point");
        String sPoint = sharedPreferences.getString("point","my_point");
        String sNama = sharedPreferences.getString("name","nama");
        String sNo = sharedPreferences.getString("no","no_hp");
        String sAddress = sharedPreferences.getString("address","alamat");
        name_item=findViewById(R.id.name_item);
        name_item.setText(sNamaItem);
        item_point=findViewById(R.id.item_point);
        item_point.setText(sPointItem+" pt");
        my_point=findViewById(R.id.my_point);
        my_point.setText(sPoint+" pt");
        nama=findViewById(R.id.nama);
        nama.setText(sNama);
        address=findViewById(R.id.alamat);
        address.setText(sAddress);
        no_hp=findViewById(R.id.no);
        no_hp.setText(sNo);
        batal=findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



}
