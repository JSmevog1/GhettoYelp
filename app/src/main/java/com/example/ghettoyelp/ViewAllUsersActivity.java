package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivityViewAllUsersBinding;

import com.example.ghettoyelp.viewHolders.ViewAllUsers.ViewAllUsersAdapter;

public class ViewAllUsersActivity extends AppCompatActivity {
    private ActivityViewAllUsersBinding binding;
    private UserRepository userRepository;
    private ReviewsRepository reviewsRepository;
    private int userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userId = getIntent().getIntExtra("com.example.ghettoyelp.MAIN_ACTIVITY_USER_ID", -1);

        userRepository = UserRepository.getRepository(getApplication());
        reviewsRepository = ReviewsRepository.getRepository(getApplication());

        userRecyclerView();

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), userId));
            }
        });

        binding.ViewAllReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(ManageReviewsActivity.ManageReviewsActivityIntentFactory(getApplicationContext()));
            }
        });

    }

    private void userRecyclerView(){
        RecyclerView recyclerView = binding.UserDisplayRecyclerView;
        ViewAllUsersAdapter adapter = new ViewAllUsersAdapter(this, userRepository.getAllUsers(),reviewsRepository.getAllReviews());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    static Intent ViewAllUsersIntentFactory(Context context){
        return new Intent(context, ViewAllUsersActivity.class);
    }
}

