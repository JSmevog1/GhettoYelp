package com.example.ghettoyelp.Database;

import android.app.Application;


import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.DAOs.UserDAO;
import com.example.ghettoyelp.Database.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Yui Nguyen
 * Last Update: Dec 5th, 2024
 * Description:
 *      A repository to read and write data to UserDAO
 */

public class UserRepository {
    // VARIABLES for repository
    private static UserRepository repository;

    // VARIABLES for DAO
    private final UserDAO userDAO;
    private ArrayList<User> allUsers;

    // CONSTRUCTOR
    public UserRepository(Application application){
        MainDatabase database = MainDatabase.getDatabase(application);
        userDAO = database.userDAO();
    }

    // METHODS to ADD and DELETE from DAO
    public void insertUser(User user){
        MainDatabase.databaseExecutor.execute(()->{
            userDAO.insert(user);
        });
    }

    public void removeUser(User user){
        MainDatabase.databaseExecutor.execute(()->{
            userDAO.delete(user);
        });
    }

    // METHODS to READ and WRITE to DAO
    // Get all users
    public LiveData<List<User>> getAllUsersLiveData(){
        return userDAO.getAllUsersLiveData();
    }

    public List<User> getAllUsers(){
        return userDAO.getAllUsers();
    }

    public static UserRepository getRepository(Application application){
        if(repository != null)
            return repository;

        Future<UserRepository> future = MainDatabase.databaseExecutor.submit(
                new Callable<UserRepository>() {
                    @Override
                    public UserRepository call() throws Exception {
                        return new UserRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }  catch (InterruptedException | ExecutionException e) {
            //Log.i(MainActivity.TAG, "Problem in the ...");
        }
        return null;
    }

    public LiveData<User> getUserByUsername(String username){
        return userDAO.getUserByUsernameLiveData(username);
    }

    public User getUserByName(String username){
        return userDAO.getUserByUsername(username);
    }

    public LiveData<User> getUserByIDLiveData(int userID){
        return userDAO.getUserByIDLiveData(userID);
    }

    public User getUserByID(int userID){
        return userDAO.getUserByID(userID);
    }

    // METHODS to update data
    public void updateReviewCount(String username, int count){
        userDAO.updateReview(username, count);
    }

    // TODO: wait for other issues to add other methods
}
