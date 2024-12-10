package com.example.ghettoyelp.Database.Entities;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class UserTest extends TestCase {

    private User user;

    public void setUp(){
        user = new User("test", "test");
    }

    public void testSetId() {
        user.setId(1);
        assertEquals(1, user.getId());
        // logically, id should not be negative
        user.setId(-10);
        assertEquals(-10, user.getId());
    }

    public void testSetUsername() {
        user.setUsername("newTest");
        assertEquals("newTest", user.getUsername());
        // logically, we should not be able to change to the same username
        user.setUsername("newTest");
        assertEquals("newTest", user.getUsername());
    }

    public void testSetPassword() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
        // logically, we should not be able to have the same password as previous password
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    public void testSetReviewsCount() {
        user.setReviewsCount(1);
        assertEquals(1, user.getReviewsCount());

        // simulate list of reviews
        List<Review> reviews = new ArrayList<>();
        reviews.add(new Review(user.getUsername(), "restaurant1", "", 10 ));
        reviews.add(new Review(user.getUsername(), "restaurant2", "", 10 ));
        reviews.add(new Review("someone", "restaurant1", "", 10 ));

        int count = 0;
        for(Review review: reviews){
            if(review.getUsername().equals(user.getUsername())){
                count++;
            }
        }
        user.setReviewsCount(count);
        assertEquals(count, user.getReviewsCount());

        // simulate increment review count
        user.setReviewsCount(user.getReviewsCount() + 1);
        assertEquals(3, user.getReviewsCount());
    }

    public void testIsAdmin() {
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }
}