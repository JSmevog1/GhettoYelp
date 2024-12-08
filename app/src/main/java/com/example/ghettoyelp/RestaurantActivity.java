package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.List;

/**
 * @author yusraashar
 * Dynamically updates the UI with a maximum of 5 restaurants displayed at a time,
 * using LiveData to observe changes in the data.
 *
 * This activity demonstrates the use of Android Architecture Components
 * such as LiveData and ViewModel to ensure the UI stays updated with real-time changes.
 *
 * Activity to manage and display a list of restaurants.
 */
public class RestaurantActivity extends AppCompatActivity {

    // Layout to dynamically add restaurant views
    private LinearLayout restaurantListLayout;

    // ViewModel to manage restaurant data
    private RestaurantViewModel restaurantViewModel;

    /**
     * Called when the activity is first created.
     * Sets up the UI components and initializes the ViewModel to observe data changes.
     *
     * @param savedInstanceState Saved state of the activity (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        // Initialize UI components
        restaurantListLayout = findViewById(R.id.restaurantListLayout);

        // Initialize ViewModel to manage restaurant data
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        // Observe LiveData for restaurant changes
        restaurantViewModel.getAllRestaurants().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                // Update the UI when data changes
                updateRestaurantList(restaurants);
            }
        });
    }

    /**
     * Updates the restaurant list dynamically in the UI.
     * Limits the display to a maximum of 5 restaurants.
     *
     * @param restaurants List of restaurants to display.
     */
    private void updateRestaurantList(List<Restaurant> restaurants) {
        restaurantListLayout.removeAllViews(); // Clear existing views

        // Limit to 5 restaurants for display
        int count = Math.min(restaurants.size(), 5);
        for (int i = 0; i < count; i++) {
            Restaurant restaurant = restaurants.get(i);
            addRestaurantBox(
                    restaurant.getName(),
                    restaurant.getDescription(),
                    String.format("%.1f/10", restaurant.getRating())
            );
        }

        // If no restaurants, display a toast message
        if (restaurants.isEmpty()) {
            Toast.makeText(this, "No restaurants available", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Dynamically creates a restaurant box and adds it to the LinearLayout.
     *
     * @param name        Name of the restaurant.
     * @param description Description of the restaurant.
     * @param rating      Rating of the restaurant (formatted as a string).
     */
    private void addRestaurantBox(String name, String description, String rating) {
        // Inflate the item_restaurant.xml layout
        View restaurantBox = LayoutInflater.from(this).inflate(R.layout.item_restaurant, restaurantListLayout, false);

        // Set restaurant data into the views
        TextView nameTextView = restaurantBox.findViewById(R.id.restaurantName);
        TextView descriptionTextView = restaurantBox.findViewById(R.id.restaurantDescription);
        TextView ratingTextView = restaurantBox.findViewById(R.id.restaurantRating);

        nameTextView.setText(name);
        descriptionTextView.setText(description);
        ratingTextView.setText(rating);

        // Add the inflated view to the LinearLayout
        restaurantListLayout.addView(restaurantBox);
    }

    static Intent RestaurantIntentFactory(Context context){
        return new Intent(context, RestaurantActivity.class);
    }
}
