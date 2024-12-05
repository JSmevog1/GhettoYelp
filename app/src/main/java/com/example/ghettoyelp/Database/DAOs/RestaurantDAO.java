package com.example.ghettoyelp.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.ghettoyelp.Database.Entities.Restaurant;

/**
 * @author Yui Nguyen
 * Last Update: Nov 30, 2024
 * Description:
 *      Data Access Object for Entity Class - Restaurant
 */

@Dao
public interface RestaurantDAO {
    // TODO: wait for Restaurant entity class to be created
    // Method to add a new restaurant into database table
    // If there is any conflict, replace with a new one
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    void insert(Restaurant restaurant);

    //@Query("SELECT * FROM " + MainDatabase.RESTAURANT_TABLE + " ORDER BY ")
    // TODO: add method after entity class - Restaurant is created

    //@Query("DELETE FROM " + MainDatabase.RESTAURANT_TABLE)
    // TODO: add method to remove all users from database
}
