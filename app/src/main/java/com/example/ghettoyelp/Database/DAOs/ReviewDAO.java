package com.example.ghettoyelp.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.MainDatabase;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 5th, 2024
 * Description:
 *      Data Access Object for Entity Class - Review
 */

@Dao
public interface ReviewDAO {
    // Method to add a new review into database table
    // If there is any conflict, replace with a new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Review review);

    // methods to get reviews
    @Query("SELECT * FROM " + MainDatabase.REVIEWS_TABLE)
    LiveData<List<Review>> getAllReviews();

    @Query("SELECT * FROM " + MainDatabase.REVIEWS_TABLE + " WHERE username == :name")
    LiveData<List<Review>> getReviewByUsername(String name);

    @Query("SELECT * FROM " + MainDatabase.REVIEWS_TABLE + " WHERE username == :restaurant")
    LiveData<List<Review>> getReviewByRestaurant(String restaurant);

    // methods to delete reviews
    @Delete
    void delete(Review review);
    @Query("DELETE FROM " + MainDatabase.REVIEWS_TABLE)
    void deleteAll();

}
