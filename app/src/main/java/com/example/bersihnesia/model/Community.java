package com.example.bersihnesia.model;

import com.google.android.gms.maps.model.LatLng;

public class Community {
    public int getId_community() {
        return id_community;
    }

    public void setId_community(int id_community) {
        this.id_community = id_community;
    }

    int id_community;
    public String name_community;
    public String jumlah;
    public String contact_person;
    public String description;
    public String photo;
    public String date;
    public String address;
    public String latlong;


    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getDate() {
        return date;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String time;

    public String getName_community() {
        return name_community;
    }

    public void setName_community(String name_community) {
        this.name_community = name_community;
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

    public Community(int id_community, String name_community, String contact_person, String description, String photo, String date, String address, String latlong, String time) {
        this.id_community = id_community;
        this.name_community = name_community;
        this.contact_person = contact_person;
        this.description = description;
        this.photo = photo;
        this.date = date;
        this.address = address;
        this.latlong = latlong;
        this.time = time;
    }
}
