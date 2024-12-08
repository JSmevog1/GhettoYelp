package com.example.ghettoyelp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.UserRepository;
import com.example.ghettoyelp.databinding.ActivitySignupBinding;

public class SignupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInUser();
            }
        });
    }

    private void signInUser() {
        String username = binding.userNameLoginEditText.getText().toString();
        String password = binding.passwordLoginEditText.getText().toString();
        userRepository = UserRepository.getRepository(getApplication());

        if(username.isEmpty() || password.isEmpty()){
            toastMaker("Please enter all fields.");
            return;
        }

        LiveData<User> userObserver = userRepository.getUserByUsername(username);
        userObserver.observe(SignupActivity.this, user -> {
            if(user != null){
                toastMaker("Username already exists.");
            }else{
                // this prevents "username already exists" from popping up after a user signs up
                userObserver.removeObservers(SignupActivity.this);
                User newUser = new User(username, password);
                userRepository.insertUser(newUser);

                toastMaker("You successfully signed up!");

                // start login activity after new user has signed up
                int userId = newUser.getId();
                Intent intent = LoginActivity.loginIntentFactory(getApplicationContext());
                startActivity(intent);
            }
        });
    }

    public void toastMaker(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}