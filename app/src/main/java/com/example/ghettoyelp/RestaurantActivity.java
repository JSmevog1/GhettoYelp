package com.example.ghettoyelp;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.RestaurantRepository;

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
        setContentView(R.layout.activity_restaurant);

        // Initialize Repository
        restaurantRepository = new RestaurantRepository(getApplication());

        // Bind UI components
        restaurantNameInput = findViewById(R.id.restaurantNameInput);
        restaurantDescriptionInput = findViewById(R.id.restaurantDescriptionInput);
        addRestaurantButton = findViewById(R.id.addRestaurantButton);
        restaurantRecyclerView = findViewById(R.id.restaurantRecyclerView);

        // Set up RecyclerView
        restaurantAdapter = new RestaurantAdapter();
        restaurantRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        restaurantRecyclerView.setAdapter(restaurantAdapter);

        // Load existing restaurants
        loadRestaurants();

        // Set up Add button
        addRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRestaurant();
            }
        });
    }

    private void loadRestaurants() {
        new Thread(() -> {
            List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();
            runOnUiThread(() -> restaurantAdapter.setRestaurants(restaurants));
        }).start();
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
                Toast.makeText(this, "Restaurant added", Toast.LENGTH_SHORT).show();
                loadRestaurants();
                restaurantNameInput.setText("");
                restaurantDescriptionInput.setText("");
            });
        }).start();
    }
}
