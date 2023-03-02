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

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.interfaces.Onclick;
import com.example.shoppingapp.models.Item;
import java.net.URL;
import java.util.ArrayList;

public class ShopItemsAdapter extends BaseAdapter {

    private ArrayList<Item> items;
    private Context context;
    private LayoutInflater layoutInflater;
    private Onclick onclick;

    public ShopItemsAdapter(ArrayList<Item> items, Context context , Onclick listenner) {
        this.items = items;
        this.context = context;
        this.onclick  = listenner;
    }

    @Override
    public int getCount() {
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
        View shopItemView;
        if (convertView == null){
            shopItemView = View.inflate(parent.getContext(), R.layout.shopping_item,null);
        }
        else shopItemView = convertView;

        Item item = this.items.get(position);
        //anh xa
        TextView itemName = (TextView) shopItemView.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) shopItemView.findViewById(R.id.itemPrice);
        ImageView itemImage = (ImageView) shopItemView.findViewById(R.id.itemImg);

        //gan du lieu
        itemName.setText(item.getItemName());
        itemPrice.setText(String.valueOf(item.getPrice()));
        Glide.with(this.context).load(item.getImgUrl()).into(itemImage);

        //bat su kien onClick
        shopItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onclick.onItemClicked(item);
            }
        });

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
