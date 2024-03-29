package com.example.bersihnesia.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.bersihnesia.fragment.c_tablayout.AllEvent;
import com.example.bersihnesia.fragment.c_tablayout.EventCommunity;
import com.example.bersihnesia.fragment.c_tablayout.InformationCommunity;
import com.example.bersihnesia.fragment.tablayout.LokasiFragment;
import com.example.bersihnesia.fragment.tablayout.MoreFragment;
import com.example.bersihnesia.fragment.tablayout.RandomFragment;

import java.util.ArrayList;
import java.util.List;

public class EventTabLayout extends FragmentPagerAdapter {
    private Context mContex;
    public EventTabLayout(FragmentManager fm, Context context) {
        super(fm);
        mContex = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new LokasiFragment();
        } else if (i == 1) {
            return new RandomFragment();
        }
        return new MoreFragment();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0 ){
            return "Lokasi";
        } else if (position == 1) {
            return "Randon Acara";
        }
        return "Lainnya";
    }

    @Override
    public int getCount() {
        return 3;
    }
}
