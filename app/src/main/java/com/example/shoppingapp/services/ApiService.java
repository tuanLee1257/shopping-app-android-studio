package com.example.shoppingapp.services;

import android.content.SharedPreferences;

import com.example.shoppingapp.models.CartItemDetail;
import com.example.shoppingapp.models.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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


    @GET("/shop")
    Call<ResponseObject>getAllItems();

    @GET("/shop/search")
    Call<ResponseObject>searchItems(@Query("name")String name);

    @POST("/user/{username}/cart/insert")
    Call<String> insertCart(@Body List<CartItemDetail> cartItemDetail, @Path("username")String username);

    @POST("/auth/login")
    Call<String> login(@Body HashMap<String,String> user);
}
