package com.example.ghettoyelp.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.MainDatabase;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 3rd, 2024
 * Description:
 *      Data Access Object for Entity Class - Restaurant
 */

@Dao
public interface RestaurantDAO {
    // TODO: wait for Restaurant entity class to be created
    // Method to add a new restaurant into database table
    // If there is any conflict, replace with a new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Restaurant restaurant);

    // METHODS to get restaurants from datatable
    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " ORDER BY username")
    List<User> getAllRestaurants ();

    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE username == :name")
    User getRestaurantByName(String name);

    @Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " WHERE id == :ID")
    User getRestaurantByID(int ID);

    // METHODS to delete restaurant from datatable
    @Delete
    void delete(Restaurant restaurant);
    @Query("DELETE FROM " + MainDatabase.RESTAURANT_TABLE)
    void deleteAll();
}
