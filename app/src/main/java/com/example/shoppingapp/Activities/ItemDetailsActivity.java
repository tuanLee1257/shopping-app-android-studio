package com.example.shoppingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.models.ShopItem;

public class ItemDetailsActivity extends AppCompatActivity {
    ShopItem shopItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        shopItem = new ShopItem("AIRism Short Sleeve Polo Shirt",
                1.1100,
                "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",
                4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling");

        //anh xa
        ImageView itemImg = findViewById(R.id.itemImg_itemDetail);
        TextView itemName = findViewById(R.id.itemName_itemDetail);
        TextView itemDetail = findViewById(R.id.itemDetail_itemDetail);
        TextView itemPrice = findViewById(R.id.itemPrice_itemDetail);
        Button addToFavBtn =findViewById(R.id.favoriteBtn_itemDetail);

        Glide.with(this.getBaseContext()).load(shopItem.getUrl()).into(itemImg);
        itemName.setText(shopItem.getName());
        itemDetail.setText(shopItem.getDescription());
        itemPrice.setText(String.valueOf(shopItem.getPrice()));
    }
}