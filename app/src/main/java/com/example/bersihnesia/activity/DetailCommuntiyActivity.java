package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.CommunityTabLayout;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.Event;

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
    String idPersonal;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_communtiy);
        txt_title = findViewById(R.id.name_community);
        imageView = findViewById(R.id.photo);
        jumlah = findViewById(R.id.jumlah);
        TabLayout tabLayout = findViewById(R.id.favorite_tab_layout);
        ViewPager viewPager = findViewById(R.id.favorite_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new CommunityTabLayout(getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);

        sharedPreferences = this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        idPersonal = sharedPreferences.getString("id_personal", null);
        mApiService = UtilsApi.getAPIService();
        intent = getIntent();
        txt_title.setText(intent.getStringExtra("name_community"));
        Log.e("RAG", "onCreate: " + intent.getIntExtra("id_com", 0));
        getSum();
        getCheck();
    }

    void getSum() {
        mApiService.getSum(intent.getIntExtra("id_com", 0))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                String data = jsonRESULTS.getString("result");
                                jumlah.setText(data + " Orang bergabung dalam komunitas");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
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
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                if (jsonRESULTS.getString("result").equals("0")){
                                    Log.e("RAG", "True: " );
                                } else {
                                    JSONArray data = jsonRESULTS.getJSONArray("result");
                                    for (int i=0; i <data.length(); i++) {
                                        JSONObject jsonObject = data.getJSONObject(i);
                                        String id_event = jsonObject.getString("status_member");
                                        String name_event = jsonObject.getString("name");
                                        String desc = jsonObject.getString("email");
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {

                    }
                });
    }
}