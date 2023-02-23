package com.example.shoppingapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.models.ShopItem;
import com.example.shoppingapp.states.CartState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class CartItemsAdapter extends BaseAdapter {
    private Context context;
    private List<ShopItem> shopItems;

    public CartItemsAdapter(Context context, List<ShopItem> shopItems) {
        this.context = context;
        this.shopItems = shopItems;
    }

    public void refresh(List<ShopItem> shopItems){
        this.shopItems = shopItems;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        if (shopItems == null) {
            return 0;
        }
        return shopItems.size();
    }

    @Override
    public Object getItem(int position) {
        return shopItems.get(position);
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

        ShopItem shopItem = shopItems.get(position);
        //anh xa
        ImageView cartItemImage = cartItemsView.findViewById(R.id.cartItemImage);
        MaterialTextView cartItemName = cartItemsView.findViewById(R.id.cartItemName);
        MaterialTextView cartItemSize = cartItemsView.findViewById(R.id.cartItemSize);
        MaterialTextView cartItemPrice= cartItemsView.findViewById(R.id.cartItemPrice);
        MaterialButton cartDeleteBtn = cartItemsView.findViewById(R.id.deleteBtn_cart);

        //gan du lieu
        Glide.with(this.context).load(shopItem.getUrl()).into(cartItemImage);
        cartItemName.setText(shopItem.getName());
        cartItemSize.setText("L");
        cartItemPrice.setText(String.valueOf(shopItem.getPrice()));

        cartDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, position, Toast.LENGTH_SHORT).show();
            }
        });


        return cartItemsView;
    }

}
