package com.example.bersihnesia.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Blob;

public class SampahnonOrganik implements Parcelable {
    public String getName_information() {
        return name_information;
    }

    public void setName_information(String name_information) {
        this.name_information = name_information;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String name_information, contact_person, description, photo, date, value;


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name_information);
        dest.writeString(this.contact_person);
        dest.writeString(this.description);
        dest.writeString(this.photo);
        dest.writeString(this.date);
        dest.writeString(this.value);
    }

    public SampahnonOrganik() {
    }

    protected SampahnonOrganik(Parcel in) {
        this.name_information = in.readString();
        this.contact_person = in.readString();
        this.description = in.readString();
        this.photo = in.readString();
        this.date = in.readString();
        this.value = in.readString();
    }

    public static final Parcelable.Creator<SampahnonOrganik> CREATOR = new Parcelable.Creator<SampahnonOrganik>() {
        @Override
        public SampahnonOrganik createFromParcel(Parcel source) {
            return new SampahnonOrganik(source);
        }

        @Override
        public SampahnonOrganik[] newArray(int size) {
            return new SampahnonOrganik[size];
        }
    };
}
