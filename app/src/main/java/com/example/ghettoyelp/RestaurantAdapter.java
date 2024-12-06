package com.example.ghettoyelp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;
/**
 * @author yusraashar
 *  Activity class for restaurant_table
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants = new ArrayList<>();

    // Method to update the list of restaurants and refresh the RecyclerView
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the custom layout for each item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        // Get the restaurant object for the current position
        Restaurant restaurant = restaurants.get(position);

        // Bind the data to the views in the ViewHolder
        holder.nameTextView.setText(restaurant.getName());
        holder.descriptionTextView.setText(restaurant.getDescription());
        holder.reviewCountTextView.setText(restaurant.getTotalReviews() + " reviews");
        holder.ratingTextView.setText(String.format("%.1f/10", restaurant.getRating()));
    }

    @Override
    public int getItemCount() {
        return restaurants.size(); // Return the size of the restaurant list
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView reviewCountTextView;
        TextView ratingTextView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);

            // Bind the views to their respective IDs from item_restaurant.xml
            nameTextView = itemView.findViewById(R.id.restaurantName);
            descriptionTextView = itemView.findViewById(R.id.restaurantDescription);
            reviewCountTextView = itemView.findViewById(R.id.reviewCount);
            ratingTextView = itemView.findViewById(R.id.restaurantRating);
        }
    }
}
