package com.example.ghettoyelp.Database;

import android.app.Application;
import android.util.Log;

import com.example.ghettoyelp.Database.DAOs.ReviewDAO;
import com.example.ghettoyelp.Database.Entities.Review;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author Yui Nguyen
 * Last Update: Dec 3nd, 2024
 * Description:
 *      A repository to read and write data to ReviewDAO
 */

public class ReviewsRepository {
    // VARIABLES for repository
    private static ReviewsRepository repository;

    // VARIABLES for DAO
    // TODO: wait for entity class - Review to be created
    private final ReviewDAO reviewDAO;
    private ArrayList<Review> allReviews;

    // CONSTRUCTOR
    public ReviewsRepository(Application application){
        MainDatabase database = MainDatabase.getDatabase(application);
        reviewDAO = database.reviewDAO();
    }

    public static ReviewsRepository getRepository(Application application){
        if(repository != null)
            return repository;

        Future<ReviewsRepository> future = MainDatabase.databaseExecutor.submit(
                new Callable<ReviewsRepository>() {
                    @Override
                    public ReviewsRepository call() throws Exception {
                        return new ReviewsRepository(application);
                    }
                }
        );
        try{
            return future.get();
        }  catch (InterruptedException | ExecutionException e) {
            //Log.i(MainActivity.TAG, "Problem in the GymLogRepository");
        }
        return null;
    }

    // METHODS to READ and WRITE to DAO
    // get all reviews
    public ArrayList<Review> getAllReviews(){
        Future<ArrayList<Review>> future = MainDatabase.databaseExecutor.submit(
                new Callable<ArrayList<Review>>() {
                    @Override
                    public ArrayList<Review> call() throws Exception {
                        return (ArrayList<Review>) reviewDAO.getAllReviews();
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

    // get reviews by username
    public ArrayList<Review> getReviewByUsername(String username){
        Future<ArrayList<Review>> future = MainDatabase.databaseExecutor.submit(
                new Callable<ArrayList<Review>>() {
                    @Override
                    public ArrayList<Review> call() throws Exception {
                        return (ArrayList<Review>) reviewDAO.getReviewByUsername(username);
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

    // get reviews by restaurant's name
    public ArrayList<Review> getReviewByRestaurant(String restaurant){
        Future<ArrayList<Review>> future = MainDatabase.databaseExecutor.submit(
                new Callable<ArrayList<Review>>() {
                    @Override
                    public ArrayList<Review> call() throws Exception {
                        return (ArrayList<Review>) reviewDAO.getReviewByRestaurant(restaurant);
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

    // add new
    public void insertReview(Review newReview){
        MainDatabase.databaseExecutor.execute(()->{
            reviewDAO.insert(newReview);
        });
    }

    // delete review
    public void deleteReview(Review review){
        MainDatabase.databaseExecutor.execute(()->{
            reviewDAO.insert(review);
        });
    }

    // TODO: wait for other issues to add other methods
}
