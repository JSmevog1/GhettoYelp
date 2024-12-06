package com.example.ghettoyelp.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.UserRepository;

import java.util.ArrayList;

public class ViewAllUserViewModel extends AndroidViewModel {
    private final UserRepository repository;

    public ViewAllUserViewModel (Application application) {
        super(application);
        repository = UserRepository.getRepository(application);
    }

//    public ArrayList<User> getAllUsers(){
//        return repository.getAllUsers();
//    }

    public void insUser(User user){
        repository.insertUser(user);
    }
}
