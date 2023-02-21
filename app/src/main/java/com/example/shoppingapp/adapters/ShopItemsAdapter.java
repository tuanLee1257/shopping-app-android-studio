package com.example.shoppingapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.models.ShopItem;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShopItemsAdapter extends BaseAdapter {
    private ArrayList<ShopItem> shopItems;
    private Context context;
    private LayoutInflater layoutInflater;

    public ShopItemsAdapter(ArrayList<ShopItem> shopItems, Context context) {
        this.shopItems = shopItems;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
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
        View shopItemView;
        if (convertView == null){

            shopItemView = View.inflate(parent.getContext(), R.layout.shopping_item,null);
        }
        else shopItemView = convertView;

        ShopItem shopItem = this.shopItems.get(position);
        //anh xa
        TextView itemName = (TextView) shopItemView.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) shopItemView.findViewById(R.id.itemPrice);
        ImageView itemImage = (ImageView) shopItemView.findViewById(R.id.itemImg);

        //gan du lieu
        itemName.setText(shopItem.getName());
        itemPrice.setText(String.valueOf(shopItem.getPrice()));
        Bitmap image = UrlToImage(shopItem.getUrl());
        itemImage.setImageBitmap(image);
        return shopItemView;
    }

    Bitmap UrlToImage(String inputUrl){
        Bitmap bitmap = null;
        try {
            URL url = new URL(inputUrl);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        }
        catch (Exception e){
            Log.e("TAG", "UrlToImage: "+e.getMessage() );
        }
        return bitmap;
    }
}