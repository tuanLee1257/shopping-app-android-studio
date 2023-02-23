package com.example.shoppingapp.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.CartItemsAdapter;
import com.example.shoppingapp.models.ShopItem;
import com.example.shoppingapp.states.CartState;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private List<ShopItem> shopItems = new ArrayList<>();
    private CartItemsAdapter cartItemsAdapter;
    private CartState state;

    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        initDumpData();

        state = (CartState) getActivity().getApplicationContext();
        shopItems = state.getShopItems();

        //anh xa
        ListView cartItemListView = view.findViewById(R.id.cartItemListView);
        MaterialTextView subTotal = view.findViewById(R.id.SubTotalPrice_cart);
        MaterialTextView vat = view.findViewById(R.id.vat_cart);
        MaterialTextView fee = view.findViewById(R.id.shippingFee_cart);
        MaterialTextView total = view.findViewById(R.id.totalPrice_cart);

        //adapter
        cartItemsAdapter = new CartItemsAdapter(getContext(), shopItems);
        cartItemListView.setAdapter(cartItemsAdapter);

        if (shopItems != null) {
            //gan du lieu
            double subTotalPrice = price(shopItems);
            subTotal.setText(String.format("%.2f", subTotalPrice));

            double vatPrice = (subTotalPrice * 0.2);
            vat.setText(String.format("%.2f", vatPrice));

            double feePrice = 2.2;
            fee.setText(String.valueOf(feePrice));

            double totalPrice = subTotalPrice + vatPrice + feePrice;
            total.setText(String.format("%.2f", totalPrice));
        }


    }

    void initDumpData() {
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt", 1.1100, "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320", 4.5, "Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));

    }

    double price(List<ShopItem> shopItems) {
        final double[] price = {0};
        shopItems.forEach(shopItem -> {
            price[0] += shopItem.getPrice();
        });

        return price[0];
    }

    @Override
    public void onResume() {
        super.onResume();
        shopItems.addAll(state.getShopItems());
        cartItemsAdapter.refresh(shopItems);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}