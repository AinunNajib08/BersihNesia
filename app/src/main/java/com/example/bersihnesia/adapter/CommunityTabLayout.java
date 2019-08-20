package com.example.bersihnesia.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bersihnesia.fragment.c_tablayout.AllEvent;
import com.example.bersihnesia.fragment.c_tablayout.EventCommunity;
import com.example.bersihnesia.fragment.c_tablayout.InformationCommunity;

public class CommunityTabLayout extends FragmentPagerAdapter {
    private Context mContex;
    public CommunityTabLayout(FragmentManager fm, Context context) {
        super(fm);
        mContex = context;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0){
            return new AllEvent();
        } else if (i == 1) {
            return new EventCommunity();
        }
        return new InformationCommunity();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position){
        if (position == 0 ){
            return "Semua";
        } else if (position == 1) {
            return "Event";
        }
        return "Pengumuman";
    }

    @Override
    public int getCount() {
        return 3;
    }
}
