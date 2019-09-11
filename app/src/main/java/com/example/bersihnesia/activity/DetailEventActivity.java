package com.example.bersihnesia.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.EventTabLayout;
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

public class DetailEventActivity extends AppCompatActivity {

    int code;
    BaseApiService mApiService;
    TextView tvNameEvent, tvCommunity, tvMode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        mApiService = UtilsApi.getAPIService();
        Bundle bundle = getIntent().getExtras();
        tvNameEvent = findViewById(R.id.name_event);
        tvCommunity = findViewById(R.id.name_community);
        tvMode = findViewById(R.id.tvMode);
        code = bundle.getInt("data1");
        Log.e("RAG", "CodeTM: "+code );
        TabLayout tabLayout = findViewById(R.id.event_tab_layout);
        ViewPager viewPager = findViewById(R.id.event_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new EventTabLayout(getSupportFragmentManager(), this));
        tabLayout.setupWithViewPager(viewPager);
        getEvent();
    }

    void getEvent(){
        mApiService.getEventDetail(code)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray data = jsonRESULTS.getJSONArray("result");
                                for (int i=0; i <data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    tvNameEvent.setText(jsonObject.getString("name_event"));
                                    tvCommunity.setText("by "+jsonObject.getString("name_community"));
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
