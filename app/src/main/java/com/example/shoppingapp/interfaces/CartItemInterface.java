package com.example.shoppingapp.interfaces;

public interface CartItemInterface {
    void onDelete(int position);
    void remove();
    void inscrease(int position);
    void descrease(int position);
}
