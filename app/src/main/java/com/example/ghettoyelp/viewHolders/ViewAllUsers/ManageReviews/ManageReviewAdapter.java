package com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.ghettoyelp.Database.Entities.Review;

public class ManageReviewAdapter extends ListAdapter<Review, ManageReviewViewHolder> {
    public ManageReviewAdapter(@NonNull DiffUtil.ItemCallback<Review> diffCallBack){
        super(diffCallBack);
    }

    @NonNull
    @Override
    public ManageReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        return ManageReviewViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ManageReviewViewHolder holder, int position) {
        Review current = getItem(position);
        holder.bind(current.toString());
    }

    public static class ManageReviewDiff extends DiffUtil.ItemCallback<Review>{
        @Override
        public boolean areItemsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Review oldItem, @NonNull Review newItem) {
            return oldItem.equals(newItem);
        }
    }
}
