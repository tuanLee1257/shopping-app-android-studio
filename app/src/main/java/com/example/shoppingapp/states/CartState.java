package com.example.shoppingapp.states;

import android.app.Application;

import com.example.shoppingapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class CartState extends Application {
    private List<Item> items = new ArrayList<>();

    public List<Item> getShopItems() {
        return items;
    }

    public void setShopItems(List<Item> items) {
        this.items = items;
    }

    public void add(Item item){
        this.items.add(item);
    }

    public void remove(int position){
        this.items.remove(position);
    }
}
