package com.example.ghettoyelp.Database.Entities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author yusraashar
 * Unit tests for the Restaurant entity class.
 */
public class RestaurantTest {

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
        restaurant.setId(100);
        assertEquals(100, restaurant.getId());
    }

    /**
     * Test setting and getting the name of a restaurant.
     */
    @Test
    public void testSetName() {
        restaurant.setName("McDonald's");
        assertEquals("McDonald's", restaurant.getName());
        restaurant.setName("Burger King");
        assertEquals("Burger King", restaurant.getName());
    }

    /**
     * Test setting and getting the description of a restaurant.
     */
    @Test
    public void testSetDescription() {
        restaurant.setDescription("Fast food chain");
        assertEquals("Fast food chain", restaurant.getDescription());
        restaurant.setDescription("Home of the Whopper");
        assertEquals("Home of the Whopper", restaurant.getDescription());
    }

    /**
     * Test setting and getting the rating of a restaurant.
     */
    @Test
    public void testSetRating() {
        restaurant.setRating(8.5);
        assertEquals(8.5, restaurant.getRating(), 0.0);
        restaurant.setRating(9.9);
        assertEquals(9.9, restaurant.getRating(), 0.0);
    }

    /**
     * Test default values for a new Restaurant object.
     */
    @Test
    public void testDefaultValues() {
        assertEquals(0, restaurant.getId());
        assertEquals(null, restaurant.getName());
        assertEquals(null, restaurant.getDescription());
        assertEquals(0.0, restaurant.getRating(), 0.0);
    }
}
