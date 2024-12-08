package com.example.ghettoyelp.Database.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ghettoyelp.Database.MainDatabase;

import java.util.Objects;

@Entity(tableName = MainDatabase.REVIEWS_TABLE)
public class Review {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String username;
    private String restaurant;
    private String review;
    private int rating;

    public Review(String username, String restaurant, String review, int rating) {
        this.username = username;
        this.restaurant = restaurant;
        this.review = review;
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Review review1 = (Review) o;
        return id == review1.id && rating == review1.rating && Objects.equals(username, review1.username) && Objects.equals(restaurant, review1.restaurant) && Objects.equals(review, review1.review);
    }

    @NonNull
    @Override
    public String toString() {
        return username + '\'' +
                restaurant + '\'' +
                review + '\'' +
                rating;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, restaurant, review, rating);
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
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

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
