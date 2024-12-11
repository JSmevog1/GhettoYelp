package com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.R;

public class ManageReviewViewHolder extends RecyclerView.ViewHolder {
    TextView reviewViewItem;
    private ManageReviewViewHolder(View reviewView){
        super(reviewView);
        reviewViewItem = reviewView.findViewById(R.id.reviewsRecyclerItemTextView);
    }

    public void bind (String text){
        reviewViewItem.setText(text);
    }

    static ManageReviewViewHolder create (ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.manage_review_recycler, parent, false);
        return new ManageReviewViewHolder(view);
    }
}
