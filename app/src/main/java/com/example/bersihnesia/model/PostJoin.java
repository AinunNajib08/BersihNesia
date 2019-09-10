package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostJoin {
    String status;
    @SerializedName("result")
    List<Community> joinList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Community> getJoinList() {
        return joinList;
    }

    public void setJoinList(List<Community> joinList) {
        this.joinList = joinList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
