package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

public class PostPersonal {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    String result;
    @SerializedName("message")
    String message;

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
