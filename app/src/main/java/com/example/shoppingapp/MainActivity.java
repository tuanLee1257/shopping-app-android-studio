package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.example.shoppingapp.Activities.LoginActivity;
import com.example.shoppingapp.Fragments.CartFragment;
import com.example.shoppingapp.Fragments.HomeFragment;
import com.example.shoppingapp.Fragments.SavedFragment;
import com.example.shoppingapp.Fragments.SettingsFragment;
import com.example.shoppingapp.services.ApiService;
import com.example.shoppingapp.services.ApiServiceGenerator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment homeFragment = new HomeFragment();
    Fragment savedFragment = new SavedFragment();
    Fragment cartFragment = new CartFragment();
    Fragment settingsFragment = new SettingsFragment();
    SharedPreferences sharedRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedRef = getSharedPreferences("userRef", Context.MODE_PRIVATE);
        String token = sharedRef.getString("token","null");
        Log.e("TAG", "onCreatetoken: "+token );

        if (token == "null"){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }

        bottomNavigationView = findViewById(R.id.bottomNavView);
        loadFragment(homeFragment);
        bottomNavigationView.setOnItemSelectedListener(item ->
                {
                    switch (item.getItemId()) {
                        case R.id.home:
                            loadFragment(homeFragment);
                            return true;
                        case R.id.saved:
                            loadFragment(savedFragment);
                            return true;
                        case R.id.cart:
                            loadFragment(cartFragment);
                            return true;
                        case R.id.settings:
                            loadFragment(settingsFragment);
                            return true;
                    }
                    return true;
                }
                );

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}