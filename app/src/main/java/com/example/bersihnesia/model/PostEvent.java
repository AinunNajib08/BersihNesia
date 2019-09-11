package com.example.bersihnesia.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostEvent {
    String status;
    @SerializedName("result")
    List<EventJoin> eventList;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<EventJoin> getEventList() {
        return eventList;
    }

    public void setEventList(List<EventJoin> eventList) {
        this.eventList = eventList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
