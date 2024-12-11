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

/**
 * RestaurantAdapter is responsible for binding restaurant data to a RecyclerView.
 * It displays a list of restaurants with their name, description, and rating, and
 * provides a delete button to remove a restaurant.
 *
 * This adapter also supports a listener interface to handle delete button clicks.
 *
 * @author yusraashar
 */
public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder> {

    // List of restaurants to display
    private List<Restaurant> restaurants = new ArrayList<>();

    // Listener for delete button clicks
    private OnDeleteClickListener onDeleteClickListener;

    /**
     * Interface to handle delete button clicks.
     */
    public interface OnDeleteClickListener {
        void onDeleteClick(Restaurant restaurant);
    }

    /**
     * Sets the delete button click listener.
     *
     * @param listener Listener to handle delete button clicks.
     */
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.onDeleteClickListener = listener;
    }

    /**
     * Creates a new ViewHolder for the RecyclerView.
     *
     * @param parent   The parent ViewGroup.
     * @param viewType The type of the view (unused in this case).
     * @return A new instance of RestaurantViewHolder.
     */
    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_restaurant, parent, false);
        return new RestaurantViewHolder(itemView);
    }

    /**
     * Binds data from a Restaurant object to the ViewHolder.
     *
     * @param holder   The ViewHolder to bind data to.
     * @param position The position of the item in the list.
     */
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

    /**
     * Returns the total number of items in the adapter.
     *
     * @return The number of restaurants in the list.
     */
    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    /**
     * Updates the adapter with a new list of restaurants and refreshes the UI.
     *
     * @param restaurants The new list of restaurants.
     */
    public void setRestaurants(List<Restaurant> restaurants) {
        this.restaurants = restaurants;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for holding references to restaurant item views.
     */
    static class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private final TextView restaurantName;
        private final TextView restaurantDescription;
        private final TextView restaurantRating;
        private final Button deleteButton;

        /**
         * Constructor to initialize views for a single restaurant item.
         *
         * @param itemView The root view of the restaurant item layout.
         */
        public RestaurantViewHolder(@NonNull View itemView) {
            super(itemView);
            restaurantName = itemView.findViewById(R.id.restaurantName);
            restaurantDescription = itemView.findViewById(R.id.restaurantDescription);
            restaurantRating = itemView.findViewById(R.id.restaurantRating);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
