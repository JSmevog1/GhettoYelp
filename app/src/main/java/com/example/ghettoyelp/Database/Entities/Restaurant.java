package com.example.ghettoyelp.Database.Entities;

public class Restaurant {
    private int id;
    private String name;
    private double rating;
    private int totalReviews;
    private String description;

    public Restaurant(String name, double rating, int totalReviews, String description) {
        this.name = name;           // Initialize the name field
        this.rating = rating;       // Initialize the rating field
        this.totalReviews = totalReviews; // Initialize the totalReviews field
        this.description = description;   // Initialize the description field
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    public int getTotalReviews() { return totalReviews; }
    public void setTotalReviews(int totalReviews) { this.totalReviews = totalReviews; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Utility Methods
    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rating=" + rating +
                ", totalReviews=" + totalReviews +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    public void updateDetails(String name, double rating, int totalReviews, String description) {
        this.name = name;
        this.rating = rating;
        this.totalReviews = totalReviews;
        this.description = description;
    }

    public boolean isPopular() {
        return rating >= 4.5 && totalReviews >= 1000;
    }

    public String getSummary() {
        return name + " - " + description + "\n" +
                totalReviews + " reviews, Rating: " + String.format("%.1f", rating) + "/10";
    }
}
