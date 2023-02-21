package com.example.shoppingapp.models;

public class ShopItem {
    private String name;
    private double price;
    private String url;
    private double preview;
    private String description;

    public ShopItem(String name, double price, String url, double preview, String description) {
        this.name = name;
        this.price = price;
        this.url = url;
        this.preview = preview;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getPreview() {
        return preview;
    }

    public void setPreview(double preview) {
        this.preview = preview;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
