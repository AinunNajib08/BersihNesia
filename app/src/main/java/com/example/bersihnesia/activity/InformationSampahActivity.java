package com.example.bersihnesia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.SampahOrganikAdapter;
import com.example.bersihnesia.adapter.SampahnonOrganikAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.GetSampahnonOrganik;
import com.example.bersihnesia.model.SampahOrganik;
import com.example.bersihnesia.model.GetSampahOrganik;
import com.example.bersihnesia.model.SampahnonOrganik;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationSampahActivity extends AppCompatActivity {
RecyclerView rv_nonorganik, rv_organik;
CardView organik, nonorganik;
RecyclerView.Adapter mAdapter;
RecyclerView.LayoutManager mLayoutManager;
BaseApiService mApiInterface;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_sampah);
        rv_nonorganik = findViewById(R.id.rv_nonorganik);
        rv_organik = findViewById(R.id.rv_organik);
        mLayoutManager=new LinearLayoutManager(this);
        rv_nonorganik.setLayoutManager(mLayoutManager);
        rv_organik.setLayoutManager(mLayoutManager);
        progressBar = findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        mApiInterface= UtilsApi.getAPIService();
        rv_nonorganik.setVisibility(View.GONE);
        rv_organik.setVisibility(View.GONE);
        refresh();
        refresh2();
        organik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rv_organik.getVisibility() == View.VISIBLE) {
                    rv_organik.setVisibility(View.GONE);
                } else {
                    rv_organik.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    public void nonorganik(View view) {
        if (rv_nonorganik.getVisibility() == View.VISIBLE) {
            rv_nonorganik.setVisibility(View.GONE);
        } else {
            rv_nonorganik.setVisibility(View.VISIBLE);
        }
    }

    private void refresh() {
        Call<GetSampahOrganik> sampahOrganikCall = mApiInterface.getSampahOrganik();
        sampahOrganikCall.enqueue(new Callback<GetSampahOrganik>() {
            @Override
            public void onResponse(Call<GetSampahOrganik> call, Response<GetSampahOrganik> response) {
                progressBar.setVisibility(View.VISIBLE);
                List<SampahOrganik> sampahOrganikList = response.body().getSampahOrganikList();
                mAdapter=new SampahOrganikAdapter(sampahOrganikList, InformationSampahActivity.this);
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(sampahOrganikList.size()));
                rv_organik.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetSampahOrganik> call, Throwable t) {

            }
        });

    }

    private void refresh2() {
        Call<GetSampahnonOrganik> sampahnonOrganikCall = mApiInterface.getSampahnonOrganik();
        sampahnonOrganikCall.enqueue(new Callback<GetSampahnonOrganik>() {
            @Override
            public void onResponse(Call<GetSampahnonOrganik> call, Response<GetSampahnonOrganik> response) {
                progressBar.setVisibility(View.VISIBLE);
                List<SampahnonOrganik> sampahnonOrganikList = response.body().getSampahnonOrganikList();
                mAdapter=new SampahnonOrganikAdapter(sampahnonOrganikList, InformationSampahActivity.this);
                Log.d("Retrofit Get", "Jumlah data Kontak: " +
                        String.valueOf(sampahnonOrganikList.size()));
                rv_nonorganik.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetSampahnonOrganik> call, Throwable t) {

            }
        });
    }
}
