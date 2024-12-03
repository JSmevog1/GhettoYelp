package com.example.ghettoyelp.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

/**
 * @author Yui Nguyen
 * Last Update: Nov 30, 2024
 * Description:
 *      Data Access Object for Entity Class - Review
 */

@Dao
public interface ReviewDAO {
    // TODO: wait for Review entity class to be created
    // Method to add a new review into database table
    // If there is any conflict, replace with a new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ReviewDAO reviewDAO);

    //@Query("SELECT * FROM " + MainDatabase.REVIEW_TABLE + " ORDER BY ")
    // TODO: add method after entity class - Review is created

    //@Query("DELETE FROM " + MainDatabase.REVIEW_TABLE)
    // TODO: add method to remove all users from database
}
