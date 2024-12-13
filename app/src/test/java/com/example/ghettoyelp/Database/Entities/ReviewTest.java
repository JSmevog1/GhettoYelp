package com.example.ghettoyelp.Database.Entities;

import junit.framework.TestCase;

/**
 * @author Jason Smevog
 */

public class ReviewTest extends TestCase {

    private Review review;

    public void setUp() throws Exception {
        super.setUp();
        review = new Review("","","",0);
    }


    public void testSetRestaurant() {
        review.setRestaurant("Wendy's");
        assertEquals("Wendy's",review.getRestaurant());
        review.setRestaurant("Olive Garden");
        assertEquals("Olive Garden",review.getRestaurant());
    }

    public void testSetId() {
        review.setId(1);
        assertEquals(1,review.getId());
        review.setId(20000);
        assertEquals(20000,review.getId());
    }

    public void testSetUsername() {
        review.setUsername("Jason");
        assertEquals("Jason", review.getUsername());
        review.setUsername("Edward");
        assertEquals("Edward", review.getUsername());
    }

    public void testSetReview() {
        review.setReview("The food was amazing!");
        assertEquals("The food was amazing!", review.getReview());
        review.setReview("The food was terrible");
        assertEquals("The food was terrible", review.getReview());
    }

    public void testSetRating() {
        review.setRating(1);
        assertEquals(1,review.getRating());
        review.setRating(10);
        assertEquals(10,review.getRating());
    }
}