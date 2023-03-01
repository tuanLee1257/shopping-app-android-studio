package com.example.shoppingapp.Fragments;

import android.content.Intent;
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
import com.example.shoppingapp.interfaces.Onclick;
import com.example.shoppingapp.models.ResponseObject;
import com.example.shoppingapp.models.ShopItem;
import com.example.shoppingapp.services.ApiService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    ArrayList<ShopItem> shopItems = new ArrayList<>();
    ShopItemsAdapter shopItemsAdapter;
    GridView shopItemListView;
    SearchView searchView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e("TAG", "onViewCreated: " );

        shopItemsAdapter = new ShopItemsAdapter(shopItems, this.getContext(), new Onclick() {
            @Override
            public void onItemClicked(ShopItem shopItem) {
                Intent intent = new Intent(getContext(),ItemDetailsActivity.class);
                intent.putExtra("ShopItem", shopItem);
                startActivity(intent);
            }
        });
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
                if (newText==  null){
                    return false;
                }
                ApiService.apiService.searchItems(newText).enqueue(new Callback<ResponseObject>() {
                    @Override
                    public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                        shopItems.clear();
                        response.body().getData().forEach(shopItem -> {
                            shopItems.add(shopItem);
                        });
                        shopItemsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<ResponseObject> call, Throwable t) {
                        Log.e("TAG", "onFailure: "+t.getMessage() );
                    }
                });
                return true;
            }
        });

    }
    void initDumpData(){
        ApiService.apiService.getAllItems().enqueue(new Callback<ResponseObject>() {
            @Override
            public void onResponse(Call<ResponseObject> call, Response<ResponseObject> response) {
                response.body().getData().forEach(shopItem -> {
                    shopItems.add(shopItem);
                });
                if (response.body() != null) {
                    shopItemsAdapter.notifyDataSetChanged();
                    Log.e("TAG", "onResponse: "+shopItems.toString() );
                }
            }

            @Override
            public void onFailure(Call<ResponseObject> call, Throwable t) {
                Log.e("TAG", "onFailure: "+t.getMessage() );
            }
        });
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDumpData();
        Log.e("TAG", "onCreate: " );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home,null);

        return root;
    }
}