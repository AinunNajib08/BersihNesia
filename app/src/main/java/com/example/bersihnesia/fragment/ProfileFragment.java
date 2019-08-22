package com.example.bersihnesia.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.LoginActivity;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {
SharedPreferences sharedPreferences;
TextView name,email,jk,no,address,photo;
ImageView img;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       // http://jwpdigitalent.com/gagas/upload/Screenshot_2019-08-06-23-23-40-25.png
        View view=inflater.inflate(R.layout.fragment_profile, container, false);
        sharedPreferences = view.getContext().getSharedPreferences("remember",Context.MODE_PRIVATE);
        String sNama = sharedPreferences.getString("name","0");
        String sNo = sharedPreferences.getString("no","1");
        String sEmail = sharedPreferences.getString("email","0");
        String sJk = sharedPreferences.getString("jk","0");
        String sAddress = sharedPreferences.getString("address","0");
        String sPhoto = sharedPreferences.getString("photo","0");
        name=view.findViewById(R.id.name);
        name.setText(sNama);
        email=view.findViewById(R.id.email);
        email.setText(sEmail);
        jk=view.findViewById(R.id.jk);
        jk.setText(sJk);
        no=view.findViewById(R.id.no);
        no.setText(sNo);
        address=view.findViewById(R.id.alamat);
        address.setText(sAddress);
        photo=view.findViewById(R.id.photo);
        photo.setText(sPhoto);
        img=view.findViewById(R.id.img);
        final String urlGambarBerita = "http://jwpdigitalent.com/gagas/upload/" +sPhoto;
        Picasso.with(getContext()).load(urlGambarBerita).into(img);


        return view;
    }

}
