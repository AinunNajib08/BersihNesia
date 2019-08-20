package com.example.bersihnesia.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.bersihnesia.R;

public class CreateCommunityActivity extends AppCompatActivity {
TextView pdf_name;
ImageButton btn_pilih;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_community);
        pdf_name=findViewById(R.id.pdf_name);
        Intent mIntent=getIntent();
        pdf_name.setText(mIntent.getStringExtra("pdf_name"));
        btn_pilih=findViewById(R.id.btn_pilih);
        btn_pilih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(CreateCommunityActivity.this,PdfActivity.class);
                startActivity(intent);
            }
        });

    }
}
