package com.example.bersihnesia.fragment;


import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    TextView tvNameEvent, tvCommunity, tvFollow, tvMode;
    String nameEvent, Community, Follow, Mode;
    BaseApiService mApiService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        mApiService = UtilsApi.getAPIService();
        tvNameEvent = view.findViewById(R.id.name_event);
        tvCommunity = view.findViewById(R.id.name_community);
        tvFollow = view.findViewById(R.id.follow);
        tvMode = view.findViewById(R.id.tvMode);
        progressBar = view.findViewById(R.id.progBar);
        progressBar.setVisibility(View.GONE);
        getDataEvent();
        return view;
    }

    private void getDataEvent(){
        Bundle bundleget = getArguments();
        int Hallo = bundleget.getInt("Code");
        Log.e("RAG", "onViewCreated: "+Hallo );
        progressBar.setVisibility(View.VISIBLE);
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
                                    nameEvent = jsonObject.getString("name_event");
                                    Community = jsonObject.getString("name_community");
                                    Mode = jsonObject.getString("status");
                                    tvNameEvent.setText(nameEvent);
                                    tvCommunity.setText("by "+Community);
                                    tvMode.setText(Mode);
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
    }

}
