package com.example.shoppingapp.models;

import java.util.List;

public class CartItemDetail {
    private Item item;
    private Integer quantity;

    public CartItemDetail(Item item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}
