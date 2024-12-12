package com.example.ghettoyelp.Database.Entities;

import static org.junit.Assert.assertEquals;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * @author yusraashar
 * Unit tests for the Restaurant entity class.
 */
public class RestaurantTest  {

   private Restaurant restaurant;

    /**
     * Set up a new Restaurant object before each test.
     */
    @Before
    public void setUp() {
        restaurant = new Restaurant();
    }

    /**
     * Test setting and getting the ID of a restaurant.
     */
    @Test
    public void testSetId() {
        restaurant.setId(1);
        assertEquals(1, restaurant.getId());
    }

    /**
     * Test setting and getting the name of a restaurant.
     */
    @Test
    public void testSetName() {
        String name = "Test Restaurant";
        restaurant.setName(name);
        assertEquals(name, restaurant.getName());
    }

    /**
     * Test setting and getting the rating of a restaurant.
     */
    @Test
    public void testSetRating() {
        double rating = 4.5;
        restaurant.setRating(rating);
        assertEquals(rating, restaurant.getRating(), 0.0);
    }

    /**
     * Test setting and getting the description of a restaurant.
     */
    @Test
    public void testSetDescription() {
        String description = "A cozy place to dine.";
        restaurant.setDescription(description);
        assertEquals(description, restaurant.getDescription());
    }

    /**
     * Test the default values of a new Restaurant object.
     */
    @Test
    public void testDefaultValues() {
        assertEquals(0, restaurant.getId());
        assertEquals(null, restaurant.getName());
        assertEquals(null, restaurant.getDescription());
        assertEquals(0.0, restaurant.getRating(), 0.0);
    }
}
