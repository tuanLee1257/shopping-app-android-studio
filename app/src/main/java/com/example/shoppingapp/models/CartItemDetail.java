package com.example.shoppingapp.models;

import java.util.List;

public class CartItemDetail {
    private Item item;
    private Integer quantity;

    public CartItemDetail(Item item, Integer quantity) {
        this.item = item;
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
