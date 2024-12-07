package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.databinding.ActivityAddReviewBinding;
import com.example.ghettoyelp.databinding.ActivityViewAllReviewsBinding;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Activity Page to handle UI operations on Add Review Page
 */

public class AddReviewActivity extends AppCompatActivity {
    private ActivityAddReviewBinding binding;
    private ReviewsRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = ReviewsRepository.getRepository(getApplication());

        binding.addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    // check input data before adding into the Review database

    static Intent AddReviewIntentFactory(Context context){
        return new Intent(context, AddReviewActivity.class);
    }
}
