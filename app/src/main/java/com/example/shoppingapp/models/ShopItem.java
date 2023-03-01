package com.example.shoppingapp.models;


import java.io.Serializable;

public class ShopItem implements Serializable {

    private Integer id;

    private String itemName;

    private String description;

    private String imgUrl;

    private Double price;

    private Object likedBy;

    public ShopItem(String itemName, String description, String imgUrl, Double price) {
        this.itemName = itemName;
        this.description = description;
        this.imgUrl = imgUrl;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Object getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(Object likedBy) {
        this.likedBy = likedBy;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", price=" + price +
                ", likedBy=" + likedBy +
                '}';
    }
}


