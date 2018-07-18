package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class UserTest {

    @Test
    public void testUserIsLoggedIn() {
        User user = new User("123-4567", "password");
        assertFalse("User is initially logged in", user.isLoggedIn());
        user.login("password");
        assertTrue("User can't login", user.isLoggedIn());
        user.logout();
        assertFalse("User is logged in after logout", user.isLoggedIn());
    }

    @Test
    public void testUserIsAlreadyLoggedIn() {
        User user = new User("123-4567", "password");
        user.login("password");
        assertFalse("User can login while already logged in", user.login("password"));
        assertTrue("User is logged out after login", user.isLoggedIn());
    }


    @Test
    public void testLoginWithPassword() {
        User user1 = new User("123-4567", "password");
        assertTrue("User can't login with correct password", user1.login("password"));
        User user2 = new User("321-4567", "password2");
        assertFalse("User can login with incorrect password", user2.login("wrongpassword"));
    }
}
