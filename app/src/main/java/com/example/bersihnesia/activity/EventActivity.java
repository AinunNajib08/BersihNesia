package com.example.bersihnesia.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.EventActivityAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.fragment.EventFragment;
import com.example.bersihnesia.listener.ItemClickSupport;
import com.example.bersihnesia.model.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bersihnesia.fragment.HomeFragment.STATE_EVENT;

public class EventActivity extends AppCompatActivity {

    ProgressBar progressBar;
    EditText searchBar;
    TextView tvNameEvent, tvCommunity, tvMode;
    RecyclerView rv_event;
    Context context;
    BaseApiService mApiService;
    ArrayList<Event> arrayList = new ArrayList<>();
    EventActivityAdapter eventAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        progressBar = findViewById(R.id.pBar);

        eventAdapter = new EventActivityAdapter(this);
        progressBar.setVisibility(View.GONE);
        searchBar = findViewById(R.id.search_event);
        rv_event = findViewById(R.id.rv_event);

        mApiService = UtilsApi.getAPIService();
        rv_event.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rv_event.setLayoutManager(layoutManager);
        getEvent();
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0){
                    arrayList.clear();
                    getEvent(s);
                }
                if (s.length() == 0){
                    arrayList.clear();
                    getEvent();
                }

            }
        });

        ItemClickSupport.addTo(rv_event).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(EventActivity.this, DetailEventActivity.class);
                intent.putExtra("data1", arrayList.get(position).getId_event());
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                final SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt(STATE_EVENT, arrayList.get(position).getId_event());
                editor.apply();
                Log.e("RAG", "onItemClicked: "+arrayList.get(position).getId_event() );
                startActivity(intent);
            }
        });
    }

    void getEvent(Editable cari){
        mApiService.getEventSearch(cari)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            progressBar.setVisibility(View.VISIBLE);
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray data = jsonRESULTS.getJSONArray("result");
                                for (int i=0; i <data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    int id_event = jsonObject.getInt("id_event");
                                    String name_event = jsonObject.getString("name_event");
                                    String desc = jsonObject.getString("description");
                                    Event event = new Event();
                                    event.setId_event(id_event);
                                    event.setName_event(name_event);
                                    event.setDescription(desc);
                                    arrayList.add(event);
                                    Log.e("RAG", "onResponse: "+arrayList );
                                }
                                eventAdapter.setListEvent(arrayList);
                                rv_event.setAdapter(eventAdapter);
                                progressBar.setVisibility(View.GONE);
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

    void getEvent(){
        progressBar.setVisibility(View.VISIBLE);
        mApiService.getEvent()
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                        if (response.isSuccessful()){
                            try {
                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                JSONArray data = jsonRESULTS.getJSONArray("result");
                                for (int i=0; i <data.length(); i++) {
                                    JSONObject jsonObject = data.getJSONObject(i);
                                    int id_event = jsonObject.getInt("id_event");
                                    String name_event = jsonObject.getString("name_event");
                                    String desc = jsonObject.getString("description");
                                    Event event = new Event();
                                    event.setId_event(id_event);
                                    event.setName_event(name_event);
                                    event.setDescription(desc);
                                    arrayList.add(event);
                                    Log.e("RAG", "onResponse: "+arrayList );
                                }
                                eventAdapter.setListEvent(arrayList);
                                rv_event.setAdapter(eventAdapter);
                                progressBar.setVisibility(View.GONE);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<ResponseBody> call,@NonNull Throwable t) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
}
