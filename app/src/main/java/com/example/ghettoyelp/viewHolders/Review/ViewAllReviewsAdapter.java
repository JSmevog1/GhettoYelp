package com.example.ghettoyelp.viewHolders.Review;

import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ghettoyelp.Database.Entities.Review;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Extend from ListAdapter
 *      Collecting Review from Data table and update to ReviewsViewHolder-UI
 */

public class ViewAllReviewsAdapter extends ListAdapter<Review, ViewAllReviewsViewHolder> {

    public ViewAllReviewsAdapter(@NonNull DiffUtil.ItemCallback<Review> diffCallback){
        super(diffCallback);
    }

    @NonNull
    @Override
    public ViewAllReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewAllReviewsViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllReviewsViewHolder holder, int position) {
        Review current = getItem(position);
        holder.setText(current.getRestaurant(), current.getRating(), current.getReview());
    }

    public static class ReviewDiff extends DiffUtil.ItemCallback<Review>{
        // compare 2 items' address
        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem){
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem){
            return oldItem.equals(newItem);
        }
    }
}
