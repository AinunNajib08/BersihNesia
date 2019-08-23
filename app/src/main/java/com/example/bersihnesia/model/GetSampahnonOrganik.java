package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetSampahnonOrganik {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<SampahnonOrganik> sampahnonOrganikList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<SampahnonOrganik> getSampahnonOrganikList() {
        return sampahnonOrganikList;
    }

    public void setSampahnonOrganikList(List<SampahnonOrganik> sampahnonOrganikList) {
        this.sampahnonOrganikList = sampahnonOrganikList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
