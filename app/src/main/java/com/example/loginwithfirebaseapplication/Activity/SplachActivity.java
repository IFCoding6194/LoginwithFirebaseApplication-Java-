package com.example.loginwithfirebaseapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginwithfirebaseapplication.R;
import com.example.loginwithfirebaseapplication.databinding.ActivitySplachBinding;

public class SplachActivity extends AppCompatActivity {
    private ActivitySplachBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplachBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        binding.signUpTvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SplachActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        binding.loginTvbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplachActivity.this,LoginActivity.class));
            }
        });
    }

}