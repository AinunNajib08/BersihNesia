package com.example.bersihnesia.fragment.tablayout;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.fragment.HomeFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.bersihnesia.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class LokasiFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap mMap;
    Geocoder geocoder;
    TextView tvLocation;
    List<Address> addresses;
    String value;
    public LokasiFragment() {
        // Required empty public constructor
    }

    String data;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment\

        View view = inflater.inflate(R.layout.fragment_lokasi, container, false);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int Hallo = sharedPreferences.getInt(HomeFragment.STATE_EVENT, 0);
        Log.e("RAG", "asds: "+Hallo );

        Log.e("RAG", "onCreateView: " + value );
        tvLocation = view.findViewById(R.id.tvLocation);
        geocoder = new Geocoder(getActivity(), Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(-7.917427, 113.828638, 1);
            String address = addresses.get(0).getAddressLine(0);
            data = address;
            tvLocation.setText(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mapView = (MapView) view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-7.917427, 113.828638);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        float zoom = 16.0f;
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(sydney, zoom);
        mMap.animateCamera(cu);

    }

}