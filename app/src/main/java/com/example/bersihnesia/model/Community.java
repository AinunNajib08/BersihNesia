package com.example.bersihnesia.model;

public class Community {
    public int getId_community() {
        return id_community;
    }

    public void setId_community(int id_community) {
        this.id_community = id_community;
    }

    int id_community;
    public String name_community;
    public String contact_person;
    public String description;
    public String photo;
    public String date;

    public String getDate() {
        return date;
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

    public Community(String name_community, String contact_person, String description, String photo) {
        this.name_community = name_community;
        this.contact_person = contact_person;
        this.description = description;
        this.photo = photo;
    }
}
