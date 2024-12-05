package com.example.ghettoyelp.viewHolders;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.User;
import com.example.ghettoyelp.Database.UserRepository;

import java.util.List;

public class ViewAllUserViewModel extends AndroidViewModel {
    private final UserRepository repository;

    private final LiveData<List<User>> allUsers;

    public ViewAllUserViewModel (Application application) {
        super(application);
        repository = UserRepository.getRepository(application);
        allUsers = repository.getAllUsersLiveData();
    }

    public LiveData<List<User>> getAllUsers(){
        return allUsers;
    }

    public void insUser(User user){
        repository.insertUser(user);
    }
}
