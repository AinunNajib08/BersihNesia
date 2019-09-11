package com.example.bersihnesia.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.EventTabLayout;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.fragment.tablayout.LokasiFragment;
import com.example.bersihnesia.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {


    public EventFragment() {
        // Required empty public constructor
    }

    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    TextView tvNameEvent, tvCommunity, tvFollow, tvMode;
    String nameEvent, Community, Follow, Mode;
    String idPersonal;
    int id_event;
    BaseApiService mApiService;
    Button followEvent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mApiService = UtilsApi.getAPIService();
        tvNameEvent = view.findViewById(R.id.name_event);
        followEvent = view.findViewById(R.id.followEvent);
        tvCommunity = view.findViewById(R.id.name_community);
        tvFollow = view.findViewById(R.id.follow);
        tvMode = view.findViewById(R.id.tvMode);
        progressBar = view.findViewById(R.id.progBar);
        progressBar.setVisibility(View.GONE);
        getDataEvent();
        sharedPreferences = view.getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);

        idPersonal = sharedPreferences.getString("id_personal",null);
        followEvent.setVisibility(View.GONE);
        followEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getInsert();
                followEvent.setVisibility(View.GONE);
            }
        });
        return view;
    }

    private void getDataEvent(){
        Bundle bundleget = getArguments();
        int Hallo = bundleget.getInt("Code");
        Log.e("RAG", "Codeeee : "+Hallo );
        mApiService.getEventDetail(Hallo)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray data = jsonRESULTS.getJSONArray("result");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    id_event = jsonObject.getInt("id_event");
                                    Log.e("RAG", "onResponse: "+id_event );
                                    nameEvent = jsonObject.getString("name_event");
                                    Community = jsonObject.getString("name_community");
                                    if (jsonObject.getString("status").equals("1")){
                                        Mode = "Sudah Terdaftar";
                                    } else {
                                        Mode = "Belum Terdaftar";
                                    }
                                    tvNameEvent.setText(nameEvent);
                                    tvCommunity.setText("by "+Community);
                                    tvMode.setText(Mode);
                                    getCheck();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TabLayout tabLayout = view.findViewById(R.id.favorite_tab_layout);
        ViewPager viewPager = view.findViewById(R.id.favorite_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new EventTabLayout(getChildFragmentManager(), getActivity()));

        tabLayout.setupWithViewPager(viewPager);
    }

    void getCheck(){
        int id_personal = Integer.parseInt(idPersonal);
        mApiService.getCheck(id_event, id_personal)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                Boolean data = jsonRESULTS.getBoolean("result");
                                if (data){
                                    followEvent.setVisibility(View.GONE);
                                } else {
                                    followEvent.setVisibility(View.VISIBLE);
                                }
                                Log.e("RAG", "onResponse: "+data );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    void getInsert(){
        int id_personal = Integer.parseInt(idPersonal);
        mApiService.getInsert(id_event, id_personal)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                Boolean data = jsonRESULTS.getBoolean("result");
                                Log.e("RAG", "onResponse: "+data );
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            progressBar.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }
}
