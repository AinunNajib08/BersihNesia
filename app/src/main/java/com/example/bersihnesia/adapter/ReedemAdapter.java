package com.example.bersihnesia.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.ReedemPointActivity;
import com.example.bersihnesia.model.Reedem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ReedemAdapter extends RecyclerView.Adapter<ReedemAdapter.MyViewHolder>  {
    List<Reedem> mReedem;
    Context context;
    int sum;
    String  kon;
    ImageButton kurang;
    TextView txt_jumlah,txt_total,my_point;
    SharedPreferences sharedPreferences;

    public ReedemAdapter(List<Reedem> ReedemList, Context context) {
        mReedem = ReedemList;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_reedem, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(mView);
        sharedPreferences = context.getSharedPreferences("remember", Context.MODE_PRIVATE);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int item) {
        final String urlGambarBerita = "http://jwpdigitalent.com/gagas/upload/" + mReedem.get(item).getPhoto();
        // Set image ke widget dengna menggunakan Library Piccasso
        // krena imagenya dari internet
        Picasso.with(context).load(urlGambarBerita).into(holder.photo);
        holder.name_item.setText(mReedem.get(item).getName_item());
        holder.jumlah_point.setText(mReedem.get(item).getJumlah_point() + " pt");
        holder.jumlah_item.setText(mReedem.get(item).getJumlah_item() + " pcs");
        holder.reedem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putInt("id_item",mReedem.get(item).getId_item());
                editor.putString("photo",mReedem.get(item).getPhoto());
                editor.putString("jmlh" ,mReedem.get(item).getJumlah_point());
                editor.putString("name_item" ,mReedem.get(item).getName_item());
                editor.apply();
                showDialog();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mReedem.size();
    }

    public FragmentManager getFragmentManager() {
        return getFragmentManager();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name_item, jumlah_point, jumlah_item;
        public ImageView photo;
        public Button reedem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name_item = itemView.findViewById(R.id.name_item);
            jumlah_item = itemView.findViewById(R.id.jumlah_item);
            jumlah_point = itemView.findViewById(R.id.jumlah_point);
            photo = itemView.findViewById(R.id.photo);
            reedem = itemView.findViewById(R.id.reedem);

        }
    }

    private void showDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_reedem);

        txt_jumlah = dialog.findViewById(R.id.jumlah);
        final int item = 0;
        final String sPoint = sharedPreferences.getString("point", "1");
        String sJumlah = sharedPreferences.getString("jmlh", "2");

        my_point = dialog.findViewById(R.id.point);
        my_point.setText(sPoint + "pt");
        txt_jumlah.setText(sJumlah + "pt");
        Double v1 = Double.parseDouble(sPoint);
        Double v2 = Double.parseDouble(sJumlah);
        final boolean kon= v2 > v1;
        Button iya=dialog.findViewById(R.id.iya);
        iya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kon==true){
                    Toast.makeText(context,"Point anda kurang!",Toast.LENGTH_SHORT).show();

                }
                else {
                    Intent intent=new Intent(context, ReedemPointActivity.class);
                    context.startActivity(intent);
                    dialog.dismiss();
                }
            }
        });
        dialog.show();

    }


}
