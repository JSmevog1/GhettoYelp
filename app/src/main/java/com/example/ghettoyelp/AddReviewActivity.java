package com.example.ghettoyelp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.RestaurantRepository;
import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivityAddReviewBinding;

import java.util.List;

/**
 * @author Yui Nguyen
 * Last Update: Dec 7th, 2024
 * Description:
 *      Activity Page to handle UI operations on Add Review Page
 */

public class AddReviewActivity extends AppCompatActivity {
    private ActivityAddReviewBinding binding;
    private ReviewsRepository repository;

    private int userID;
    private String mRestaurant;
    private int mRating;
    private String mDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddReviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        repository = ReviewsRepository.getRepository(getApplication());
        userID = getIntent().getIntExtra("com.example.ghettoyelp.MAIN_ACTIVITY_USER_ID", -1);

        binding.addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getInformationFromDisplay()){
                    addReview();
                    resetReview();
                }
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                warningMessage();
            }
        });
    }

    private  void warningMessage(){
        // create warning for user
        AlertDialog.Builder builder = new AlertDialog.Builder(AddReviewActivity.this);
        final AlertDialog alertDialog = builder.create();

        // setup for alert dialog
        builder.setMessage("Are you sure you want to go back?\n Any change won't be saved");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                backToMain();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        builder.create().show();
    }

    // return back to main page
    private void backToMain(){
        startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), userID));
    }

    private boolean getInformationFromDisplay(){
        //TODO: check for restaurant existence before adding
        mRestaurant = binding.RestaurantEditText.getText().toString();
        if (!mRestaurant.isEmpty()){
            LiveData<List<Restaurant>> restaurants = RestaurantRepository.getRepository(getApplication()).getAllRestaurants();
            restaurants.observe(this, restaurant ->{
                if(restaurant != null){

                }
            });
        }
        else{
            Toast.makeText(this, "Please enter restaurant you want to review", Toast.LENGTH_SHORT).show();
            return false;
        }


        // TODO: check for Rating range from 0 to 10
        mRating = Integer.parseInt(String.valueOf(binding.RatingEditText.getText()));
        if(mRating < 0 || mRating > 10){
            Toast.makeText(this, "Rating must be from 0 to 10", Toast.LENGTH_SHORT).show();
            return false;
        }

        // description can be emptied
        mDescription = binding.DescriptionEditText.getText().toString();
        return true;
    }

    // check input data before adding into the Review database
    private void addReview(){
        LiveData<User> userLiveData = UserRepository.getRepository(getApplication()).getUserByID(userID);
        userLiveData.observe(this, user -> {
            if (user != null) {
                String username = user.getUsername();
                Review review = new Review(username, mRestaurant, mDescription, mRating);
                repository.insertReview(review);

                //TODO: increment count for restaurant and user
                user.setReviewsCount(user.getReviewsCount() + 1);

                Toast.makeText(this,"Thank you for your review!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // reset display on UI
    private void resetReview(){
        binding.RestaurantEditText.setText("");
        binding.RatingEditText.setText("");
        binding.DescriptionEditText.setText("");
    }

    static Intent AddReviewIntentFactory(Context context){
        return new Intent(context, AddReviewActivity.class);
    }
}
