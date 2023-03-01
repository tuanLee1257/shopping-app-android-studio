package com.example.shoppingapp.services;

import com.example.shoppingapp.models.ResponseObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    String BASE_URL = "http://192.168.2.3:8090";

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM--dd HH:mm:ss").create();
    ApiService apiService = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService.class);


    @GET("/shop")
    Call<ResponseObject>getAllItems();

    @GET("/shop/search")
    Call<ResponseObject>searchItems(@Query("name")String name);

}
