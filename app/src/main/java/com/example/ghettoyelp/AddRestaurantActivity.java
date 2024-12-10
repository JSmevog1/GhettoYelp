package com.example.ghettoyelp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.ghettoyelp.Database.Entities.Restaurant;

public class AddRestaurantActivity extends AppCompatActivity {

    private EditText nameInput, descriptionInput, ratingInput;
    private Button addRestaurantButton;
    private RestaurantViewModel restaurantViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_restaurant);

        // Initialize UI components
        nameInput = findViewById(R.id.restaurantNameInput);
        descriptionInput = findViewById(R.id.restaurantDescriptionInput);
        ratingInput = findViewById(R.id.restaurantRatingInput);
        addRestaurantButton = findViewById(R.id.addRestaurantButton);

        // Initialize ViewModel
        restaurantViewModel = new ViewModelProvider(this).get(RestaurantViewModel.class);

        // Handle Add Restaurant button click
        addRestaurantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameInput.getText().toString();
                String description = descriptionInput.getText().toString();
                String ratingStr = ratingInput.getText().toString();

                if (name.isEmpty() || description.isEmpty() || ratingStr.isEmpty()) {
                    Toast.makeText(AddRestaurantActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                }

                double rating;
                try {
                    rating = Double.parseDouble(ratingStr);
                    if (rating < 0 || rating > 10) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(AddRestaurantActivity.this, "Rating must be a number between 0 and 10", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new Restaurant object and insert it into the database
                Restaurant restaurant = new Restaurant();
                restaurant.setName(name);
                restaurant.setDescription(description);
                restaurant.setRating(rating);

                restaurantViewModel.insertRestaurant(restaurant);
                Toast.makeText(AddRestaurantActivity.this, "Restaurant added successfully", Toast.LENGTH_SHORT).show();

                // Clear the input fields
                nameInput.setText("");
                descriptionInput.setText("");
                ratingInput.setText("");
            }
        });
    }
}
