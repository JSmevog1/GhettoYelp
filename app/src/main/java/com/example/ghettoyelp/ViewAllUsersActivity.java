package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivityViewAllUsersBinding;
import com.example.ghettoyelp.viewHolders.ViewAllUsers.ViewAllUserViewModel;
import com.example.ghettoyelp.viewHolders.ViewAllUsers.ViewAllUsersAdapter;

public class ViewAllUsersActivity extends AppCompatActivity {
    private ActivityViewAllUsersBinding binding;
    private UserRepository userRepository;
    private ReviewsRepository reviewsRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        userRepository = UserRepository.getRepository(getApplication());
        reviewsRepository = ReviewsRepository.getRepository(getApplication());

        userRecyclerView();

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

