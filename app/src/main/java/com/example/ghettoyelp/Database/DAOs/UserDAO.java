package com.example.ghettoyelp.Database.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.MainDatabase;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 3rd, 2024
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

    // METHODS to get users from datatable
    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " ORDER BY username")
    List<User> getAllUsers ();

    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " WHERE username == :userName")
    User getUserByUserName(String userName);

    @Query("SELECT * FROM " + MainDatabase.USER_TABLE + " WHERE id == :userID")
    User getUserByUserID(int userID);

    // METHODS to delete user from datatable
    @Delete
    void delete(User user);
    @Query("DELETE FROM " + MainDatabase.USER_TABLE)
    void deleteAll();
}
