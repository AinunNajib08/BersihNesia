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
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.bersihnesia.R;
import com.example.bersihnesia.model.Event;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.GridViewHolder> {

    final Context context;
    private ArrayList<Event> listEvent;
    public ArrayList<Event> getListEvent() {
        return listEvent;
    }
    public void setListEvent(ArrayList<Event> listDataEvent) {
        this.listEvent = listDataEvent;
    }
    public EventAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_event, viewGroup, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridViewHolder gridViewHolder, int i) {
        Glide.with(context)
                .load("http://jwpdigitalent.com/gagas/upload/"+getListEvent().get(i).getPhoto())
                .apply(new RequestOptions().transform(new RoundedCorners(50)))
                .into(gridViewHolder.imgEvent);
        gridViewHolder.tvNameEvent.setText(getListEvent().get(i).getName_event());
    }

    @Override
    public int getItemCount()  {
        return (getListEvent() == null) ? 0 : getListEvent().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameEvent;
        ImageView imgEvent;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEvent = itemView.findViewById(R.id.imgEvent);
            tvNameEvent = itemView.findViewById(R.id.name_event);
        }
    }
}
