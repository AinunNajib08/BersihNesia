package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSampahOrganik {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<SampahOrganik> sampahOrganikList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SampahOrganik> getSampahOrganikList() {
        return sampahOrganikList;
    }

    public void setSampahOrganikList(List<SampahOrganik> sampahOrganikList) {
        this.sampahOrganikList = sampahOrganikList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
