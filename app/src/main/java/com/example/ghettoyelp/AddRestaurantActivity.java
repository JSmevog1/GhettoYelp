package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ghettoyelp.Database.Entities.Restaurant;

/**
 * AddRestaurantActivity is responsible for providing the user interface to add a new restaurant.
 * Users can input the restaurant name, description, and rating. The information is validated
 * and added to the database.
 *
 * @author yusraashar
 */
public class AddRestaurantActivity extends AppCompatActivity {

    // UI components
    private EditText nameInput, descriptionInput, ratingInput;
    private Button addRestaurantButton;

    // ViewModel for interacting with the database
    private RestaurantViewModel restaurantViewModel;

    /**
     * Initializes the activity and sets up UI components, ViewModel, and button click listeners.
     *
     * @param savedInstanceState State information saved from previous instance (if any).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // Bind UI components to their XML counterparts
        nameInput = findViewById(R.id.restaurantNameInput);
        descriptionInput = findViewById(R.id.restaurantDescriptionInput);
        ratingInput = findViewById(R.id.restaurantRatingInput);
        addRestaurantButton = findViewById(R.id.addRestaurantButton);

        // Initialize the ViewModel for database interactions
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        // Set up click listener for the Add Restaurant button
        addRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleAddRestaurant();
            }
        });
    }

    /**
     * Handles the click event for adding a restaurant. Validates the input, and if valid,
     * creates a new restaurant entry and saves it to the database.
     */
    private void handleAddRestaurant() {
        // Retrieve user input
        String name = nameInput.getText().toString();
        String description = descriptionInput.getText().toString();
        String ratingStr = ratingInput.getText().toString();

        // Validate input fields
        if (name.isEmpty() || description.isEmpty() || ratingStr.isEmpty()) {
            Toast.makeText(AddRestaurantActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        double rating;
        try {
            // Parse and validate the rating input
            rating = Double.parseDouble(ratingStr);
            if (rating < 0 || rating > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(AddRestaurantActivity.this, "Rating must be a number between 0 and 10", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a new Restaurant object
        Restaurant restaurant = new Restaurant(name, rating, 0, description);
        restaurant.setName(name);
        restaurant.setDescription(description);
        restaurant.setRating(rating);

        // Insert the restaurant into the database using the ViewModel
        restaurantViewModel.insertRestaurant(restaurant);
        Toast.makeText(AddRestaurantActivity.this, "Restaurant added successfully", Toast.LENGTH_SHORT).show();

        // Clear the input fields for a better user experience
        nameInput.setText("");
        descriptionInput.setText("");
        ratingInput.setText("");
    }

    static Intent AddRestaurantIntentFactory(Context context){
        return new Intent(context, AddRestaurantActivity.class);
    }
}
