package com.example.bersihnesia.model;

public class Reedem {
    int id_item;
    String id_personal,id_act,nama,no_hp,alamat_pengiriman,tanggal_reedem,name_item,jumlah_point,jumlah_item,photo;


    public Reedem(int id_item, String id_personal, String id_act, String nama, String no_hp, String alamat_pengiriman, String tanggal_reedem, String name_item, String jumlah_point, String jumlah_item, String photo) {
        this.id_item = id_item;
        this.id_personal = id_personal;
        this.id_act = id_act;
        this.nama = nama;
        this.no_hp = no_hp;
        this.alamat_pengiriman = alamat_pengiriman;
        this.tanggal_reedem = tanggal_reedem;
        this.name_item = name_item;
        this.jumlah_point = jumlah_point;
        this.jumlah_item = jumlah_item;
        this.photo = photo;
    }


    public String getId_personal() {
        return id_personal;
    }

    public void setId_personal(String id_personal) {
        this.id_personal = id_personal;
    }

    public String getId_act() {
        return id_act;
    }

    public void setId_act(String id_act) {
        this.id_act = id_act;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
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

    public String getTanggal_reedem() {
        return tanggal_reedem;
    }

    public void setTanggal_reedem(String tanggal_reedem) {
        this.tanggal_reedem = tanggal_reedem;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
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
