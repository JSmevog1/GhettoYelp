package com.example.ghettoyelp.Database.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.MainDatabase;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Nov 30, 2024
 * Description:
 *      Data Access Object for Entity Class - User
 */

@Dao
public interface UserDAO {
    // TODO: wait for User entity class to be created
    // Method to add a new user into database table
    // If there is any conflict, replace with a new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);


    //@Query("SELECT * FROM " + MainDatabase.USER_TABLE + " ORDER BY username")
    // TODO: add method after entity class - User is created

    @Query("DELETE FROM " + MainDatabase.USER_TABLE)
    void deleteAll();
}
