package com.example.bersihnesia.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.SampahOrganikAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.SampahOrganik;
import com.example.bersihnesia.model.GetSampahOrganik;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationSampahActivity extends AppCompatActivity {
RecyclerView nonorganik, organik;
RecyclerView.Adapter mAdapter;
RecyclerView.LayoutManager mLayoutManager;
BaseApiService mApiInterface;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_sampah);
        nonorganik = findViewById(R.id.rv_nonorganik);
        organik = findViewById(R.id.rv_organik);
        mLayoutManager=new LinearLayoutManager(this);
        nonorganik.setLayoutManager(mLayoutManager);
        progressBar = findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        mApiInterface= UtilsApi.getAPIService();
        nonorganik.setVisibility(View.VISIBLE);
        refresh();

    }

    public void nonorganik(View view) {
        nonorganik.setVisibility(View.VISIBLE);
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
                nonorganik.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetSampahOrganik> call, Throwable t) {

            }
        });

    }
}
