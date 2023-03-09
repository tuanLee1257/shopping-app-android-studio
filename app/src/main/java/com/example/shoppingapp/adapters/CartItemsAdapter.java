package com.example.shoppingapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.interfaces.ItemInterface;
import com.example.shoppingapp.models.CartItemDetail;
import com.example.shoppingapp.models.Item;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class CartItemsAdapter extends BaseAdapter {
    private Context context;
    private List<CartItemDetail> items;
    private ItemInterface listenner;

    public CartItemsAdapter(Context context, List<CartItemDetail> items, ItemInterface listenner) {
        this.context = context;
        this.items = items;
        this.listenner = listenner;
    }

    public void refresh(List<CartItemDetail> items){
        this.items = items;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (items == null) {
            return 0;
        }
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cartItemsView;
        if (convertView == null){
            cartItemsView = View.inflate(parent.getContext(), R.layout.cart_item,null);
        }
        else cartItemsView = convertView;

        CartItemDetail cartItem = items.get(position);
        Item item = cartItem.getItem();
        //anh xa
        MaterialTextView quantity = cartItemsView.findViewById(R.id.quantity);
        ImageView cartItemImage = cartItemsView.findViewById(R.id.cartItemImage);
        MaterialTextView cartItemName = cartItemsView.findViewById(R.id.cartItemName);
        MaterialTextView cartItemSize = cartItemsView.findViewById(R.id.cartItemSize);
        MaterialTextView cartItemPrice= cartItemsView.findViewById(R.id.cartItemPrice);
        MaterialButton cartDeleteBtn = cartItemsView.findViewById(R.id.deleteBtn_cart);
        MaterialButton decrease = cartItemsView.findViewById(R.id.decrease);
        MaterialButton increase = cartItemsView.findViewById(R.id.increase);

        //gan du lieu
        Glide.with(this.context).load(item.getImgUrl()).into(cartItemImage);
        cartItemName.setText(item.getItemName());
        cartItemSize.setText("L");
        cartItemPrice.setText(String.valueOf(item.getPrice()));
        quantity.setText(String.valueOf(cartItem.getQuantity()));
        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.descrease(position);
            }
        });
        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.inscrease(position);
            }
        });

        cartDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenner.onDelete(position);
            }
        });
        return cartItemsView;
    }

}
