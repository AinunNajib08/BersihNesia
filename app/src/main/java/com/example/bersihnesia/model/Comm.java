package com.example.bersihnesia.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Comm implements Parcelable {
    int id_community;
    String name_community, description;

    public int getId_community() {
        return id_community;
    }

    public void setId_community(int id_community) {
        this.id_community = id_community;
    }

    public String getName_community() {
        return name_community;
    }

    public void setName_community(String name_community) {
        this.name_community = name_community;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id_community);
        dest.writeString(this.name_community);
        dest.writeString(this.description);
    }

    public Comm() {
    }

    protected Comm(Parcel in) {
        this.id_community = in.readInt();
        this.name_community = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Comm> CREATOR = new Parcelable.Creator<Comm>() {
        @Override
        public Comm createFromParcel(Parcel source) {
            return new Comm(source);
        }

        @Override
        public Comm[] newArray(int size) {
            return new Comm[size];
        }
    };
}
