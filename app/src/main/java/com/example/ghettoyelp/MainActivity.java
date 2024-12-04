package com.example.ghettoyelp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghettoyelp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddReviewsPage();
            }
        });

        binding.previousReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreviousReviewsPage();
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }


    //todo: implement these functions once the pages have been created
    private void goToAddReviewsPage() {

    }

    private void goToPreviousReviewsPage() {
    }

    private void logout() {
    }
}