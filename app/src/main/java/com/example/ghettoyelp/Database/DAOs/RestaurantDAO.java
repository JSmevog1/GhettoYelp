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
    LiveData<Restaurant> getRestaurantByNameLiveData(String restaurant);

    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE name = :restaurant")
    Restaurant getRestaurantByName(String restaurant);

    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE id = :givenID")
    LiveData<Restaurant> getRestaurantByID(int givenID);

    // METHOD to update information
    @Query(" UPDATE " + MainDatabase.RESTAURANT_TABLE + " SET totalReviews = :count WHERE name == :name")
    void updateReview(String name, int count);

    @Query(" UPDATE " + MainDatabase.RESTAURANT_TABLE + " SET rating = :rating WHERE name == :name")
    void updateRating(String name, double rating);

    @Query(" UPDATE " + MainDatabase.RESTAURANT_TABLE + " SET totalReviews = :count WHERE name == :name")
    void updateRestaurantReviewCount(String name, int count);
    // METHOD to delete restaurants
    @Delete
    void delete(Restaurant restaurant);

    @Query("DELETE FROM " + MainDatabase.RESTAURANT_TABLE)
    void deleteAll();

    default List<Restaurant> getAllRestaurantsPaginated(int limit, int offset) {
        return null;
    }

    @Query("DELETE FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE id = :restaurantId")
    void deleteById(int restaurantId);

    @Query("DELETE FROM " + MainDatabase.RESTAURANT_TABLE)
    void clearAll();


}
