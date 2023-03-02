package com.example.shoppingapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.shoppingapp.R;
import com.example.shoppingapp.adapters.CartItemsAdapter;
import com.example.shoppingapp.interfaces.CartItemInterface;
import com.example.shoppingapp.models.CartItemDetail;
import com.example.shoppingapp.models.Item;
import com.example.shoppingapp.models.ResponseObject;
import com.example.shoppingapp.services.ApiService;
import com.example.shoppingapp.states.CartState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private List<Item> items = new ArrayList<>();
    private CartItemsAdapter cartItemsAdapter;
    private CartState state;
    double subTotalPrice = 0, vatPrice = 0, feePrice = 0, totalPrice = 0;


    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        initDumpData();
        state = (CartState) getActivity().getApplicationContext();
        items = state.getShopItems();


        //anh xa
        ListView cartItemListView = view.findViewById(R.id.cartItemListView);
        MaterialTextView subTotal = view.findViewById(R.id.SubTotalPrice_cart);
        MaterialTextView vat = view.findViewById(R.id.vat_cart);
        MaterialTextView fee = view.findViewById(R.id.shippingFee_cart);
        MaterialTextView total = view.findViewById(R.id.totalPrice_cart);
        MaterialButton checkOut = view.findViewById(R.id.checkOutBtn_cart);


        //adapter
        cartItemsAdapter = new CartItemsAdapter(getContext(), items, new CartItemInterface() {
            @Override
            public void onDelete(int position) {
                state.remove(position);
                subTotalPrice = price(items);
                vatPrice = (subTotalPrice * 0.2);
                feePrice = 2.2;
                totalPrice = subTotalPrice + vatPrice + feePrice;

                subTotal.setText(String.format("%.2f", subTotalPrice));
                vat.setText(String.format("%.2f", vatPrice));
                fee.setText(String.valueOf(feePrice));
                total.setText(String.format("%.2f", totalPrice));
                cartItemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void remove() {

            }
        });
        cartItemListView.setAdapter(cartItemsAdapter);

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items != null){
                    List<CartItemDetail> cartItemDetails = new ArrayList<>();
                    items.forEach(item -> {
                        cartItemDetails.add(new CartItemDetail(item,2));
                    });
                    Log.e("TAG", "onClick: "+cartItemDetails);
                    ApiService.apiService.insertCart(cartItemDetails,"john").enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.e("TAG", "onResponse: "+response.body() );
                            Toast.makeText(state, "Insert cart successfully", Toast.LENGTH_SHORT).show();
                            items.clear();
                            cartItemsAdapter.notifyDataSetChanged();
                            subTotalPrice = 0;
                            vatPrice = 0;
                            totalPrice = subTotalPrice + vatPrice + feePrice;

                            subTotal.setText(String.format("%.2f", subTotalPrice));
                            vat.setText(String.format("%.2f", vatPrice));
                            fee.setText(String.valueOf(feePrice));
                            total.setText(String.format("%.2f", totalPrice));
                            cartItemsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("TAG", "onFail: "+t.getMessage() );
                        }
                    });
                }
            }
        });


        if (items != null) {
            //gan du lieu
            subTotalPrice = price(items);
            vatPrice = (subTotalPrice * 0.2);
            feePrice = 2.2;
            totalPrice = subTotalPrice + vatPrice + feePrice;
        }

        subTotal.setText(String.format("%.2f", subTotalPrice));
        vat.setText(String.format("%.2f", vatPrice));
        fee.setText(String.valueOf(feePrice));
        total.setText(String.format("%.2f", totalPrice));

    }


    double price(List<Item> items) {
        final double[] price = {0};
        items.forEach(shopItem -> {
            price[0] += shopItem.getPrice();
        });

        return price[0];
    }

    @Override
    public void onResume() {
        super.onResume();
        cartItemsAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }
}