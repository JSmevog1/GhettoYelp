package com.example.ghettoyelp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.List;

/**
 * ViewAllRestaurantsActivity is responsible for displaying a list of all restaurants in the app.
 * Restaurants are displayed using a RecyclerView, and users can delete a restaurant if needed.
 * It observes LiveData from the RestaurantViewModel for real-time updates to the restaurant list.
 *
 * @author yusraashar
 */
public class ViewAllRestaurantsActivity extends AppCompatActivity {

    // UI components
    private RecyclerView restaurantRecyclerView;
    private RestaurantAdapter restaurantAdapter;

    // ViewModel for database interaction
    private RestaurantViewModel restaurantViewModel;

    /**
     * Initializes the activity and sets up the RecyclerView, ViewModel, and LiveData observers.
     *
     * @param savedInstanceState State information saved from the previous instance (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_restaurants);

        // Bind RecyclerView from the layout
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setHasFixedSize(true);

        // Initialize the adapter for RecyclerView
        restaurantAdapter = new RestaurantAdapter();
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Initialize the ViewModel for restaurant data
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        // Set up delete action for restaurants
        restaurantAdapter.setOnDeleteClickListener(restaurant -> {
            if (restaurant != null) {
                // Delete the selected restaurant using ViewModel
                //restaurantViewModel.deleteRestaurant(restaurant.getId());

                Toast.makeText(this, "Restaurant deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: Restaurant not found", Toast.LENGTH_SHORT).show();
            }
        });

        // Observe LiveData for updates to the restaurant list
        restaurantViewModel.getAllRestaurants().observe(this, restaurants -> {
            if (restaurants != null && !restaurants.isEmpty()) {
                // Update RecyclerView with the new list of restaurants
                updateRestaurantList(restaurants);
            } else {
                Toast.makeText(this, "No restaurants to display", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Updates the RecyclerView with a new list of restaurants.
     *
     * @param restaurants List of restaurants to display in the RecyclerView.
     */
    private void updateRestaurantList(List<Restaurant> restaurants) {
        restaurantAdapter.setRestaurants(restaurants);
    }
}
