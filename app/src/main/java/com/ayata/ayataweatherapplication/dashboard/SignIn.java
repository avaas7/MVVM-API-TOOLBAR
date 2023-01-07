package com.ayata.ayataweatherapplication.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ayata.ayataweatherapplication.R;
import com.ayata.ayataweatherapplication.databinding.ActivitySignInBinding;

import javax.inject.Inject;

public class SignIn extends AppCompatActivity {

    ActivitySignInBinding signInBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signInBinding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(signInBinding.getRoot());

        signInBinding.btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignIn.this,MainActivity.class);
                intent.putExtra("username",signInBinding.etUsername.getText().toString().trim());
                intent.putExtra("desc",signInBinding.etDescription.getText().toString().trim());
                startActivity(intent);
            }
        });
    }
}