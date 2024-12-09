package com.example.ghettoyelp.viewHolders.ViewAllUsers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.R;

import java.util.ArrayList;
import java.util.List;

public class ViewAllUserReviewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private final List<Review> allReviews;
    private final List<Review> userReviews = new ArrayList<>();
    private final User user;

    public ViewAllUserReviewsAdapter(Context context, List<Review> allReviews, User user) {
        this.context = context;
        this.allReviews = allReviews;
        this.user = user;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(
                R.layout.user_review_recycler_item,parent,false);
        return new UserReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        getUserReviews();
        Review review = userReviews.get(position);
        ((UserReviewViewHolder) holder).review.setText(review.toString());
    }

    private void getUserReviews(){
        for (int i = 0; i < allReviews.size(); i++) {
            if (user.getUsername().equals(allReviews.get(i).getUsername())) {
                userReviews.add(allReviews.get(i));
            }
        }
    }

    @Override
    public int getItemCount() {
        return user.getReviewsCount();
    }

    private static class UserReviewViewHolder extends RecyclerView.ViewHolder {
        TextView review;

        UserReviewViewHolder(View view){
            super(view);
            review = view.findViewById(R.id.listUserReviewsTextView);
        }
    }
}
