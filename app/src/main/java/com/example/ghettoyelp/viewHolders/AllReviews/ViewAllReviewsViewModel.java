package com.example.ghettoyelp.viewHolders.AllReviews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.ReviewsRepository;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Connecting to Review Repository for live update
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

    public List<Review> getAllReviewsByUsername(String username) {
        return repository.getReviewByUsername(username);
    }

    public List<Review> getAllReviewsByID(int userID) {
        return repository.getReviewByUserID(userID);
    }
}
