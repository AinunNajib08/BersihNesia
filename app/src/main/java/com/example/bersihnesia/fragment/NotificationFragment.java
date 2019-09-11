package com.example.bersihnesia.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.CommunityActivity;
import com.example.bersihnesia.adapter.CommunityAdapter;
import com.example.bersihnesia.adapter.NotifAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.Community;
import com.example.bersihnesia.model.GetCommunity;
import com.example.bersihnesia.model.GetItemReedem;
import com.example.bersihnesia.model.PostNotif;
import com.example.bersihnesia.model.PostPersonal;
import com.example.bersihnesia.model.Reedem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationFragment extends Fragment {
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    BaseApiService mApiInterface;
    EditText editText;
    ProgressBar progressBar;
    String sIdPersonal;
    SharedPreferences sharedPreferences;

    public NotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_notification, container, false);
        mRecyclerView=view.findViewById(R.id.rv_notif);
        mLayoutManager=new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        progressBar = view.findViewById(R.id.progressBar);
        mApiInterface= UtilsApi.getAPIService();
        sharedPreferences = getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        sIdPersonal = sharedPreferences.getString("id_personal", "1");
        refresh();
        return view;
    }

    private void refresh() {
        Call<PostNotif> transCall=mApiInterface.postTrans(sIdPersonal);
        transCall.enqueue(new Callback<PostNotif>() {
            @Override
            public void onResponse(Call<PostNotif> call, Response<PostNotif> response) {
                progressBar.setVisibility(View.GONE);
                List<Reedem> communityList=response.body().getNotifList();
                mAdapter=new NotifAdapter(communityList,getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<PostNotif> call, Throwable t) {
                Toast.makeText(getContext(),"Koneksi Anda Lemah",Toast.LENGTH_SHORT).show();

            }
        });
    }

}
