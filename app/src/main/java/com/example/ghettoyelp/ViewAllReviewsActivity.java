package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.databinding.ActivityViewAllReviewsBinding;
import com.example.ghettoyelp.viewHolders.AllReviews.ViewAllReviewsAdapter;
import com.example.ghettoyelp.viewHolders.AllReviews.ViewAllReviewsViewHolder;
import com.example.ghettoyelp.viewHolders.AllReviews.ViewAllReviewsViewModel;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Activity Page to handle back-end operations of ViewAllReviews
 */

public class ViewAllReviewsActivity extends AppCompatActivity {
    private ActivityViewAllReviewsBinding binding;
    private ViewAllReviewsViewModel reviewsViewModel;
    private ReviewsRepository repository;
    private int userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reviewsViewModel = new ViewModelProvider(this).get(ViewAllReviewsViewModel.class);
        repository = ReviewsRepository.getRepository(getApplication());
        RecyclerView recyclerView = binding.ReviewsDisplayRecyclerView;
        userID = getIntent().getIntExtra("com.example.ghettoyelp.MAIN_ACTIVITY_USER_ID", -1);

        ViewAllReviewsAdapter adapter = new ViewAllReviewsAdapter(new ViewAllReviewsAdapter.ReviewDiff(),
                this, repository.getReviewByUserID(userID));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.submitList(reviewsViewModel.getAllReviewsByID(userID));
    }

    static Intent ViewAllReviewsIntentFactory(Context context){
        return new Intent(context, ViewAllReviewsActivity.class);
    }
}
