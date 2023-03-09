package com.example.shoppingapp.models;

import java.util.List;

public class User {

    private Integer id;
    private String username;

    private String password;

    private String displayName;

    private String avatar;

    private String role;

    private List<Item> likedItems;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public java.lang.Object getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public java.lang.Object getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Item> getLikedItems() {
        return likedItems;
    }

    public void setLikedItems(List<Item> likedItems) {
        this.likedItems = likedItems;
    }

}