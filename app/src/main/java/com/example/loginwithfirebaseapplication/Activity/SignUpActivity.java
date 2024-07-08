package com.example.loginwithfirebaseapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginwithfirebaseapplication.R;
import com.example.loginwithfirebaseapplication.databinding.ActivitySignUpBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private ActivitySignUpBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        mAuth = FirebaseAuth.getInstance();

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });

        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void signUp() {
        String gmail = binding.gmailEdt.getText().toString().trim();
        String password = binding.passwordEdt.getText().toString().trim();
        String confirmPassword = binding.confirmPasswordEdt.getText().toString().trim();

        if (TextUtils.isEmpty(gmail)) {
            binding.gmailEdt.setError("Please Enter a Gmail");
            binding.gmailEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            binding.passwordEdt.setError("Please Enter a Password");
            binding.passwordEdt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            binding.confirmPasswordEdt.setError("Please Enter a Confirm password");
            binding.confirmPasswordEdt.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(gmail, password).addOnCompleteListener(this, task -> {
            if (task.isSuccessful()) {
                Toast.makeText(this, "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                onBackPressed();
                finish();
            }
            else {
                Toast.makeText(this, "Sign Up Failed: " + task.getException().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
