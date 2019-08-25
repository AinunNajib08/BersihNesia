package com.example.bersihnesia.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bersihnesia.R;
import com.example.bersihnesia.activity.DetailCommuntiyActivity;
import com.example.bersihnesia.fragment.EventFragment;
import com.example.bersihnesia.model.Event;

import java.util.ArrayList;

public class EventActivityAdapter extends RecyclerView.Adapter<EventActivityAdapter.GridViewHolder> {
    final Context context;
    private ArrayList<Event> listEvent;
    public ArrayList<Event> getListEvent() {
        return listEvent;
    }
    public void setListEvent(ArrayList<Event> listDataEvent) {
        this.listEvent = listDataEvent;
    }
    public EventActivityAdapter(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public EventActivityAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_acitivity_event, viewGroup, false);
        return new EventActivityAdapter.GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventActivityAdapter.GridViewHolder gridViewHolder, final int i) {
        gridViewHolder.tvNameEvent.setText(getListEvent().get(i).getName_event());
        gridViewHolder.tvDesc.setText(getListEvent().get(i).getDescription());
    }

    @Override
    public int getItemCount() {
        return (getListEvent() == null) ? 0 : getListEvent().size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        TextView tvDesc, tvNameEvent;
        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameEvent = itemView.findViewById(R.id.tvName);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }
    }
}
