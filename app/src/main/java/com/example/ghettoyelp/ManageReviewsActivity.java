package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;

import com.example.ghettoyelp.databinding.ActivityManageReviewsBinding;

public class ManageReviewsActivity extends AppCompatActivity {
    private ActivityManageReviewsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ViewAllUsersActivity.ViewAllUsersIntentFactory(getApplicationContext()));
            }
        });
    }

    static Intent ManageReviewsActivityIntentFactory(Context context){
        return new Intent(context, ManageReviewsActivity.class);
    }
}