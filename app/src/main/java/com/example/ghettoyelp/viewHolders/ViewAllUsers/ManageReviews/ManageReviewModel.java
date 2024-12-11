package com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;

import java.util.List;

public class ManageReviewModel extends AndroidViewModel {
    private final ReviewsRepository reviewsRepository;

    private final UserRepository userRepository;


    public ManageReviewModel(Application application){
        super(application);
        reviewsRepository = ReviewsRepository.getRepository(application);
        userRepository = UserRepository.getRepository(application);
    }

    public LiveData<List<Review>> getAllReviews(){
        return reviewsRepository.getAllReviewsLiveData();
    }

    public void deleteReview(Review review) {
        User user = userRepository.getUserByName(review.getUsername());
        userRepository.updateReviewCount(user.getUsername(), user.getReviewsCount()-1);
        reviewsRepository.deleteReview(review);
    }


}
