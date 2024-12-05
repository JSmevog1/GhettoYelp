package com.example.ghettoyelp.Database.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ghettoyelp.Database.MainDatabase;

/**
 * @author yusraashar
 * Entity class for the restaurant_table in the database
 * This defines the structure for storing restaurant information.
 */
@Entity(tableName = MainDatabase.RESTAURANT_TABLE)
public class Restaurant {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private double rating;
    private int totalReviews;
    private String description;

    // Constructor
    public Restaurant(String name, double rating, int totalReviews, String description) {
        this.name = name;
        this.rating = rating;
        this.totalReviews = totalReviews;
        this.description = description;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}