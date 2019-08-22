package com.example.bersihnesia.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.EventTabLayout;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailEventActivity extends AppCompatActivity {

    int code;
    BaseApiService mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        mApiService = UtilsApi.getAPIService();
        Bundle bundle = getIntent().getExtras();
//        code = bundle.getInt("data1");
//        TabLayout tabLayout = findViewById(R.id.event_tab_layout);
//        ViewPager viewPager = findViewById(R.id.event_viewpager);
//        viewPager.setOffscreenPageLimit(3);
//        viewPager.setAdapter(new EventTabLayout(getSupportFragmentManager(), this));
//        tabLayout.setupWithViewPager(viewPager);
    }

    void getEvent(){
        mApiService.getEventDetail(code)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
