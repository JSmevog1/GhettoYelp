package com.example.ghettoyelp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.RestaurantRepository;

import java.util.List;

/**
 * RestaurantViewModel is a part of the MVVM architecture and acts as a bridge
 * between the UI and the data repository. It manages the restaurant data and
 * provides lifecycle-aware access to the repository.
 *
 * @author yusraashar
 */
public class RestaurantViewModel extends AndroidViewModel {

    // Repository instance for handling restaurant data
    private final RestaurantRepository repository;

    // LiveData for observing all restaurants
    private final LiveData<List<Restaurant>> allRestaurants;

    /**
     * Constructor initializes the repository and preloads all restaurants as LiveData.
     *
     * @param application The application context.
     */
    public RestaurantViewModel(@NonNull Application application) {
        super(application);
        repository = new RestaurantRepository(application);
        allRestaurants = repository.getAllRestaurants();
    }

    /**
     * Returns a LiveData object containing the list of all restaurants.
     * Observing this LiveData ensures that the UI updates automatically
     * when the data changes.
     *
     * @return LiveData list of all restaurants.
     */
    public LiveData<List<Restaurant>> getAllRestaurants() {
        return allRestaurants;
    }

    /**
     * Inserts a new restaurant into the database.
     *
     * @param restaurant The Restaurant object to be inserted.
     */
    public void insertRestaurant(Restaurant restaurant) {
        repository.insertRestaurant(restaurant);
    }

    /**
     * Deletes a restaurant from the database by its ID.
     *
     * @param restaurantId The ID of the restaurant to be deleted.
     */
    public void deleteRestaurant(int restaurantId) {
        repository.deleteRestaurant(restaurantId);
    }

    /**
     * Fetches a paginated list of restaurants from the repository.
     * This method runs synchronously and is typically used for smaller datasets.
     *
     * @param limit  The maximum number of restaurants to retrieve.
     * @param offset The offset from which to start fetching.
     * @return A list of restaurants for the given page.
     */
    public List<Restaurant> getAllRestaurantsPaginated(int limit, int offset) {
        return repository.getAllRestaurantsPaginated(limit, offset);
    }
}
