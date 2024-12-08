package com.example.ghettoyelp.viewHolders.AllReviews;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.R;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Each ViewHolder is a Recycler Item
 *      Updating to the displacement-UI
 *      Receiving details from Adapter to display information of Review
 */

public class ViewAllReviewsViewHolder extends RecyclerView.ViewHolder {
    private final TextView restaurantNameTextView, ratingTextView, descriptionTextview;

    private ViewAllReviewsViewHolder(View viewAllReviews){
        super(viewAllReviews);
        // reference to item that will be generated
        restaurantNameTextView = viewAllReviews.findViewById(R.id.restaurantNameReviewTextView);
        ratingTextView = viewAllReviews.findViewById(R.id.restaurantRatingReviewTextView);
        descriptionTextview = viewAllReviews.findViewById(R.id.restaurantDescriptionReviewTextView);
    }

    @SuppressLint("DefaultLocale")
    public void setText(String restaurant, int rating, String description){
        restaurantNameTextView.setText(restaurant);
        descriptionTextview.setText(description);
        ratingTextView.setText(String.format("%d/10", rating));
    }

    // reference to the displacement
    static ViewAllReviewsViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_recycler_item, parent, false);
        return new ViewAllReviewsViewHolder(view);
    }

}
