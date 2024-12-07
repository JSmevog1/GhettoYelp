package com.example.ghettoyelp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.RestaurantRepository;

import java.util.List;

/**
 * @author yusraashar
 * ViewModel class to manage UI-related data for the RestaurantActivity.
 * It provides a clean API for the Activity to interact with the underlying data layer.
 * This ensures data persists across configuration changes (e.g., screen rotations).
 */
public class RestaurantViewModel extends AndroidViewModel {

    private final RestaurantRepository restaurantRepository; // Repository to manage restaurant data
    private final LiveData<List<Restaurant>> allRestaurants; // LiveData containing a list of all restaurants

    /**
     * Constructor for RestaurantViewModel.
     *
     * @param application Application context required for repository initialization.
     */
    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        // Initialize the repository and retrieve the list of all restaurants
        restaurantRepository = new RestaurantRepository(application);
        allRestaurants = restaurantRepository.getAllRestaurants();
    }

    /**
     * Returns a LiveData object containing the list of all restaurants.
     * The data is observed in the Activity to automatically reflect changes in the UI.
     *
     * @return LiveData object containing a list of Restaurant objects.
     */
    public LiveData<List<Restaurant>> getAllRestaurants() {
        return allRestaurants;
    }

    /**
     * Inserts a new restaurant into the database.
     *
     * @param restaurant The Restaurant object to be added to the database.
     */
    public void insertRestaurant(Restaurant restaurant) {
        restaurantRepository.insertRestaurant(restaurant);
    }

    /**
     * Deletes an existing restaurant from the database.
     *
     * @param restaurant The Restaurant object to be removed from the database.
     */
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantRepository.removeRestaurant(restaurant);
    }
}
