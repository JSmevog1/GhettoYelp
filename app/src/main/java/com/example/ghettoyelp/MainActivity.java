package com.example.ghettoyelp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.MainDatabase;
import com.example.ghettoyelp.Database.RestaurantRepository;
import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.ghettoyelp.MAIN_ACTIVITY_USER_ID"; // constant for user ID key
    static final String SAVED_INSTANCE_STATE_USERID_KEY = "com.example.ghettoyelp.SAVED_INSTANCE_STATE_USERID_KEY";
    private static final int LOGGED_OUT = -1; // denotes whether a user is logged out or not
    private ActivityMainBinding binding; // binding object for accessing views in the activity's layout
    private int loggedInUserId = LOGGED_OUT;  // the unique identifier tied to each user in the database
    private User user;  // The user logged in to the app
    private ReviewsRepository reviewsRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        reviewsRepository = ReviewsRepository.getRepository(getApplication());
        restaurantRepository = RestaurantRepository.getRepository(getApplication());
        userRepository = UserRepository.getRepository(getApplication());

        loginUser(savedInstanceState);

        if(loggedInUserId == LOGGED_OUT){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }

        updateSharedPreference();

        binding.addReviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddReviewsPage();
            }
        });

        binding.previousReviewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToPreviousReviewsPage();
            }
        });

        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLogoutDialog();
            }
        });

        // moved isAdmin functionality to loginUser
    }

    private void loginUser(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);

        loggedInUserId = sharedPreferences.getInt(getString(R.string.preference_userId_key), LOGGED_OUT);

        if(loggedInUserId == LOGGED_OUT && savedInstanceState != null && savedInstanceState.containsKey(SAVED_INSTANCE_STATE_USERID_KEY)){
            loggedInUserId = savedInstanceState.getInt(SAVED_INSTANCE_STATE_USERID_KEY, LOGGED_OUT);
        }
        if(loggedInUserId == LOGGED_OUT){
            loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
        }
        if(loggedInUserId == LOGGED_OUT){
            return;
        }
        LiveData<User> userObserver = userRepository.getUserByID(loggedInUserId);
        userObserver.observe(this, user -> {
            this.user = user;
            if(user != null){
                invalidateOptionsMenu();
                showAdminButtons(user.isAdmin());
            }
        });
    }

    private void showAdminButtons(boolean isAdmin) {
        if (isAdmin) {
            binding.AdminViewAllUsersButton.setVisibility(View.VISIBLE);
            binding.AdminViewAllUsersButton.setOnClickListener(v -> adminViewAllUsers());

            binding.AdminAddRemoveRestaurantsButton.setVisibility(View.VISIBLE);
            binding.AdminAddRemoveRestaurantsButton.setOnClickListener(v -> adminAddRemoveRestaurants());
        } else {
            binding.AdminViewAllUsersButton.setVisibility(View.GONE);
            binding.AdminAddRemoveRestaurantsButton.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SAVED_INSTANCE_STATE_USERID_KEY, loggedInUserId);
        updateSharedPreference();
    }

    /**
     * Called to inflate the options menu and add items to it.
     * This method inflates the logout menu from the specified menu resource.
     *
     * @param menu The options menu in which you place your items.
     * @return true if the menu is successfully created, otherwise false.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.username_menu_display, menu);
        return true;
    }

    /**
     * Called to prepare the options menu when it is being displayed.
     * This method sets up the logout menu item with the user's username
     * and a click listener to show the logout dialog.
     *
     * @param menu The options menu as last shown or first initialized by
     *             onCreateOptionsMenu().
     *
     * @return true if the menu should be displayed, otherwise false.
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.userNameDisplay);
        item.setVisible(true);
        if(user == null){
            return false;
        }
        item.setTitle(user.getUsername());  // change this once we are able to get the databases working.
                                // it should be user.geUsername as an argument
        return true;
    }

    /**
     * Displays an {@code AlertDialog} prompting the user to either logout of the app
     * or dismiss the alert.
     */
    private void showLogoutDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        final AlertDialog alertDialog = alertBuilder.create();

        alertBuilder.setMessage("Logout?");

        // logs out the user when Logout is clicked
        alertBuilder.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
            }
        });

        // dismiss the logout alert
        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });

        alertBuilder.create().show();
    }

    /**
     * Factory method to create an Intent for starting the MainActivity.
     *
     * @param context is the context used to create the intent.
     * @param userId is the user ID to be passed as an extra in the Intent.
     * @return an Intent configured to start MainActivity with the provided user ID.
     */
    static Intent mainActivityIntentFactory(Context context, int userId){
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }

    //todo: implement these functions once the pages have been created
    private void goToAddReviewsPage() {
        Intent intent = AddReviewActivity.AddReviewIntentFactory(getApplicationContext());
        intent.putExtra(MAIN_ACTIVITY_USER_ID, loggedInUserId);
        startActivity(intent);
    }

    private void goToPreviousReviewsPage() {
        Intent intent = ViewAllReviewsActivity.ViewAllReviewsIntentFactory(getApplicationContext());
        intent.putExtra(MAIN_ACTIVITY_USER_ID, loggedInUserId);
        startActivity(intent);
    }

    /**
     * Logs out the user by starting {@code LoginActivity} redirecting the user to the login screen.
     */
    private void logout() {
        loggedInUserId = LOGGED_OUT;
        updateSharedPreference();
        getIntent().putExtra(MAIN_ACTIVITY_USER_ID, loggedInUserId);
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
    }

    private void updateSharedPreference(){
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(getString(R.string.preference_file_key),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        sharedPrefEditor.putInt(getString(R.string.preference_userId_key), loggedInUserId);
        sharedPrefEditor.apply();
    }

    /**
     * Takes the admin to the view all users page by starting {@code ViewAllUsersActivity}
     */
    private void adminViewAllUsers(){
        startActivity(ViewAllUsersActivity.ViewAllUsersIntentFactory(getApplicationContext()));
    }

    private void adminAddRemoveRestaurants(){

    }
}