package com.example.ghettoyelp.Database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.ghettoyelp.Database.DAOs.RestaurantDAO;
import com.example.ghettoyelp.Database.DAOs.ReviewDAO;
import com.example.ghettoyelp.Database.DAOs.UserDAO;
import com.example.ghettoyelp.Database.Entities.Restaurant;
import com.example.ghettoyelp.Database.Entities.Review;
import com.example.ghettoyelp.Database.Entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Yui Nguyen
 * Last Update: Dec 10th, 2024
 * Description:
 *      Main database processor of the application
 *      Communicate between database (DAOs) and other files
 *      Generate default admin account, user account and 5 default restaurants
 */


@Database(entities = {User.class, Review.class, Restaurant.class}, version = 1, exportSchema = false)
public abstract class MainDatabase extends RoomDatabase {
    // name of the database tables
    private static final String DATABASE_NAME = "mainDatabase";
    public static final String USER_TABLE = "userTable";
    public static final String RESTAURANT_TABLE = "restaurantTable";
    public static final String REVIEWS_TABLE = "reviewsTable";

    // for database using
    private static volatile MainDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // METHODS for database
    public static MainDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (MainDatabase.class){
                // Database created for the 1st time
                // Call method to add default values, which is admin account
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MainDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .allowMainThreadQueries() // added for view users recycler view
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * Method used to create default user, which is an admin
     * Modify database of users table
     */
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            //Log.i("MainDatabase","DATABASE CREATED!");
            databaseExecutor.execute(()->{
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();

                // default users
                User admin = new User("admin1", "admin1");
                admin.setAdmin(true);
                User user = new User("user1", "user1");
                dao.insert(admin);
                dao.insert(user);

                // default restaurants
                RestaurantDAO restaurantDAO = INSTANCE.restaurantDAO();

                restaurantDAO.insert(new Restaurant("Hong Kong Express", 0, 0,
                        "Chinese fast food."));
                restaurantDAO.insert(new Restaurant("Carrot & Daikon", 0, 0,
                        "Best Vietnamese Baguettes in SoCal"));
                restaurantDAO.insert(new Restaurant("Shin-Sen-Gumi Yakitori", 0, 0,
                        "Informal Japanese chain serving charcoal-grilled yakitori, plus sushi, udon & ramen."));
                restaurantDAO.insert(new Restaurant("In-N-Out Burger", 0, 0,
                        "Classic burger chain serving customizable burgers, hand-cut fries & shakes."));
                restaurantDAO.insert(new Restaurant("Chick-fil-A", 0, 0,
                        "Fast-food chain serving chicken sandwiches & nuggets along with salads & sides."));

                //Log.i("MainDatabase", "Default admin added");
            });
        }
    };

    // METHODS for DAOs
    public abstract UserDAO userDAO();
    public abstract RestaurantDAO restaurantDAO();
    public abstract ReviewDAO reviewDAO();

}
