package com.example.bersihnesia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.DetailCommuntiyActivity;
import com.example.bersihnesia.model.SampahOrganik;


import java.util.List;

public class SampahOrganikAdapter extends RecyclerView.Adapter<SampahOrganikAdapter.MyViewHolder> {
    List<SampahOrganik> mSampahOrganik;
    Context context;

    public SampahOrganikAdapter(List<SampahOrganik>ListSampahOrganik, Context context){
        mSampahOrganik=ListSampahOrganik;
        this.context=context;

    }
    public SampahOrganikAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sampahorganik,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(SampahOrganikAdapter.MyViewHolder holder, final int posisi) {
        holder.title.setText(mSampahOrganik.get(posisi).getName_information());
        holder.description.setText(mSampahOrganik.get(posisi).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),DetailCommuntiyActivity.class);
                intent.putExtra("name_information",mSampahOrganik.get(posisi).getName_information());
                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mSampahOrganik.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,description;
        public ImageView photo;
        public MyViewHolder( View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.txt_title);
            description=itemView.findViewById(R.id.txt_description);
            photo=itemView.findViewById(R.id.photo);
        }
    }
}
