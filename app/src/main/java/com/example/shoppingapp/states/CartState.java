package com.example.shoppingapp.states;

import android.app.Application;

import com.example.shoppingapp.models.ShopItem;

import java.util.ArrayList;
import java.util.List;

public class CartState extends Application {
    private List<ShopItem> shopItems = new ArrayList<>();

    public List<ShopItem> getShopItems() {
        return shopItems;
    }

    public void setShopItems(List<ShopItem> shopItems) {
        this.shopItems = shopItems;
    }

    public void add(ShopItem shopItem){
        this.shopItems.add(shopItem);
    }

    public void remove(int position){
        this.shopItems.remove(position);
    }
}
