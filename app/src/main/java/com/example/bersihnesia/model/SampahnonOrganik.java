package com.example.bersihnesia.model;

import java.sql.Blob;

public class SampahnonOrganik {
    public String name_information, contact_person, description, date, value;
    public Blob photo;

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

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public SampahnonOrganik(String name_information, String description, Blob photo, String date, String value) {
        this.name_information = name_information;
        this.contact_person = description;
        this.photo = photo;
        this.date = date;
        this.value = value;
    }
}
