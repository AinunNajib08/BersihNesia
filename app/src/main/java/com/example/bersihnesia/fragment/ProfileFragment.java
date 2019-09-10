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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.CommunityActivity;
import com.example.bersihnesia.activity.LoginActivity;
import com.example.bersihnesia.adapter.CommunityAdapter;
import com.example.bersihnesia.adapter.JoinAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.model.Community;
import com.example.bersihnesia.model.PostJoin;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
    SharedPreferences sharedPreferences;
    TextView name, email, jk, no, address, photo;
    CircleImageView img;
    BaseApiService mApiService;
    String sId;
    RecyclerView mRecyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // http://jwpdigitalent.com/gagas/upload/Screenshot_2019-08-06-23-23-40-25.png
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreferences = view.getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        String sNama = sharedPreferences.getString("name", "0");
        sId = sharedPreferences.getString("id_personal", "id");
        String sNo = sharedPreferences.getString("no", "1");
        String sEmail = sharedPreferences.getString("email", "0");
        String sJk = sharedPreferences.getString("jk", "0");
        String sAddress = sharedPreferences.getString("address", "0");
        String sPhoto = sharedPreferences.getString("photo", "0");
        name = view.findViewById(R.id.name);
        name.setText(sNama);
        email = view.findViewById(R.id.email);
        email.setText(sEmail);
        jk = view.findViewById(R.id.jk);
        jk.setText(sJk);
        no = view.findViewById(R.id.no);
        no.setText(sNo);
        address = view.findViewById(R.id.alamat);
        address.setText(sAddress);
        photo = view.findViewById(R.id.photo);
        photo.setText(sPhoto);
        img = view.findViewById(R.id.img);
        final String urlGambarBerita = "http://jwpdigitalent.com/gagas/upload/" + sPhoto;
        Picasso.with(getContext()).load(urlGambarBerita).into(img);
        mApiService = UtilsApi.getAPIService();
        mRecyclerView=view.findViewById(R.id.rv_komunitas);
        mLayoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        refresh();
        return view;
    }

    private void refresh() {
        retrofit2.Call<PostJoin> postJoin = mApiService.postJoin(sId);
        postJoin.enqueue(new Callback<PostJoin>() {
            @Override
            public void onResponse(Call<PostJoin> call, Response<PostJoin> response) {
                List<Community> communityList=response.body().getJoinList();
                mAdapter=new JoinAdapter(communityList, getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onFailure(Call<PostJoin> call, Throwable t) {

            }
        });


    }


}
