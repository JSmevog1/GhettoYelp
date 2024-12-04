package com.example.ghettoyelp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private User user;

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

        if (user.isAdmin()){
            binding.AdminViewAllUsersButton.setVisibility(View.VISIBLE);
            binding.AdminViewAllUsersButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adminViewAllUsers();
                }
            });
            binding.AdminAddRemoveRestaurantsButton.setVisibility(View.VISIBLE);
            binding.AdminAddRemoveRestaurantsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adminAddRemoveRestaurants();
                }
            });
        }
    }


    //todo: implement these functions once the pages have been created
    private void goToAddReviewsPage() {

    }

    private void goToPreviousReviewsPage() {
    }

    private void logout() {
    }

    private void adminViewAllUsers(){

    }

    private void adminAddRemoveRestaurants(){

    }
}