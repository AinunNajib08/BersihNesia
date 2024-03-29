package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.ReedemAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.GetItemReedem;
import com.example.bersihnesia.model.PostPersonal;
import com.example.bersihnesia.model.Reedem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class ReedemActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    BaseApiService mApiInterface;
    EditText editText;
    TextView txt_point;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    String sIdPersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reedem);
        sharedPreferences = ReedemActivity.this.getSharedPreferences("remember", Context.MODE_PRIVATE);
        sIdPersonal = sharedPreferences.getString("id_personal","1");
        txt_point=findViewById(R.id.jum_point);
        mRecyclerView=findViewById(R.id.rv_item);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(GONE);
        mLayoutManager=new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mApiInterface= UtilsApi.getAPIService();
        refresh();
        getPoint();
    }

    private void refresh() {
        progressBar.setVisibility(View.VISIBLE);
        Call<GetItemReedem> reedemCall=mApiInterface.getItem();
        reedemCall.enqueue(new Callback<GetItemReedem>() {
            @Override
            public void onResponse(Call<GetItemReedem> call, Response<GetItemReedem> response) {
                progressBar.setVisibility(GONE);
                List<Reedem> reedems=response.body().getReedemList();
                mAdapter=new ReedemAdapter(reedems,ReedemActivity.this);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<GetItemReedem> call, Throwable t) {

            }
        });
    }
    private void getPoint() {
        Call<PostPersonal> postPoint=mApiInterface.postPoint(sIdPersonal);
        postPoint.enqueue(new Callback<PostPersonal>() {
            @Override
            public void onResponse(Call<PostPersonal> call, Response<PostPersonal> response) {
                String my_point=response.body().getPoint();
                txt_point.setText(my_point+" pt");
            }
            @Override
            public void onFailure(Call<PostPersonal> call, Throwable t) {

            }
        });
    }
}
