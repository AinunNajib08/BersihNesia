package com.example.bersihnesia.fragment.tablayout;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.MemberAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.fragment.HomeFragment;
import com.example.bersihnesia.model.Member;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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
    ArrayList<Member> arrayMember = new ArrayList<>();
    BaseApiService apiService;
    String idPersonal;
    MemberAdapter memberAdapter;
    RecyclerView rv_member;
    SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        apiService = UtilsApi.getAPIService();
        memberAdapter = new MemberAdapter(getContext());
        rv_member = view.findViewById(R.id.rv_member);
        rv_member.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rv_member.setLayoutManager(layoutManager);
        sharedPreferences = view.getContext().getSharedPreferences("remember", Context.MODE_PRIVATE);
        idPersonal = sharedPreferences.getString("id_personal",null);
        getVoid();
        return view;
    }

    void getVoid(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int Hallo = sharedPreferences.getInt(HomeFragment.STATE_EVENT, 0);
        Log.e("RAG", "CuMMM: "+Hallo );
        apiService.getMember(Hallo)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray data = jsonRESULTS.getJSONArray("result");
                                for (int i = 0; i < data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    Member member = new Member();
                                    member.setName(jsonObject.getString("name"));
                                    member.setAddress(jsonObject.getString("address"));
                                    member.setPhoto(jsonObject.getString("photo"));
                                    arrayMember.add(member);
                                }
                                memberAdapter.setListMember(arrayMember);
                                rv_member.setAdapter(memberAdapter);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });


    }

}
