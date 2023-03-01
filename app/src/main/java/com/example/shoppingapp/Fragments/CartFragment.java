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
import com.example.shoppingapp.interfaces.CartItemInterface;
import com.example.shoppingapp.models.ShopItem;
import com.example.shoppingapp.states.CartState;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private List<ShopItem> shopItems = new ArrayList<>();
    private CartItemsAdapter cartItemsAdapter;
    private CartState state;
    double subTotalPrice = 0, vatPrice = 0, feePrice = 0, totalPrice = 0;


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
        cartItemsAdapter = new CartItemsAdapter(getContext(), shopItems, new CartItemInterface() {
            @Override
            public void onDelete(int position) {
                state.remove(position);
                subTotalPrice = price(shopItems);
                vatPrice = (subTotalPrice * 0.2);
                feePrice = 2.2;
                totalPrice = subTotalPrice + vatPrice + feePrice;

                subTotal.setText(String.format("%.2f", subTotalPrice));
                vat.setText(String.format("%.2f", vatPrice));
                fee.setText(String.valueOf(feePrice));
                total.setText(String.format("%.2f", totalPrice));
                cartItemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void remove() {

            }
        });
        cartItemListView.setAdapter(cartItemsAdapter);


        if (shopItems != null) {
            //gan du lieu
            subTotalPrice = price(shopItems);
            vatPrice = (subTotalPrice * 0.2);
            feePrice = 2.2;
            totalPrice = subTotalPrice + vatPrice + feePrice;
        }
        subTotal.setText(String.format("%.2f", subTotalPrice));
        vat.setText(String.format("%.2f", vatPrice));
        fee.setText(String.valueOf(feePrice));
        total.setText(String.format("%.2f", totalPrice));
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
        cartItemsAdapter.notifyDataSetChanged();
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