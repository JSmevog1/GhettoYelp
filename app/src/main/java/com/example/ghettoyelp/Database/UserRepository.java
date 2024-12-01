package com.example.ghettoyelp.Database;

import android.app.Application;


import java.util.ArrayList;

/**
 * @author Yui Nguyen
 * Last Update: Nov 30, 2024
 * Description:
 *      A repository to read and write data to UserDAO
 */

public class UserRepository {
    // VARIABLES for repository
    private static UserRepository repository;

    // VARIABLES for DAO
    // TODO: wait for entity class - User to be created
    private final UserDAO userDAO;
    private ArrayList<User> allUsers;

    // CONSTRUCTOR
    public UserRepository(Application application){
        MainDatabase database = MainDatabase.getDatabase(application);
    }

    // METHODS to READ and WRITE to DAO
    // TODO: add method to retrieve all user from DAO

    // TODO: add method to insert an user to DAO

    // TODO: wait for other issues to add other methods

}
