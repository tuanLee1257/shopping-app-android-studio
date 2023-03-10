package com.example.shoppingapp.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.example.shoppingapp.Activities.ItemDetailsActivity;
import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.ShopItemsAdapter;
import com.example.shoppingapp.interfaces.ItemAdapterInterface;
import com.example.shoppingapp.models.ResponseItem;
import com.example.shoppingapp.models.Item;
import com.example.shoppingapp.models.ResponseUser;
import com.example.shoppingapp.services.ApiService;
import com.example.shoppingapp.services.ApiServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ArrayList<Item> items = new ArrayList<>();
    ShopItemsAdapter shopItemsAdapter;
    GridView shopItemListView;
    SearchView searchView;
    ApiService apiService;
    SharedPreferences sharedRef;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        sharedRef = getActivity().getSharedPreferences("userRef", Context.MODE_PRIVATE);
        String username = sharedRef.getString("username","");
        apiService = ApiServiceGenerator.getClient(sharedRef.getString("token", "")).create(ApiService.class);

        apiService.getAllItems().enqueue(new Callback<ResponseItem>() {
            @Override
            public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {
                response.body().getData().forEach(shopItem -> {
                    items.add(shopItem);
                });
                if (response.body() != null) {
                    shopItemsAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseItem> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });

        shopItemsAdapter = new ShopItemsAdapter(items, this.getContext(),apiService , sharedRef);
        shopItemListView = (GridView) getActivity().findViewById(R.id.shoppingItemGridView);
        shopItemListView.setAdapter(shopItemsAdapter);

        shopItemListView.setOnItemClickListener((parent, view1, position, id) -> Toast.makeText(getContext(), String.valueOf(position), Toast.LENGTH_SHORT).show());
        searchView = getActivity().findViewById(R.id.searchItems);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText == null || newText == "") {
                    return false;
                }
                apiService.searchItems(newText).enqueue(new Callback<ResponseItem>() {
                    @Override
                    public void onResponse(Call<ResponseItem> call, Response<ResponseItem> response) {
                        items.clear();
                        response.body().getData().forEach(shopItem -> {
                            items.add(shopItem);
                        });
                        shopItemsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ResponseItem> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
                return true;
            }
        });

    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home, null);

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}