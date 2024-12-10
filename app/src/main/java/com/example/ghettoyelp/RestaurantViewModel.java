package com.example.ghettoyelp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.RestaurantRepository;

import java.util.List;

public class RestaurantViewModel extends AndroidViewModel {
    private final RestaurantRepository repository;
    private final LiveData<List<Restaurant>> allRestaurants;

    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        repository = new RestaurantRepository(application);
        allRestaurants = repository.getAllRestaurants();
    }

    public LiveData<List<Restaurant>> getAllRestaurants() {
        return allRestaurants;
    }

    public void insertRestaurant(Restaurant restaurant) {
        repository.insertRestaurant(restaurant);
    }

    public void deleteRestaurant(int restaurantId) {
        repository.deleteRestaurant(restaurantId);
    }

    public List<Restaurant> getAllRestaurantsPaginated(int limit, int offset) {
        return repository.getAllRestaurantsPaginated(limit, offset);
    }
}
