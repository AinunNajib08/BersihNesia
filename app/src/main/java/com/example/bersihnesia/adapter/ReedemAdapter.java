package com.example.bersihnesia.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.model.Reedem;

import java.util.List;

public class ReedemAdapter extends RecyclerView.Adapter<ReedemAdapter.MyViewHolder> {
List<Reedem> mReedem;
Context context;

public ReedemAdapter(List<Reedem>ReedemList,Context context){
    mReedem=ReedemList;
    this.context=context;

}
    @NonNull
    @Override
    public ReedemAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reedem,viewGroup,false);
        MyViewHolder myViewHolder=new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ReedemAdapter.MyViewHolder holder, int item) {
    holder.name_item.setText(mReedem.get(item).getName_item());
    holder.jumlah_point.setText(mReedem.get(item).getJumlah_point()+" pt");
    holder.jumlah_item.setText(mReedem.get(item).getJumlah_item()+" pcs");
    }

    @Override
    public int getItemCount() {
        return mReedem.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_item,jumlah_point,jumlah_item;
        public ImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_item=itemView.findViewById(R.id.name_item);
            jumlah_item=itemView.findViewById(R.id.jumlah_item);
            jumlah_point=itemView.findViewById(R.id.jumlah_point);
            photo=itemView.findViewById(R.id.photo);

        }
    }
}
