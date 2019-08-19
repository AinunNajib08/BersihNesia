package com.example.bersihnesia.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.EventAdapter;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.listener.ItemClickSupport;
import com.example.bersihnesia.model.Event;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

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
public class HomeFragment extends Fragment implements LocationListener {


    public HomeFragment() {
        // Required empty public constructor
    }

    private int[] mImage = new int[]{
            R.drawable.ic_banner, R.drawable.ic_banner
    };

    private String[] mImagesTitle = new String[]{
            "Banner Ads", "Banner Ads 2"
    };

    private LocationManager locationManager;
    private BaseApiService mApiService;
    private ArrayList<Event> arrayList = new ArrayList<>();
    RecyclerView rv_event;
    Context mContext;
    public static final String EXTRA_MOVIE = "arrayList";
    public static final String STATE_EVENT = "state_event";
    EventAdapter eventAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        CarouselView carouselView = view.findViewById(R.id.slideImage);
        carouselView.setPageCount(mImage.length);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        boolean permissionGranted = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;

        if(permissionGranted) {
            Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            onLocationChanged(location);
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
        }

        // For Event
        mContext = getContext();
        eventAdapter = new EventAdapter(mContext);
        mApiService = UtilsApi.getAPIService();
        rv_event = view.findViewById(R.id.rv_event);
        rv_event.setHasFixedSize(true);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv_event.setLayoutManager(layoutManager);
        if (savedInstanceState==null){
            getEvent();
        } else {
            arrayList = savedInstanceState.getParcelableArrayList(EXTRA_MOVIE);
            eventAdapter.setListEvent(arrayList);
            rv_event.setAdapter(eventAdapter);
        }
        ItemClickSupport.addTo(rv_event).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                EventFragment nextFrag= new EventFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, nextFrag, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });




        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImage[position]);
            }
        });
        return view;
    }

    @Override
    public void onLocationChanged(Location location) {

        double latiounde = location.getLatitude();
        double asd = location.getLongitude();

        Location loc1 = new Location("");
        loc1.setLatitude(latiounde);
        loc1.setLongitude(asd);

        Location loc2 = new Location("");
        loc2.setLatitude(-8.174230);
        loc2.setLongitude(113.718665);

        float distanceInMeters = loc1.distanceTo(loc2);

        String tes = String.valueOf(distanceInMeters * 0.001);
        Log.e("RAG", "onCreateView: "+tes );

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    void getEvent(){
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
                                    Event event = new Event();
                                    event.setId_event(id_event);
                                    event.setName_event(name_event);
                                    arrayList.add(event);
                                }
                                eventAdapter.setListEvent(arrayList);
                                rv_event.setAdapter(eventAdapter);
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
                    }
                });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putParcelableArrayList(EXTRA_MOVIE, new ArrayList<>(eventAdapter.getListEvent()));
        super.onSaveInstanceState(outState);
    }
}
