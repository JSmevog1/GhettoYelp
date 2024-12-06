package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.databinding.ActivityLoginBinding;

/**
 * @author Tariq Kakar
 * Last Update: Dec 4, 2024
 * Description:
 *      This activity is the entry point for user authentification in the app.
 *      It provides a user interface for logging in, including fields for username and password.
 */

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ReviewsRepository reviewsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //todo: get user reviews from ReviewRepository
        // reviewsRepository = ReviewsRepository.getRepository(getApplication);

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.mainActivityIntentFactory(getApplicationContext(), 0);
                startActivity(intent);
            }
        });
    }

    /**
     * Factory method to create an Intent for starting the LoginActivity.
     *
     * @param context is the context used to create the Intent.
     * @return an Intent configured to start the LoginActivity.
     */
    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}