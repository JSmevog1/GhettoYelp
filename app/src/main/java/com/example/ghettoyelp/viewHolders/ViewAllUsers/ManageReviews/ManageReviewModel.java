package com.example.ghettoyelp.viewHolders.ViewAllUsers.ManageReviews;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.RestaurantRepository;
import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;

import java.util.List;

/**
 * @author Jason Smevog
 * view model for manageReviewActivity that holds the methods for the implementation of the recycler view
 */

public class ManageReviewModel extends AndroidViewModel {
    private final ReviewsRepository reviewsRepository;

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;


    public ManageReviewModel(Application application){
        super(application);
        reviewsRepository = ReviewsRepository.getRepository(application);
        userRepository = UserRepository.getRepository(application);
        restaurantRepository = RestaurantRepository.getRepository(application);
    }

    public LiveData<List<Review>> getAllReviews(){
        return reviewsRepository.getAllReviewsLiveData();
    }

    public void deleteReview(Review review) {
        User user = userRepository.getUserByName(review.getUsername());
        Restaurant restaurant = restaurantRepository.getRestaurantByName(review.getRestaurant());
        userRepository.updateReviewCount(user.getUsername(), user.getReviewsCount()-1);
        restaurantRepository.updateRestaurantReviewCount(restaurant.getName(), restaurant.getTotalReviews()-1);
        reviewsRepository.deleteReview(review);
    }


}
