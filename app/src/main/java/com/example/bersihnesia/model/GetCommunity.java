package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCommunity {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Community> communityList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Community> getCommunityList() {
        return communityList;
    }

    public void setCommunityList(List<Community> communityList) {
        this.communityList = communityList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
