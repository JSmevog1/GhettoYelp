package com.example.ghettoyelp.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ghettoyelp.Database.MainDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Tariq Kakar
 * Last Update: Dec 3, 2024
 * Description:
 *      Entity representing a user in the application
 *
 * @code id is the Primary Key for the user
 * @code username is the username of the user
 * @code password is the password of the user
 * @code reviews is a list containing all the reviews a user has submitted
 * @code reviewsCount is the number of reviews a user has submitted
 * @code isAdmin is an identifier denoting whether a user is an admin or not
 *
 */

@Entity(tableName = MainDatabase.USER_TABLE)
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String password;
    //private List<Review> reviews;
    private int reviewsCount;
    private boolean isAdmin;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //reviews = new ArrayList<>();
        reviewsCount = 0;
        isAdmin = false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && reviewsCount == user.reviewsCount && isAdmin == user.isAdmin && Objects.equals(username, user.username) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, reviewsCount, isAdmin);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public List<Review> getReviews() {
//        return reviews;
//    }
//
//    public void setReviews(List<Review> reviews) {
//        this.reviews = reviews;
//    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        this.reviewsCount = reviewsCount;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
