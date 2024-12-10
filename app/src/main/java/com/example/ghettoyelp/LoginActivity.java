package com.example.ghettoyelp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.ReviewsRepository;
import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivityLoginBinding;

/**
 * @author Tariq Kakar
 * Last Update: Dec 4, 2024
 * Description:
 *      This activity is the entry point for user authentification in the app.
 *      It provides a user interface for logging in, including fields for username and password.
 */

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;
    private ReviewsRepository reviewsRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }
        });

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }

    private void verifyUser() {
        String username = binding.userNameLoginEditText.getText().toString();

        if (username.isEmpty()) {
            toastMaker("Username can't be blank.");
            return;
        }

        UserRepository userRepository = UserRepository.getRepository(getApplication());
        assert userRepository != null;
        LiveData<User> userObserver = userRepository.getUserByUsername(username);

        userObserver.observe(this, user -> {
            if(user != null){
                String password = binding.passwordLoginEditText.getText().toString();
                if(password.equals(user.getPassword())){
                    startActivity(MainActivity.mainActivityIntentFactory(getApplicationContext(), user.getId()));
                }else{
                    toastMaker("Invalid password.");
                    binding.passwordLoginEditText.setSelection(0);
                }
            }else{
                toastMaker(String.format("%s is not a valid username.", username));
                binding.userNameLoginEditText.setSelection(0);
            }
        });
    }

    public void toastMaker(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }

    /**
     * Factory method to create an Intent for starting the LoginActivity.
     *
     * @param context is the context used to create the Intent.
     * @return an Intent configured to start the LoginActivity.
     */
    static Intent loginIntentFactory(Context context){
        return new Intent(context, LoginActivity.class);
    }
}