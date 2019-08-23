package com.example.bersihnesia.fragment.tablayout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bersihnesia.R;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.fragment.HomeFragment;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment {


    public MoreFragment() {
        // Required empty public constructor
    }

    BaseApiService apiService;
    String idPersonal;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        apiService = UtilsApi.getAPIService();
        sharedPreferences = view.getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        idPersonal = sharedPreferences.getString("id_personal",null);
        getVoid();
        return view;
    }

    void getVoid(){
        int id_personal = Integer.parseInt(idPersonal);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int Hallo = sharedPreferences.getInt(HomeFragment.STATE_EVENT, 0);
        Log.e("RAG", "CuMMM: "+Hallo );
        apiService.getMember(Hallo, id_personal)
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
