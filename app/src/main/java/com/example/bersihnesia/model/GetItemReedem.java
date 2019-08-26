package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetItemReedem {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Reedem> reedemList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Reedem> getReedemList() {
        return reedemList;
    }

    public void setReedemList(List<Reedem> reedemList) {
        this.reedemList = reedemList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
