package com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.ReviewsRepository;

import java.util.List;

public class ManageReviewModel extends AndroidViewModel {
    private final ReviewsRepository repository;

    public ManageReviewModel(Application application){
        super(application);
        repository = ReviewsRepository.getRepository(application);
    }

    public LiveData<List<Review>> getAllReviews(){
        return repository.getAllReviewsLiveData();
    }

    public void deleteReview(Review review) {
        repository.deleteReview(review);
    }

}
