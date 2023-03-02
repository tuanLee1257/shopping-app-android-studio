package com.example.shoppingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.models.Item;
import com.example.shoppingapp.states.CartState;

public class ItemDetailsActivity extends AppCompatActivity {
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        CartState state = (CartState) getApplicationContext();

        item = (Item) getIntent().getSerializableExtra("ShopItem");
//        shopItem = new ShopItem("AIRism Short Sleeve Polo Shirt",
//                1.1100,
//                "https://image.uniqlo.com/UQ/ST3/vn/imagesgoods/458186/item/vngoods_71_458186.jpg?width=320",
//                4.5,"Smooth AIRism with a fresh feel. A versatile polo for casual or refined styling");

        //anh xa
        ImageView itemImg = findViewById(R.id.itemImg_itemDetail);
        TextView itemName = findViewById(R.id.itemName_itemDetail);
        TextView itemDetail = findViewById(R.id.itemDetail_itemDetail);
        TextView itemPrice = findViewById(R.id.itemPrice_itemDetail);
        ImageButton addToFavBtn =findViewById(R.id.favoriteBtn_itemDetail);
        Button addToCart = findViewById(R.id.addBtn_itemDetail);

        //
        Glide.with(this.getBaseContext()).load(item.getImgUrl()).into(itemImg);
        itemName.setText(item.getItemName());
        itemDetail.setText(item.getDescription());
        itemPrice.setText(String.valueOf(item.getPrice()));
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state.add(item);
            }
        });

    }
}