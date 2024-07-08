package com.example.loginwithfirebaseapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.loginwithfirebaseapplication.R;
import com.example.loginwithfirebaseapplication.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthRegistrar;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.internal.zzac;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.white));

        mAuth = FirebaseAuth.getInstance();

        binding.backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gmail = binding.gmailEdt.getText().toString().trim();
                String password = binding.passwordEdt.getText().toString().trim();

                if (TextUtils.isEmpty(gmail)){
                    binding.gmailEdt.setError("Please Enter a Gmail");
                    binding.gmailEdt.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(password)){
                    binding.passwordEdt.setError("Please Enter a Password");
                    binding.passwordEdt.requestFocus();
                    return;
                }

                if (!Patterns.EMAIL_ADDRESS.matcher(gmail).matches()){
                    binding.gmailEdt.setError("Please Enter a Valid Gmail");
                    binding.gmailEdt.requestFocus();
                    return;
                }

                mAuth.signInWithEmailAndPassword(gmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}