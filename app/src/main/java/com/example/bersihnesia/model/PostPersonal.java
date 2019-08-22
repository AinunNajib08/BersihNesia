package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

public class PostPersonal {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("message")
    String message;
    @SerializedName("email")
    String email;
    @SerializedName("jk")
    String jk;
    @SerializedName("id_personal")
    String id_personal;
    @SerializedName("contact_person")
    String contact_person;
    @SerializedName("name")
    String name;
    @SerializedName("point")
    String point;
    @SerializedName("address")
    String address;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @SerializedName("photo")
    String photo;

    public String getJk() {
        return jk;
    }

    public void setJk(String jk) {
        this.jk = jk;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId_personal() {
        return id_personal;
    }

    public void setId_personal(String id_personal) {
        this.id_personal = id_personal;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
