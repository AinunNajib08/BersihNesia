package com.example.bersihnesia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.adapter.CommunityTabLayout;
import android.util.Log;

public class DetailEventActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event);
        TabLayout tabLayout = findViewById(R.id.favorite_tab_layout);
        ViewPager viewPager = findViewById(R.id.favorite_viewpager);
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(new CommunityTabLayout(getSupportFragmentManager(), this));

        tabLayout.setupWithViewPager(viewPager);

        Intent intent=getIntent();
        Bundle bundle = getIntent().getExtras();
        bundle.getInt("data1");
        Log.e("RAG", "onCreate: "+bundle.getInt("data1"));
    }
}
