package com.example.ghettoyelp.Database;

import android.app.Application;

import com.example.ghettoyelp.Database.DAOs.ReviewDAO;

import java.util.ArrayList;

/**
 * @author Yui Nguyen
 * Last Update: Dec 2nd, 2024
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

    // METHODS to READ and WRITE to DAO
    // TODO: add method to retrieve all Review from DAO

    // TODO: add method to insert an Review to DAO

    // TODO: wait for other issues to add other methods
}
