package com.example.shoppingapp.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.shoppingapp.R;
import com.example.shoppingapp.services.ApiService;
import com.example.shoppingapp.services.ApiServiceGenerator;
import com.google.android.material.button.MaterialButton;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText username_edit;
    EditText password_edit;
    MaterialButton signin_btn;
    TextView signup_route;
    String username, password;
    SharedPreferences sharedRef;
    ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        sharedRef = getSharedPreferences("userRef", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedRef.edit();
        Log.e("TAG", "onCreate: "+sharedRef.getString("token","") );
        apiService = ApiServiceGenerator.getClient(sharedRef.getString("token", "")).create(ApiService.class);
        //anh xa
        username_edit = findViewById(R.id.username);
        password_edit = findViewById(R.id.password);
        signin_btn = findViewById(R.id.signin_btn);
        signup_route = findViewById(R.id.signup_text);

        username = sharedRef.getString("username", "");
        password = sharedRef.getString("password", "");

        username_edit.setText(username);
        password_edit.setText(password);


        username_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                username = s.toString();
            }
        });

        password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                password = s.toString();
            }
        });

        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("username", username);
                editor.putString("password", password);
                editor.apply();
                HashMap<String, String> user = new HashMap<>();
                user.put("username", username);
                user.put("password", password);
                apiService.login(user).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Log.e("TAG", "onResponse: " + response.body());
                        editor.putString("token",response.body()).commit();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("TAG", "onFailure: " + t.getMessage());
                    }
                });
            }
        });

        signup_route.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

    }
}