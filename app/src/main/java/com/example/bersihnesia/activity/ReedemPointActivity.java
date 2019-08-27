package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
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

public class ReedemPointActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    TextView name_item,my_point,item_point;
    EditText nama,address,no_hp;
    Button batal,tukar;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_reedem);
        sharedPreferences = this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        final int sIdItem = sharedPreferences.getInt("id_item",0);
        final String sIdPersonal = sharedPreferences.getString("id_personal","id_personal");
        String sNamaItem = sharedPreferences.getString("name_item","name");
        final String sPointItem = sharedPreferences.getString("jmlh","jum_point");
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
        tukar=findViewById(R.id.tukar);
        mApiService = UtilsApi.getAPIService();
        tukar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<PostPersonal> postReedem=mApiService.postReedem(sIdPersonal,sIdItem,nama.getText().toString(),no_hp.getText().toString(),address.getText().toString(),sPointItem);
                postReedem.enqueue(new Callback<PostPersonal>() {
                    @Override
                    public void onResponse(Call<PostPersonal> call, Response<PostPersonal> response) {
                        // Get the custom layout view.
                        View toastView = getLayoutInflater().inflate(R.layout.custom_toast, null);

                        // Initiate the Toast instance.
                        Toast toast = new Toast(getApplicationContext());
                        // Set custom view in toast.
                        toast.setView(toastView);
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0,0);
                        toast.show();
                        Intent intent=new Intent(ReedemPointActivity.this,HomeActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<PostPersonal> call, Throwable t) {
                        Toast.makeText(ReedemPointActivity.this, "Gagal", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        batal=findViewById(R.id.batal);
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }



}
