package com.example.ghettoyelp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.List;

public class ViewAllRestaurantsActivity extends AppCompatActivity {

    private RecyclerView restaurantRecyclerView;
    private RestaurantAdapter restaurantAdapter;
    private RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_restaurants);

        // Initialize RecyclerView
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setHasFixedSize(true);

        // Initialize Adapter
        restaurantAdapter = new RestaurantAdapter();
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Initialize ViewModel
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        // Set up delete action
        restaurantAdapter.setOnDeleteClickListener(restaurant -> {
            if (restaurant != null) {
                restaurantViewModel.deleteRestaurant(restaurant.getId());
                Toast.makeText(this, "Restaurant deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: Restaurant not found", Toast.LENGTH_SHORT).show();
            }
        });

        // Observe LiveData for restaurant updates
        restaurantViewModel.getAllRestaurants().observe(this, restaurants -> {
            if (restaurants != null && !restaurants.isEmpty()) {
                updateRestaurantList(restaurants);
            } else {
                Toast.makeText(this, "No restaurants to display", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateRestaurantList(List<Restaurant> restaurants) {
        restaurantAdapter.setRestaurants(restaurants);
    }
}
