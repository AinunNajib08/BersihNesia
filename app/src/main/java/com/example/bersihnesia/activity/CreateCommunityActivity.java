package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.PostPersonal;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCommunityActivity extends PdfActivity {
    TextView pdf_name,lol;
    ImageButton btn_pilih, bt_picker;
    EditText name_community, no_hp, lokasi, deskripsi;
    Button daftar;
    private int PLACE_PICKER_REQUEST = 2;
    SharedPreferences sharedPreferences;
    BaseApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
        mApiService = UtilsApi.getAPIService();
        sharedPreferences = this.getSharedPreferences("remember",Context.MODE_PRIVATE);
        String sNo = sharedPreferences.getString("no","0");
        final String sId = sharedPreferences.getString("id_personal","1");
        pdf_name = findViewById(R.id.pdf_name);
        Intent mIntent = getIntent();
        pdf_name.setText(mIntent.getStringExtra("pdf_name"));
        btn_pilih = findViewById(R.id.btn_pilih);
        name_community = findViewById(R.id.name_community);
        no_hp = findViewById(R.id.no_hp);
        no_hp.setText(sNo);
        lokasi = findViewById(R.id.lokasi);
        deskripsi = findViewById(R.id.deskripsi);
        daftar = findViewById(R.id.daftar);
        lol=findViewById(R.id.longlat);
        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadFile();
                Call<PostPersonal> postCommunity=mApiService.postCommunity(sId,name_community.getText().toString(),no_hp.getText().toString(),lokasi.getText().toString(),lol.getText().toString(),deskripsi.getText().toString(),pdf_name.getText().toString());
                postCommunity.enqueue(new Callback<PostPersonal>() {
                    @Override
                    public void onResponse(Call<PostPersonal> call, Response<PostPersonal> response) {
                        Toast.makeText(CreateCommunityActivity.this, "Komunitas akan di verifikasi oleh admin terlebih dahulu!", Toast.LENGTH_LONG).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<PostPersonal> call, Throwable t) {

                    }
                });

            }
        });
        bt_picker = findViewById(R.id.bt_ppicker);
        bt_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(CreateCommunityActivity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
            }
        });

        btn_pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateCommunityActivity.this, PdfActivity.class);
                intent.putExtra("name_com", name_community.getText().toString());
                intent.putExtra("location", lokasi.getText().toString());
                intent.putExtra("desc", deskripsi.getText().toString());
                intent.putExtra("longlat", lol.getText().toString());
                startActivity(intent);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            name_community.setText(bundle.getString("komunitas"));
            lokasi.setText(bundle.getString("lokasi"));
            deskripsi.setText(bundle.getString("deskripsi"));
            lol.setText(bundle.getString("longlat"));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == PLACE_PICKER_REQUEST && resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String toastMsg = String.format(

                        "%s "
                        , place.getAddress());
                String toastLatLong = String.format(
                        place.getLatLng().latitude + "," + place.getLatLng().longitude);
                lokasi.setText(toastMsg);
                lol.setText(toastLatLong);
            }

        } catch(Exception e){
            Toast.makeText(CreateCommunityActivity.this, "Koneksi anda bermasalah", Toast.LENGTH_LONG).show();
        }
    }
}
