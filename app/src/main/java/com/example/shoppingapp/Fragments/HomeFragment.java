package com.example.shoppingapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.ShopItemsAdapter;
import com.example.shoppingapp.models.ShopItem;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    ArrayList<ShopItem> shopItems = new ArrayList<>();
    ShopItemsAdapter shopItemsAdapter;
    GridView shopItemListView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDumpData();
        shopItemsAdapter = new ShopItemsAdapter(shopItems,this.getContext());
        shopItemListView = (GridView) getActivity().findViewById(R.id.shoppingItemGridView);
        shopItemListView.setAdapter(shopItemsAdapter);

    }
    void initDumpData(){
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));
        shopItems.add(new ShopItem("AIRism Short Sleeve Polo Shirt",1.1100,"https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling"));

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home,null);

        return root;
    }
}