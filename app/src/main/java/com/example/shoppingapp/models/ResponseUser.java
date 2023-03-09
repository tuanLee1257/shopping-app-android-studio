package com.example.shoppingapp.models;

public class ResponseUser {
    private String status;
    private String messages;
    private User data;

    public ResponseUser(String status, String messages, User data) {
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

    public User getData() {
        return data;
    }

    public void setData(User data) {
        this.data = data;
    }


}
