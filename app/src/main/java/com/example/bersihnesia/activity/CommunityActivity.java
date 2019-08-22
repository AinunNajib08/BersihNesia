package com.example.bersihnesia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.CommunityAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.Community;
import com.example.bersihnesia.model.GetCommunity;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityActivity extends AppCompatActivity {
RecyclerView mRecyclerView;
RecyclerView.Adapter mAdapter;
RecyclerView.LayoutManager mLayoutManager;
BaseApiService mApiInterface;
EditText editText;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        mRecyclerView=findViewById(R.id.rv_community);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressBar = findViewById(R.id.pBar);
        progressBar.setVisibility(View.GONE);
        mApiInterface=UtilsApi.getAPIService();
        editText = findViewById(R.id.search);
        refresh();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    getSearch(s);
                }
            }
        });
    }

    private void refresh() {
        Call<GetCommunity> communityCall=mApiInterface.getCommunity();
        communityCall.enqueue(new Callback<GetCommunity>() {
            @Override
            public void onResponse(Call<GetCommunity> call, Response<GetCommunity> response) {
                List<Community> communityList=response.body().getCommunityList();
                mAdapter=new CommunityAdapter(communityList,CommunityActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetCommunity> call, Throwable t) {

            }
        });

    }

    void getSearch(Editable cari){
        Call<GetCommunity> communityCall=mApiInterface.getSearch(cari);
        communityCall.enqueue(new Callback<GetCommunity>() {
            @Override
            public void onResponse(Call<GetCommunity> call, Response<GetCommunity> response) {
                progressBar.setVisibility(View.VISIBLE);
                List<Community> communityList=response.body().getCommunityList();
                mAdapter=new CommunityAdapter(communityList,CommunityActivity.this);
                mRecyclerView.setAdapter(mAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<GetCommunity> call, Throwable t) {

            }
        });
    }

}
