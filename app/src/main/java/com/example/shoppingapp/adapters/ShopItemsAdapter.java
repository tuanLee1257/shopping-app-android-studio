package com.example.shoppingapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.Activities.ItemDetailsActivity;
import com.example.shoppingapp.R;
import com.example.shoppingapp.models.Item;
import com.example.shoppingapp.models.ResponseUser;
import com.example.shoppingapp.services.ApiService;

import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShopItemsAdapter extends BaseAdapter {

    private ArrayList<Item> items;
    private Context context;
    private ApiService apiService;
    private SharedPreferences sharedPref;

    public ShopItemsAdapter(ArrayList<Item> items, Context context, ApiService apiService, SharedPreferences sharedRef) {
        this.items = items;
        this.context = context;
        this.apiService = apiService;
        this.sharedPref = sharedRef;
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
        if (convertView == null) {
            shopItemView = View.inflate(parent.getContext(), R.layout.shopping_item, null);
        } else shopItemView = convertView;

        Item item = this.items.get(position);
        //anh xa
        TextView itemName = (TextView) shopItemView.findViewById(R.id.itemName);
        TextView itemPrice = (TextView) shopItemView.findViewById(R.id.itemPrice);
        ImageView itemImage = (ImageView) shopItemView.findViewById(R.id.itemImg);
        ImageButton saveBtn = (ImageButton) shopItemView.findViewById(R.id.saveIcon);

        //gan du lieu
        itemName.setText(item.getItemName());
        itemPrice.setText(String.valueOf(item.getPrice()));
        Glide.with(this.context).load(item.getImgUrl()).into(itemImage);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.onSaveIconClicked(sharedPref.getString("username",""),item).enqueue(new Callback<ResponseUser>() {
                    @Override
                    public void onResponse(Call<ResponseUser> call, Response<ResponseUser> response) {
                        Toast.makeText(context, response.body().getMessages(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseUser> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        //bat su kien onClick
        shopItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ItemDetailsActivity.class);
                intent.putExtra("ShopItem", item);
                context.startActivity(intent);
            }
        });

        return shopItemView;
    }

    Bitmap UrlToImage(String inputUrl) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(inputUrl);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (Exception e) {
            Log.e("TAG", "UrlToImage: " + e.getMessage());
        }
        return bitmap;
    }
}
