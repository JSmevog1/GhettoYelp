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

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants = new ArrayList<>();

    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant restaurant = restaurants.get(position);
        holder.nameTextView.setText(restaurant.getName());
        holder.descriptionTextView.setText(restaurant.getDescription());
        holder.reviewCountTextView.setText(restaurant.getTotalReviews() + " reviews");
        holder.ratingTextView.setText(String.format("%.1f/10", restaurant.getRating()));
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView descriptionTextView;
        TextView reviewCountTextView;
        TextView ratingTextView;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.restaurantName);
            descriptionTextView = itemView.findViewById(R.id.restaurantDescription);
            reviewCountTextView = itemView.findViewById(R.id.reviewCount);
            ratingTextView = itemView.findViewById(R.id.restaurantRating);
        }
    }
}
