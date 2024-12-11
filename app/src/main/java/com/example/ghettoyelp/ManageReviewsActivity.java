package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.databinding.ActivityManageReviewsBinding;
import com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews.ManageReviewAdapter;
import com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews.ManageReviewModel;

public class ManageReviewsActivity extends AppCompatActivity {
    private ActivityManageReviewsBinding binding;
    private ManageReviewModel manageReviewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManageReviewsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        manageReviewModel = new ViewModelProvider(this).get(ManageReviewModel.class);

        RecyclerView recyclerView = binding.ManageReviewsRecycler;
        final ManageReviewAdapter adapter = new ManageReviewAdapter(new ManageReviewAdapter.ManageReviewDiff(), review -> {
            // Handle button click action here
            manageReviewModel.deleteReview(review);
            Toast.makeText(this, "Review deleted.", Toast.LENGTH_SHORT).show();
        });
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        manageReviewModel.getAllReviews().observe(this, reviews -> {
            adapter.submitList(reviews);
        });

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