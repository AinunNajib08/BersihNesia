package com.example.bersihnesia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class EventJoin  {
    private int id_event, id_community;
    private String name_event, photo, description, address, time_date,  status_event;
    String date;
    String longlat;

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public int getId_community() {
        return id_community;
    }

    public void setId_community(int id_community) {
        this.id_community = id_community;
    }

    public String getName_event() {
        return name_event;
    }

    public void setName_event(String name_event) {
        this.name_event = name_event;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTime_date() {
        return time_date;
    }

    public void setTime_date(String time_date) {
        this.time_date = time_date;
    }

    public String getStatus_event() {
        return status_event;
    }

    public void setStatus_event(String status_event) {
        this.status_event = status_event;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLonglat() {
        return longlat;
    }

    public void setLonglat(String longlat) {
        this.longlat = longlat;
    }

    public EventJoin(int id_event, int id_community, String name_event, String photo, String description, String address, String time_date, String status_event, String date, String longlat) {
        this.id_event = id_event;
        this.id_community = id_community;
        this.name_event = name_event;
        this.photo = photo;
        this.description = description;
        this.address = address;
        this.time_date = time_date;
        this.status_event = status_event;
        this.date = date;
        this.longlat = longlat;
    }
}
