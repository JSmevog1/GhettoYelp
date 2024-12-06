package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivityViewAllUsersBinding;
import com.example.ghettoyelp.viewHolders.ViewAllUserViewModel;
import com.example.ghettoyelp.viewHolders.ViewAllUsersAdapter;

public class ViewAllUsersActivity extends AppCompatActivity {
    private ActivityViewAllUsersBinding binding;
    private ViewAllUserViewModel viewAllUserViewModel;
    //private UserRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityViewAllUsersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewAllUserViewModel = new ViewModelProvider(this).get(ViewAllUserViewModel.class);


        RecyclerView recyclerView = binding.UserDisplayRecyclerView;
        final ViewAllUsersAdapter adapter = new ViewAllUsersAdapter(new ViewAllUsersAdapter.UserDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //repository = UserRepository.getRepository(getApplication());

        viewAllUserViewModel.getAllUsers().observe(this, users ->{
            adapter.submitList(users);
        });
    }



    static Intent ViewAllUsersIntentFactory(Context context){
        return new Intent(context, ViewAllUsersActivity.class);
    }
}

