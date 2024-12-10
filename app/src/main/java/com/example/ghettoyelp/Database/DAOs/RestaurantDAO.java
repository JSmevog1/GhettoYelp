package com.example.ghettoyelp.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.MainDatabase;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 5th, 2024
 * Description:
 *      Data Access Object for Entity Class - Restaurant
 */

@Dao
public interface RestaurantDAO {
    // METHOD to add a restaurant
    // If there is any conflict, replace with a new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Restaurant restaurant);

    // METHOD to get restaurants
    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " ORDER BY name DESC")
    LiveData<List<Restaurant>> getAllRestaurants();

    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE name = :restaurant")
    LiveData<Restaurant> getRestaurantByName(String restaurant);

    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE id = :givenID")
    LiveData<Restaurant> getRestaurantByID(int givenID);

    // METHOD to delete restaurants
    @Delete
    void delete(Restaurant restaurant);

    @Query("DELETE FROM " + MainDatabase.RESTAURANT_TABLE)
    void deleteAll();

    default List<Restaurant> getAllRestaurantsPaginated(int limit, int offset) {
        return null;
    }

    void deleteById(int restaurantId);

    void clearAll();
}
