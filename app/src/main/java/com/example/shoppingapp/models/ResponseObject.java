package com.example.shoppingapp.models;

import java.util.List;

public class ResponseObject {
    private String status;
    private String messages;
    private List<Item> data;

    public ResponseObject(String status, String messages, List<Item> data) {
        this.status = status;
        this.messages = messages;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public List<Item> getData() {
        return data;
    }

    public void setData(List<Item> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseObject{" +
                "status='" + status + '\'' +
                ", messages='" + messages + '\'' +
                ", data=" + data +
                '}';
    }
}
