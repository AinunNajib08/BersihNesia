package com.example.bersihnesia.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.bersihnesia.fragment.tablayout.LokasiFragment;
import com.example.bersihnesia.fragment.tablayout.MoreFragment;
import com.example.bersihnesia.fragment.tablayout.RandomFragment;

import java.util.ArrayList;
import java.util.List;

public class EventTabLayout extends FragmentPagerAdapter {
    private Context mContex;
    int ajd;
    List<Fragment> fragments = new ArrayList<>();
    public EventTabLayout(FragmentManager fm, Context context) {
        super(fm);
        mContex = context;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new LokasiFragment();
                break;
                case 1:
                    fragment = new RandomFragment();
                    break;
            case 2:
                fragment = new MoreFragment();
                break;
        }
        return fragment;
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
