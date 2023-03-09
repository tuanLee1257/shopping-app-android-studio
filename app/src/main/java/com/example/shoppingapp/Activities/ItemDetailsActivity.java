package com.example.shoppingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.CommentsAdapter;
import com.example.shoppingapp.models.Comment;
import com.example.shoppingapp.models.Item;
import com.example.shoppingapp.models.ResponseComments;
import com.example.shoppingapp.services.ApiService;
import com.example.shoppingapp.services.ApiServiceGenerator;
import com.example.shoppingapp.states.CartState;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDetailsActivity extends AppCompatActivity {
    Item item;
    String messageString = "";
    SharedPreferences sharedRef;
    ApiService apiService;
    String username;
    List<Comment> commentList = new ArrayList<>();
    CommentsAdapter commentsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        sharedRef = getSharedPreferences("userRef", Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedRef.edit();
        apiService = ApiServiceGenerator.getClient(sharedRef.getString("token", "")).create(ApiService.class);
        username = sharedRef.getString("username", "");

        CartState state = (CartState) getApplicationContext();
        item = (Item) getIntent().getSerializableExtra("ShopItem");

        apiService.getAllCommentByItem(Long.valueOf(item.getId())).enqueue(new Callback<ResponseComments>() {
            @Override
            public void onResponse(Call<ResponseComments> call, Response<ResponseComments> response) {
                Log.e("TAG", "onResponse: "+Long.valueOf(item.getId()) );
                Log.e("TAG", "onResponse: "+response.body().getData());
                response.body().getData().forEach(item ->{
                    commentList.add(item);
                });
                Log.e("TAG", "onResponse: " + commentList);
                commentsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<ResponseComments> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

        //anh xa
        ImageView itemImg = findViewById(R.id.itemImg_itemDetail);
        TextView itemName = findViewById(R.id.itemName_itemDetail);
        TextView itemDetail = findViewById(R.id.itemDetail_itemDetail);
        TextView itemPrice = findViewById(R.id.itemPrice_itemDetail);
        MaterialButton addToFavBtn = findViewById(R.id.favoriteBtn_itemDetail);
        Button addToCart = findViewById(R.id.addBtn_itemDetail);
        RecyclerView commentsListView = findViewById(R.id.commentsListView);
        EditText message = findViewById(R.id.new_comment);
        MaterialButton send_btn = findViewById(R.id.send_btn);


        Log.e("TAG", "onCreate: "+ commentList );
        commentsAdapter = new CommentsAdapter(getBaseContext(), commentList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getBaseContext(), RecyclerView.VERTICAL, false);
        commentsListView.setLayoutManager(linearLayoutManager);
        commentsListView.setAdapter(commentsAdapter);
        commentsAdapter.notifyDataSetChanged();


        //

        message.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                messageString = String.valueOf(s);
            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiService.postComment(username, messageString,item).enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        apiService.postComment(username,messageString,item).enqueue(new Callback<Object>() {
                            @Override
                            public void onResponse(Call<Object> call, Response<Object> response) {
                                Toast.makeText(state, "new comment posted", Toast.LENGTH_SHORT).show();
                                Log.e("TAG", "onResponse: "+ response.body());
                                commentsAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onFailure(Call<Object> call, Throwable t) {
                                Log.e("TAG", "onFailure: "+t.getMessage() );
                            }
                        });
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
            }
        });

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