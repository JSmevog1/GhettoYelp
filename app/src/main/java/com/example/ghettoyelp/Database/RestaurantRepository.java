package com.example.ghettoyelp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.DAOs.RestaurantDAO;
import com.example.ghettoyelp.Database.Entities.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Yui Nguyen
 * Last Update: Dec 5th, 2024
 * Description:
 *      A repository to read and write data to RestaurantDAO
 */

public class RestaurantRepository {
    // VARIABLES for repository
    private static RestaurantRepository repository;

    // VARIABLES for DAO
    private final RestaurantDAO restaurantDAO;
    private ArrayList<Restaurant> allUsers;

    // CONSTRUCTOR
    public RestaurantRepository(Application application){
        MainDatabase database = MainDatabase.getDatabase(application);
        restaurantDAO = database.restaurantDAO();
    }

    // METHOD to get this repository
    public static RestaurantRepository getRepository(Application application){
        if(repository != null)
            return repository;

        Future<RestaurantRepository> future = MainDatabase.databaseExecutor.submit(
                new Callable<RestaurantRepository>() {
                    @Override
                    public RestaurantRepository call() throws Exception {
                        return new RestaurantRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }  catch (InterruptedException | ExecutionException e) {
            //Log.i(MainActivity.TAG, "Problem in the ...");
        }
        return null;
    }

    // METHODS to add and delete restaurant
    public void insertRestaurant(Restaurant newRestaurant) {
        restaurantDAO.insert(newRestaurant);
    }

    public void removeRestaurant(Restaurant restaurant){
        restaurantDAO.delete(restaurant);

    }

    // METHODS to get restaurants
    public LiveData<List<Restaurant>> getAllRestaurants() {
        return restaurantDAO.getAllRestaurants();
    }

    public LiveData<Restaurant> getRestaurantByName(String name){
        return restaurantDAO.getRestaurantByName(name);
    }

    public LiveData<Restaurant> getRestaurantByID(int ID){
        return restaurantDAO.getRestaurantByID(ID);
    }

    // TODO: wait for other issues to add other methods
}
