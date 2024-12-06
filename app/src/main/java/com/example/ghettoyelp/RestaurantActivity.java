package com.example.ghettoyelp;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class RestaurantActivity extends AppCompatActivity {

    private RestaurantRepository restaurantRepository;
    private RestaurantAdapter restaurantAdapter;
    private EditText restaurantNameInput;
    private EditText restaurantDescriptionInput;
    private Button addRestaurantButton;
    private RecyclerView restaurantRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // Initialize Repository
        restaurantRepository = new RestaurantRepository(getApplication());

        // Bind UI components
        restaurantNameInput = findViewById(R.id.restaurantNameInput);
        restaurantDescriptionInput = findViewById(R.id.restaurantDescriptionInput);
        addRestaurantButton = findViewById(R.id.addRestaurantButton);
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);

        // Set up RecyclerView
        setupRecyclerView();

        // Add dummy restaurants
        addDummyRestaurants();

        // Observe LiveData for real-time updates
        observeLiveData();

        // Set up Add button click listener
        addRestaurantButton.setOnClickListener(v -> addRestaurant());
    }

    private void setupRecyclerView() {
        restaurantAdapter = new RestaurantAdapter();
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setAdapter(restaurantAdapter);
    }

    private void observeLiveData() {
        // Observe the LiveData from the repository
        restaurantRepository.getAllRestaurantsLiveData().observe(this, new Observer<List<Restaurant>>() {
            @Override
            public void onChanged(List<Restaurant> restaurants) {
                // Update the RecyclerView when the data changes
                restaurantAdapter.setRestaurants(restaurants);
            }
        });
    }

    private void addRestaurant() {
        String name = restaurantNameInput.getText().toString().trim();
        String description = restaurantDescriptionInput.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Restaurant newRestaurant = new Restaurant(name, 0.0, 0, description);

        new Thread(() -> {
            restaurantRepository.insertRestaurant(newRestaurant);
            runOnUiThread(() -> {
                Toast.makeText(this, "Restaurant added successfully!", Toast.LENGTH_SHORT).show();
                restaurantNameInput.setText("");
                restaurantDescriptionInput.setText("");
            });
        }).start();
    }

    private void addDummyRestaurants() {
        // Add dummy data for initial display
        new Thread(() -> {
            List<Restaurant> dummyRestaurants = new ArrayList<>();
            dummyRestaurants.add(new Restaurant("Restaurant 1", 8.5, 100, "A cozy place to dine."));
            dummyRestaurants.add(new Restaurant("Restaurant 2", 9.0, 200, "Fine dining experience."));
            dummyRestaurants.add(new Restaurant("Restaurant 3", 7.5, 50, "Affordable meals for all."));
            dummyRestaurants.add(new Restaurant("Restaurant 4", 8.0, 120, "Casual dining at its best."));

            for (Restaurant restaurant : dummyRestaurants) {
                restaurantRepository.insertRestaurant(restaurant);
            }
        }).start();
    }
}
