package com.example.ghettoyelp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    private List<Restaurant> restaurants = new ArrayList<>();
    private OnDeleteClickListener onDeleteClickListener;

    // Interface for delete button click listener
    public interface OnDeleteClickListener {
        void onDeleteClick(Restaurant restaurant);
    }

    // Set delete button listener
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        Restaurant currentRestaurant = restaurants.get(position);

        // Bind data to the views
        holder.restaurantName.setText(currentRestaurant.getName());
        holder.restaurantDescription.setText(currentRestaurant.getDescription());
        holder.restaurantRating.setText(String.format("Rating: %.1f/10", currentRestaurant.getRating()));

        // Handle delete button click
        holder.deleteButton.setOnClickListener(v -> {
            if (onDeleteClickListener != null) {
                onDeleteClickListener.onDeleteClick(currentRestaurant);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    // Update the adapter with a new list of restaurants
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    // ViewHolder for the RecyclerView
    static class RestaurantViewHolder extends RecyclerView.ViewHolder {
        private final TextView restaurantName;
        private final TextView restaurantDescription;
        private final TextView restaurantRating;
        private final Button deleteButton;

        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantDescription = itemView.findViewById(R.id.restaurantDescription);
            restaurantRating = itemView.findViewById(R.id.restaurantRating);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
