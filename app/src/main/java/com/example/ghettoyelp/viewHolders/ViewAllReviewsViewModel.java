package com.example.ghettoyelp.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.ReviewsRepository;

import java.util.List;

public class ViewAllReviewsViewModel extends AndroidViewModel {
    private ReviewsRepository repository;

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
