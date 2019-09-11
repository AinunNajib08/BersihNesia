package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.PostPersonal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JumlahBarangActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView name;
    Intent intent;
    String id_ku;
    Button jumlah;
    EditText jml;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jumlah_barang);
        intent = getIntent();
        name = findViewById(R.id.name);
        name.setText(intent.getStringExtra("nama"));
        id_ku = intent.getStringExtra("id");
        mApiService = UtilsApi.getAPIService();
        jml=findViewById(R.id.jum_sampah);
        jumlah = findViewById(R.id.jumlah);
        jumlah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPersonal> postJumlah =mApiService.postSampah(id_ku,jml.getText().toString());
                postJumlah.enqueue(new Callback<PostPersonal>() {
                    @Override
                    public void onResponse(Call<PostPersonal> call, Response<PostPersonal> response) {
                        Toast.makeText(JumlahBarangActivity.this, "Berhasil", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<PostPersonal> call, Throwable t) {
                        Toast.makeText(JumlahBarangActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


}
