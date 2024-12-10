package com.example.ghettoyelp.Database.Entities;

import junit.framework.TestCase;

public class UserTest extends TestCase {

    private User user;

    public void setUp(){
        user = new User("test", "test");
    }

    public void testSetId() {
        user.setId(1);
        assertEquals(1, user.getId());
    }

    public void testSetUsername() {
        user.setUsername("newTest");
        assertEquals("newTest", user.getUsername());
    }

    public void testSetPassword() {
        user.setPassword("password");
        assertEquals("password", user.getPassword());
    }

    public void testSetReviewsCount() {
        user.setReviewsCount(1);
        assertEquals(1, user.getReviewsCount());
    }

    public void testIsAdmin() {
        user.setAdmin(true);
        assertTrue(user.isAdmin());
    }
}