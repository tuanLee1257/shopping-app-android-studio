package com.example.shoppingapp.services;

import com.example.shoppingapp.models.CartItemDetail;
import com.example.shoppingapp.models.Item;
import com.example.shoppingapp.models.ResponseComments;
import com.example.shoppingapp.models.ResponseItem;
import com.example.shoppingapp.models.ResponseUser;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
//    String BASE_URL = "http://192.168.2.3:8090";
//
//    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM--dd HH:mm:ss").setLenient().create();
//    ApiService apiService = new Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(ApiService.class);


    @GET("/item")
    Call<ResponseItem>getAllItems();

    @PUT("/item/like")
    Call<ResponseUser> onSaveIconClicked(@Query("username") String username, @Body Item item);

    @GET("/item/search")
    Call<ResponseItem>searchItems(@Query("name")String name);

    @POST("/auth/login")
    Call<String> login(@Body HashMap<String,String> user);

    @POST("/cart/insert")
    Call<String> insertCart(@Body List<CartItemDetail> cartItemDetail, @Query("username") String username);

    @GET("/comment/getByItem")
    Call<ResponseComments> getAllCommentByItem(@Query("id") Long id);

    @POST("/comment")
    Call<Object> postComment(@Query("username") String username,@Query("comment")String comment,@Body Item item);

}
