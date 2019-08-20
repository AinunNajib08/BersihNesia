package com.example.bersihnesia.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.CommunityAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.Community;
import com.example.bersihnesia.model.GetCommunity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityActivity extends AppCompatActivity {
RecyclerView mRecyclerView;
RecyclerView.Adapter mAdapter;
RecyclerView.LayoutManager mLayoutManager;
BaseApiService mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        mRecyclerView=findViewById(R.id.rv_community);
        mLayoutManager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface=UtilsApi.getAPIService();
        refresh();
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

}
