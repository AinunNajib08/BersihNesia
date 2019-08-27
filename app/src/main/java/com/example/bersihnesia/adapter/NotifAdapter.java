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

public class NotifAdapter extends RecyclerView.Adapter<NotifAdapter.MyViewHolder> {
    List<Reedem> mNotif;
    Context context;

    public NotifAdapter(List<Reedem> NotifList,Context context){
        mNotif=NotifList;
        this.context=context;

    }
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_notif_reedem, viewGroup, false);
        MyViewHolder myViewHolder=new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int item) {
        holder.txt_deskripsi.setText("Barang "+mNotif.get(item).getName_item()+" anda sedang kami proses, tunggu kabar dari kami selanjutnya");
        holder.txt_tanggal.setText(mNotif.get(item).getTanggal_reedem());

    }

    @Override
    public int getItemCount() {
        return mNotif.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txt_deskripsi,txt_tanggal;
        public ImageView photo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_deskripsi=itemView.findViewById(R.id.txt_deskripsi);
            txt_tanggal=itemView.findViewById(R.id.txt_tanggal);
            photo=itemView.findViewById(R.id.photo);

        }
    }
}
