package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostNotif {

    @SerializedName("id_personal")
    String id_personal;
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Reedem> notifList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reedem> getNotifList() {
        return notifList;
    }

    public void setNotifList(List<Reedem> notifList) {
        this.notifList = notifList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId_personal() {
        return id_personal;
    }

    public void setId_personal(String id_personal) {
        this.id_personal = id_personal;
    }
}
