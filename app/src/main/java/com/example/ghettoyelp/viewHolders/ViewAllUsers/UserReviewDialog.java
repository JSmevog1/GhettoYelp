package com.example.ghettoyelp.viewHolders.ViewAllUsers;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.R;

import java.util.List;

public class UserReviewDialog extends Dialog {
    private final List<Review> reviews;
    User user;
    public UserReviewDialog(Context context, List<Review>reviews, User user) {
        super(context);
        this.reviews = reviews;
        this.user = user;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState != null ? savedInstanceState : new Bundle());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.user_review_dialog, null);
        setContentView(view);
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setUpRecycler(view);
    }

    private void setUpRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.reviewListRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Context context = this.getContext();
        ViewAllUserReviewsAdapter adapter = new ViewAllUserReviewsAdapter(context, reviews, user);
        recyclerView.setAdapter(adapter);
    }
}
