package com.example.bersihnesia.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.model.Comm;

import java.util.ArrayList;

public class CommAdapter extends RecyclerView.Adapter<CommAdapter.GridHolder>  {

    final Context context;
    private ArrayList<Comm> listComm;
    public ArrayList<Comm> getListComm() {
        return listComm;
    }
    public void setListEvent(ArrayList<Comm> listDataComm) {
        this.listComm = listDataComm;
    }
    public CommAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_comm, viewGroup, false);
        return new CommAdapter.GridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder gridHolder, int i) {
        gridHolder.tv_nameCommunity.setText(getListComm().get(i).getName_community());
    }

    @Override
    public int getItemCount() {
        return (getListComm() == null) ? 0 : getListComm().size();
    }

    public class GridHolder extends RecyclerView.ViewHolder {
        TextView tv_nameCommunity, tv_Description;
        public GridHolder(@NonNull View itemView) {
            super(itemView);
            tv_nameCommunity = itemView.findViewById(R.id.name_community);
            tv_Description = itemView.findViewById(R.id.description);
        }
    }
}
