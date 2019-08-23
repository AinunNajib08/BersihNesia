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
import com.example.bersihnesia.model.SampahnonOrganik;

import java.util.List;


public class SampahnonOrganikAdapter extends RecyclerView.Adapter<SampahnonOrganikAdapter.MyViewHolder> {
    List<SampahnonOrganik> mSampahnonOrganik;
    Context context;
    public SampahnonOrganikAdapter(List<SampahnonOrganik>ListSampahnonOrganik, Context context){
        mSampahnonOrganik=ListSampahnonOrganik;
        this.context=context;
    }
    public SampahnonOrganikAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sampahorganik,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(mView);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(SampahnonOrganikAdapter.MyViewHolder holder, final int posisi) {
        holder.name_information.setText(mSampahnonOrganik.get(posisi).getName_information());
        holder.description.setText(mSampahnonOrganik.get(posisi).getDescription());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),DetailCommuntiyActivity.class);
                intent.putExtra("name_information",mSampahnonOrganik.get(posisi).getName_information());
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mSampahnonOrganik.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_information,description;
        public ImageView photo;
        public MyViewHolder( View itemView) {
            super(itemView);
            name_information=itemView.findViewById(R.id.name_information);
            description=itemView.findViewById(R.id.description);
            photo=itemView.findViewById(R.id.photo_sampah);
        }
    }
}