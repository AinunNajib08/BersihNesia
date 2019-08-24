package com.example.bersihnesia.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.bersihnesia.R;
import com.example.bersihnesia.model.Event;
import com.example.bersihnesia.model.Member;

import java.util.ArrayList;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.GridHolder> {

    final Context context;
    private ArrayList<Member> listMember;
    public ArrayList<Member> getListMember() {
        return listMember;
    }
    public void setListMember(ArrayList<Member> listMember) {
        this.listMember = listMember;
    }
    public MemberAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_member, viewGroup, false);
        return new MemberAdapter.GridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder gridHolder, int i) {
        gridHolder.tvName.setText(getListMember().get(i).getName());
        gridHolder.tvDesc.setText(getListMember().get(i).getAddress());
        Glide.with(context)
                .load("http://jwpdigitalent.com/gagas/upload/"+getListMember().get(i).getPhoto())
                .placeholder(R.drawable.ic_banner)
                .apply(RequestOptions.circleCropTransform())
                .into(gridHolder.imgProfile);
    }

    @Override
    public int getItemCount() {
        return getListMember().size();
    }

    public class GridHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDesc;
        ImageView imgProfile;
        public GridHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvDesc = itemView.findViewById(R.id.address);
            imgProfile = itemView.findViewById(R.id.imgMember);
        }
    }
}
