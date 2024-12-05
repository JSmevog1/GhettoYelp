package com.example.ghettoyelp.Database;

import android.app.Application;


import com.example.ghettoyelp.Database.DAOs.UserDAO;
import com.example.ghettoyelp.Database.Entities.User;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Yui Nguyen
 * Last Update: Dec 4th, 2024
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

    // METHODS to READ and WRITE to DAO
    // Get all users
    public ArrayList<User> getAllUsers(){
        Future<ArrayList<User>> future = MainDatabase.databaseExecutor.submit(
                new Callable<ArrayList<User>>() {
                    @Override
                    public ArrayList<User> call() throws Exception {
                        return (ArrayList<User>) userDAO.getAllUsers();
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            //Log.i(MainActivity.TAG, "Problem in the repository - getAllLogs");
        }
        return null;
    }

    public User getUserByUsername(String username){
        Future<User> future = MainDatabase.databaseExecutor.submit(
                new Callable<User>() {
                    @Override
                    public User call() throws Exception {
                        return userDAO.getUserByUsername(username);
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            //Log.i(MainActivity.TAG, "Problem in the repository - getAllLogs");
        }
        return null;
    }

    public User getUserByID(int ID){
        Future<User> future = MainDatabase.databaseExecutor.submit(
                new Callable<User>() {
                    @Override
                    public User call() throws Exception {
                        return userDAO.getUserByID(ID);
                    }
                }
        );
        try{
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            //Log.i(MainActivity.TAG, "Problem in the repository - getAllLogs");
        }
        return null;
    }

    // TODO: wait for other issues to add other methods

}
