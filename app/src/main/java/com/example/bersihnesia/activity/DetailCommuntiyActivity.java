package com.example.bersihnesia.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.CommunityTabLayout;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCommuntiyActivity extends AppCompatActivity {
    TextView txt_title, jumlah;
    ImageView imageView;
    BaseApiService mApiService;
    Intent intent;
    String idPersonal,id_personal;
    SharedPreferences sharedPreferences;
    Button btnEvent, btnJoin;
    FloatingActionButton scanner;
    private IntentIntegrator qrScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_communtiy);
        txt_title = findViewById(R.id.name_community);
        imageView = findViewById(R.id.photo);
        jumlah = findViewById(R.id.jumlah);
        btnEvent = findViewById(R.id.create_event);
        btnJoin = findViewById(R.id.gabung);
        TabLayout tabLayout = findViewById(R.id.favorite_tab_layout);
        ViewPager viewPager = findViewById(R.id.favorite_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new CommunityTabLayout(getSupportFragmentManager(), this));
        qrScan = new IntentIntegrator(this);
        tabLayout.setupWithViewPager(viewPager);

        btnEvent.setVisibility(View.GONE);
        btnJoin.setVisibility(View.GONE);

        sharedPreferences = this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        idPersonal = sharedPreferences.getString("id_personal", null);
        mApiService = UtilsApi.getAPIService();
        intent = getIntent();
        txt_title.setText(intent.getStringExtra("name_community"));
        Log.e("RAG", "onCreate: " + intent.getIntExtra("id_com", 0));
        getSum();
        getCheck();
        Glide.with(this)
                .load("http://jwpdigitalent.com/gagas/upload/"+intent.getStringExtra("photo"))
                .placeholder(R.drawable.ic_banner)
                .apply(new RequestOptions().transform(new RoundedCorners(1)))
                .into(imageView);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInsert();
                btnJoin.setVisibility(View.GONE);
            }
        });
        btnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(DetailCommuntiyActivity.this, CreateEventActivity.class);
                intents.putExtra("id_comm", intent.getIntExtra("id_com", 0));
                startActivity(intents);
            }
        });
        scanner=findViewById(R.id.scanner);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qrScan.initiateScan();
                showDialog();
            }
        });
    }

    void getSum() {
        mApiService.getSum(intent.getIntExtra("id_com", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                assert response.body() != null;
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                String data = jsonRESULTS.getString("result");
                                jumlah.setText(data + " Orang bergabung");
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                    }
                });
    }

    void getCheck() {
        int id_personal = Integer.parseInt(idPersonal);
        mApiService.getCheckMember(intent.getIntExtra("id_com", 0), id_personal)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                assert response.body() != null;
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("result").equals("null")){
                                    Log.e("RAG", "True: " );
                                    btnJoin.setVisibility(View.VISIBLE);
                                } else {
                                    JSONArray data = jsonRESULTS.getJSONArray("result");
                                    for (int i=0; i <data.length(); i++) {
                                        JSONObject jsonObject = data.getJSONObject(i);
                                        String id_event = jsonObject.getString("status_member");
                                        Log.e("RAG", "onResponse: "+id_event );
                                        if (id_event.equals("Ketua")) {
                                            btnEvent.setVisibility(View.VISIBLE);
                                            btnJoin.setVisibility(View.GONE);
                                        } else {
                                            btnJoin.setVisibility(View.GONE);
                                        }
                                        String photo = jsonObject.getString("photo");
                                        String desc = jsonObject.getString("email");
                                        Log.e("RAG", "onResponse: "+photo );
                                    }
                                }
                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                    }
                });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            //if qrcode has nothing in it
            if (result.getContents() == null) {
                Toast.makeText(this, "Barcode Tidak Ada", Toast.LENGTH_LONG).show();
            } else {
                //if qr contains data
                try {
                    //converting the data to json
                    JSONObject obj = new JSONObject(result.getContents());
                    //setting values to textviews
                     id_personal= (obj.getString("id_personal"));
                    //alamat.setText(obj.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    //if control comes here
                    //that means the encoded format not matches
                    //in this case you can display whatever data is available on the qrcode
                    //to a toast
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_scanner);
        Button iya=dialog.findViewById(R.id.iya);
        TextView id=dialog.findViewById(R.id.id_personal);
        id.setText(id_personal);
        iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();

    }

    void getInsert(){
        int id_personal = Integer.parseInt(idPersonal);
        mApiService.getInsertJoin(intent.getIntExtra("id_com", 0), id_personal)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        Toast.makeText(getApplicationContext(), "Berhasil Bergabung Dengan Komunitas", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                    }
                });
    }
}