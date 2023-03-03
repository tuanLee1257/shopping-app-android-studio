package com.example.shoppingapp.states;

import android.app.Application;
import android.util.Log;

import com.example.shoppingapp.models.CartItemDetail;
import com.example.shoppingapp.models.Item;

import java.util.ArrayList;
import java.util.List;

public class CartState extends Application {
//    public List<Item> items = new ArrayList<>();
//
//    public List<Item> getShopItems() {
//        return items;
//    }
//
//    public void setShopItems(List<Item> items) {
//        this.items = items;
//    }
//
//    public void add(Item item){
//        this.items.add(item);
//    }
//
//    public void remove(int position){
//        this.items.remove(position);
//    }


    private List<CartItemDetail> items = new ArrayList<>();

    public List<CartItemDetail> getShopItems() {
        return this.items;
    }

    public void add(Item item) {
        CartItemDetail addItemToCart = new CartItemDetail(item, 1);
        if (items.isEmpty()) {
            items.add(addItemToCart);
        } else {
            boolean isExist = false;
            for (CartItemDetail cartItemDetail : items) {
                if (cartItemDetail.getItem() == item) {
                    Log.e("TAG", "add: " + cartItemDetail.getItem());
                    Log.e("TAG", "add: " + item);
                    isExist = true;
                    cartItemDetail.setQuantity(cartItemDetail.getQuantity() + 1);
                    break;
                }
                isExist = false;
            }
            Log.e("TAG", "add: " + isExist);
            if (!isExist) {
                items.add(addItemToCart);
            }
        }

    }

    public void remove(int position) {
        items.remove(position);

    }

    public void inscrease(int position) {
        CartItemDetail item = this.getShopItems().get(position);
        item.setQuantity(item.getQuantity() + 1);
    }

    public void descrease(int position) {
        CartItemDetail item = this.getShopItems().get(position);
        if (item.getQuantity() > 1){
        item.setQuantity(item.getQuantity() - 1);
        return;
        }
        remove(position);
    }
}
