package com.example.bersihnesia.fragment.tablayout;

import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.bersihnesia.apihelper.BaseApiService;
import com.example.bersihnesia.apihelper.UtilsApi;
import com.example.bersihnesia.fragment.HomeFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.bersihnesia.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LokasiFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap mMap;
    Geocoder geocoder;
    TextView tvLocation, tvDesc;
    List<Address> addresses;
    String value;
    String longlat;
    BaseApiService mApiService;
    String[] latlong;
    ProgressBar progressBar;
    public LokasiFragment() {
        // Required empty public constructor
    }

    String data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lokasi, container, false);
        mApiService = UtilsApi.getAPIService();
        progressBar = view.findViewById(R.id.progBar);
        progressBar.setVisibility(View.GONE);
        tvDesc = view.findViewById(R.id.tvDesc);
        getDataEvent();
        tvLocation = view.findViewById(R.id.tvLocation);
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        return view;
    }

    public void openMap(){
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    private void getDataEvent(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int Hallo = sharedPreferences.getInt(HomeFragment.STATE_EVENT, 0);
        Log.e("RAG", "getDataEvent: "+Hallo );
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
                                    longlat = jsonObject.getString("longlat");
                                    String desc = jsonObject.getString("description");
                                    tvDesc.setText(desc);
                                    latlong =  longlat.split(",");
                                    openMap();
                                }
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latitude = Double.parseDouble(latlong[0]);
        double longitude = Double.parseDouble(latlong[1]);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            String address = addresses.get(0).getAddressLine(0);
            data = address;
            tvLocation.setText(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        LatLng sydney = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Lokasi di Tetapkan"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        float zoom = 16.0f;
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(sydney, zoom);
        mMap.animateCamera(cu);
        progressBar.setVisibility(View.GONE);

    }

}