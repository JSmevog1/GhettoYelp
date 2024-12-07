package com.example.ghettoyelp.viewHolders.Review;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.ReviewsRepository;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Connecting to Review Repository for live update
 *      Not really necessary since Add and Display are 2 different activities
 */

public class ViewAllReviewsViewModel extends AndroidViewModel {
    private final ReviewsRepository repository;

    public ViewAllReviewsViewModel(Application application){
        super(application);
        repository = ReviewsRepository.getRepository(application);
    }

    public void insert(Review review){
        repository.insertReview(review);
    }

    public LiveData<List<Review>> getAllReviewsByUsername(String username) {
        return repository.getReviewByUsername(username);
    }

    public LiveData<List<Review>> getAllReviewsByID(int userID) {
        return repository.getReviewByUserID(userID);
    }
}
