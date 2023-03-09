package com.example.shoppingapp.models;

import java.util.List;

public class ResponseComments {
    private String status;
    private String message;
    private List<Comment> data;

    public ResponseComments(String status, String message, List<Comment> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseComments() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Comment> getData() {
        return data;
    }

    public void setData(List<Comment> data) {
        this.data = data;
    }
}
