package com.example.bersihnesia.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.EventTabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventFragment extends Fragment {


    public EventFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false);
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

}
