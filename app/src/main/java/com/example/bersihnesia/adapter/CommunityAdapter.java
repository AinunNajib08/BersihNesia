package com.example.bersihnesia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.DetailCommuntiyActivity;
import com.example.bersihnesia.model.Community;

import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.MyViewHolder> {
    List<Community> mCommunity;
    Context context;

    public CommunityAdapter(List<Community>CommunityList, Context context){
        mCommunity=CommunityList;
        this.context=context;

    }
    public CommunityAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView=LayoutInflater.from(parent.getContext()).inflate(R.layout.item_community,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(mView);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(CommunityAdapter.MyViewHolder holder, final int posisi) {
        holder.title.setText(mCommunity.get(posisi).getName_community());
        holder.description.setText(mCommunity.get(posisi).getDescription());
        Glide.with(context)
                .load("http://jwpdigitalent.com/gagas/upload/"+mCommunity.get(posisi).getPhoto())
                .placeholder(R.drawable.ic_banner)
                .apply(new RequestOptions().transform(new RoundedCorners(50)))
                .into(holder.photo);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),DetailCommuntiyActivity.class);
                intent.putExtra("id_com", mCommunity.get(posisi).getId_community());
                intent.putExtra("photo", mCommunity.get(posisi).getPhoto());
                intent.putExtra("name_community",mCommunity.get(posisi).getName_community());
                v.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return mCommunity.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,description;
        public ImageView photo;
        public MyViewHolder( View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.txt_title);
            description=itemView.findViewById(R.id.txt_description);
            photo=itemView.findViewById(R.id.i_community);

        }
    }
}
