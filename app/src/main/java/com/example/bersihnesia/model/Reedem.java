package com.example.bersihnesia.model;

public class Reedem {
    int id_item;
    String name_item,jumlah_point,jumlah_item;

    public Reedem(int id_item, String name_item, String jumlah_point, String jumlah_item) {
        this.id_item = id_item;
        this.name_item = name_item;
        this.jumlah_point = jumlah_point;
        this.jumlah_item = jumlah_item;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }



    public String getName_item() {
        return name_item;
    }

    public void setName_item(String name_item) {
        this.name_item = name_item;
    }

    public String getJumlah_point() {
        return jumlah_point;
    }

    public void setJumlah_point(String jumlah_point) {
        this.jumlah_point = jumlah_point;
    }

    public String getJumlah_item() {
        return jumlah_item;
    }

    public void setJumlah_item(String jumlah_item) {
        this.jumlah_item = jumlah_item;
    }
}
