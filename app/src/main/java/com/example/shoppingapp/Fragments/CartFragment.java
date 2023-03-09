package com.example.shoppingapp.Fragments;

import android.annotation.SuppressLint;
import android.database.DataSetObserver;
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
import com.example.shoppingapp.interfaces.ItemInterface;
import com.example.shoppingapp.models.CartItemDetail;
import com.example.shoppingapp.services.ApiService;
import com.example.shoppingapp.services.ApiServiceGenerator;
import com.example.shoppingapp.states.CartState;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartFragment extends Fragment {
    private List<CartItemDetail> items = new ArrayList<>();
    private CartItemsAdapter cartItemsAdapter;
    private CartState state;
    double subTotalPrice = 0, vatPrice = 0, feePrice = 0, totalPrice = 0;
    ApiService apiService;


    @SuppressLint("DefaultLocale")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiService = ApiServiceGenerator.getClient("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjc3ODI4MDU5LCJleHAiOjE2Nzg0MzI4NTl9.AhmEUhUbGQtSlxgCnoAnAwc1GPunmA01io6oOzJgui8").create(ApiService.class);

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
        cartItemsAdapter = new CartItemsAdapter(getContext(), items, new ItemInterface() {
            @Override
            public void onDelete(int position) {
                state.remove(position);
                cartItemsAdapter.notifyDataSetChanged();
//                subTotalPrice = price(items);
//                vatPrice = (subTotalPrice * 0.2);
//                feePrice = 2.2;
//                totalPrice = subTotalPrice + vatPrice + feePrice;
//
//                subTotal.setText(String.format("%.2f", subTotalPrice));
//                vat.setText(String.format("%.2f", vatPrice));
//                fee.setText(String.valueOf(feePrice));
//                total.setText(String.format("%.2f", totalPrice));
//                cartItemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void remove() {

            }

            @Override
            public void inscrease(int position) {
                state.inscrease(position);
                cartItemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void descrease(int position) {
                state.descrease(position);
                cartItemsAdapter.notifyDataSetChanged();

            }
        });
        cartItemListView.setAdapter(cartItemsAdapter);

        cartItemsAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                subTotalPrice = price(items);
                vatPrice = subTotalPrice*0.1;
                feePrice = subTotalPrice*0.01;
                totalPrice = subTotalPrice + vatPrice + feePrice;

                subTotal.setText(String.format("%.2f", subTotalPrice));
                vat.setText(String.format("%.2f", vatPrice));
                fee.setText(String.valueOf(feePrice));
                total.setText(String.format("%.2f", totalPrice));

            }
        });

        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (items != null) {
                    apiService.insertCart(items, "john").enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            Log.e("TAG", "onResponse: " + response.body());
                            Toast.makeText(state, "Insert cart successfully", Toast.LENGTH_SHORT).show();
                            items.clear();
                            cartItemsAdapter.notifyDataSetChanged();
                            subTotalPrice = 0;
                            vatPrice = 0;
                            feePrice = 0;
                            totalPrice = 0;
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Log.e("TAG", "onFail: " + t.getMessage());
                        }
                    });
                }
            }
        });
    }


    double price(List<CartItemDetail> items) {
        final double[] price = {0};
        items.forEach(item -> {
            price[0] += item.getItem().getPrice() * item.getQuantity();
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