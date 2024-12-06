package com.example.ghettoyelp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.DAOs.RestaurantDAO;
import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 2nd, 2024
 * Description:
 *      A repository to read and write data to RestaurantDAO
 */

public class RestaurantRepository {
    // VARIABLES for repository
    private static RestaurantRepository repository;

    // VARIABLES for DAO
    // TODO: wait for entity class - Restaurant to be created
    private final RestaurantDAO restaurantDAO;
    private ArrayList<Restaurant> allUsers;

    // CONSTRUCTOR
    public RestaurantRepository(Application application){
        MainDatabase database = MainDatabase.getDatabase(application);
        restaurantDAO = database.restaurantDAO();
    }

    public void insertRestaurant(Restaurant newRestaurant) {
    }

    public List<Restaurant> getAllRestaurants() {
        return java.util.Collections.emptyList();
    }

    public LiveData<List<Restaurant>> getAllRestaurantsLiveData() {
        return null;
    }

    // METHODS to READ and WRITE to DAO
    // TODO: add method to retrieve all restaurants from DAO

    // TODO: add method to insert an restaurant to DAO

    // TODO: wait for other issues to add other methods
}
