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
    @SerializedName("description")
    String description;
    @SerializedName("longlat")
    String longlat;
    @SerializedName("latlong")
    String latlong;
    @SerializedName("name_community")
    String name_community;
    @SerializedName("legality")
    String legality;
    @SerializedName("photo")
    String photo;
    @SerializedName("nama")
    String nama;
    @SerializedName("id_item")
    int id_item;
    @SerializedName("no_hp")
    String no_hp;
    @SerializedName("alamat_pengiriman")
    String alamat_pengiriman;
    @SerializedName("reedem_point")
    int reedem_point;


    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getAlamat_pengiriman() {
        return alamat_pengiriman;
    }

    public void setAlamat_pengiriman(String alamat_pengiriman) {
        this.alamat_pengiriman = alamat_pengiriman;
    }

    public int getReedem_point() {
        return reedem_point;
    }

    public void setReedem_point(int reedem_point) {
        this.reedem_point = reedem_point;
    }

    public String getLatlong() {
        return latlong;
    }

    public void setLatlong(String latlong) {
        this.latlong = latlong;
    }

    public String getLonglat() {
        return longlat;
    }

    public void setLonglat(String longlat) {
        this.longlat = longlat;
    }

    public String getName_community() {
        return name_community;
    }

    public void setName_community(String name_community) {
        this.name_community = name_community;
    }

    public String getLegality() {
        return legality;
    }

    public void setLegality(String legality) {
        this.legality = legality;
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
