package com.example.bersihnesia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Event implements Parcelable {
    private int id_event, id_community;
    private String name_event, photo, description, address, time_date, longlat, status_event;

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

    public String getLonglat() {
        return longlat;
    }

    public void setLonglat(String longlat) {
        this.longlat = longlat;
    }

    public String getStatus_event() {
        return status_event;
    }

    public void setStatus_event(String status_event) {
        this.status_event = status_event;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id_event);
        dest.writeInt(this.id_community);
        dest.writeString(this.name_event);
        dest.writeString(this.photo);
        dest.writeString(this.description);
        dest.writeString(this.address);
        dest.writeString(this.time_date);
        dest.writeString(this.longlat);
        dest.writeString(this.status_event);
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.id_event = in.readInt();
        this.id_community = in.readInt();
        this.name_event = in.readString();
        this.photo = in.readString();
        this.description = in.readString();
        this.address = in.readString();
        this.time_date = in.readString();
        this.longlat = in.readString();
        this.status_event = in.readString();
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
}
