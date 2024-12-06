package com.example.ghettoyelp.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.MainDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 5th, 2024
 * Description:
 *      Data Access Object for Entity Class - User
 */

@Dao
public interface UserDAO {
    // Method to add a new user into database table
    // If there is any conflict, replace with a new one
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    // METHODS to get data from USER_TABLE
    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " ORDER BY username DESC")
    LiveData<List<User>> getAllUsersLiveData();

    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " ORDER BY username DESC")
    List<User> getAllUsers();

    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " WHERE username == :name")
    LiveData<User> getUserByUsername(String name);

    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " WHERE id == :ID")
    LiveData<User> getUserByID(int ID);

    // METHODS to delete user
    @Delete
    void delete(User user);
    @Query("DELETE FROM " + MainDatabase.USER_TABLE)
    void deleteAll();
}
