package com.example.shoppingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.shoppingapp.Fragments.CartFragment;
import com.example.shoppingapp.Fragments.HomeFragment;
import com.example.shoppingapp.Fragments.SavedFragment;
import com.example.shoppingapp.Fragments.SettingsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    Fragment homeFragment = new HomeFragment();
    Fragment savedFragment = new SavedFragment();
    Fragment cartFragment = new CartFragment();
    Fragment settingsFragment = new SettingsFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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