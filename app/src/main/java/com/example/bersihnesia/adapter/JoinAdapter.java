package com.example.bersihnesia.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.model.Community;

import java.util.List;

public class JoinAdapter extends RecyclerView.Adapter<JoinAdapter.MyViewHolder> {
    List<Community> mJoin;
    Context context;

    public  JoinAdapter(List<Community>JoinList,Context context){
        mJoin=JoinList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community_join,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder( MyViewHolder holder, int i) {
        holder.txt_title.setText(mJoin.get(i).getName_community());
        holder.txt_jumlah.setText(mJoin.get(i).getJumlah());

    }

    @Override
    public int getItemCount() {
        return mJoin.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_title,txt_jumlah;
        public MyViewHolder(View itemView) {
            super(itemView);
            txt_title=itemView.findViewById(R.id.txt_title);
            txt_jumlah=itemView.findViewById(R.id.txt_jumlah);
        }
    }
}
