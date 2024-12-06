package com.example.ghettoyelp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String MAIN_ACTIVITY_USER_ID = "com.example.ghettoyelp.MAIN_ACTIVITY_USER_ID"; // constant for user ID key
    private static final int LOGGED_OUT = -1; // denotes whether a user is logged out or not
    private ActivityMainBinding binding; // binding object for accessing views in the activity's layout
    private int loggedInUserId = LOGGED_OUT;  // the unique identifier tied to each user in the database
    private User user;  // The user logged in to the app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginUser();

        invalidateOptionsMenu();

        if(loggedInUserId == LOGGED_OUT){
            Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
            startActivity(intent);
        }

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
                logout();
            }
        });

        if (true){
            binding.AdminViewAllUsersButton.setVisibility(View.VISIBLE);
            binding.AdminViewAllUsersButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adminViewAllUsers();
                }
            });
            binding.AdminAddRemoveRestaurantsButton.setVisibility(View.VISIBLE);
            binding.AdminAddRemoveRestaurantsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    adminAddRemoveRestaurants();
                }
            });
        }
    }

    //todo: complete login method
    private void loginUser() {
        // if there is no intent to start activity e.g. first time the app loads
        // then we go to login page to verify user
        user = new User("user", "user"); // dummy arguments for now
        loggedInUserId = getIntent().getIntExtra(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
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
        inflater.inflate(R.menu.logout_menu, menu);
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
        MenuItem item = menu.findItem(R.id.logoutMenuItem);
        item.setVisible(true);
        item.setTitle(user.getUsername());
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(@NonNull MenuItem item) {
                showLogoutDialog();
                return false;
            }
        });
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
    }

    private void goToPreviousReviewsPage() {
    }

    /**
     * Logs out the user by starting {@code LoginActivity} redirecting the user to the login screen.
     */
    private void logout() {
        startActivity(LoginActivity.loginIntentFactory(getApplicationContext()));
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