package com.twu.biblioteca;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class UserTest {

    @Test
    public void testUserIsLoggedIn() {
        User user = new User("123-4567", "password");
        assertEquals(false, user.isLoggedIn());
        user.login("password");
        assertEquals(true, user.isLoggedIn());
        user.logout();
        assertEquals(false, user.isLoggedIn());
    }

    @Test
    public void testUserIsAlreadyLoggedIn() {
        User user = new User("123-4567", "password");
        user.login("password");
        assertEquals(false, user.login("password"));
        assertEquals(true, user.isLoggedIn());
    }


    @Test
    public void testLoginWithPassword() {
        User user1 = new User("123-4567", "password");
        assertEquals(true, user1.login("password"));
        User user2 = new User("321-4567", "password2");
        assertEquals(false, user2.login("wrongpassword"));
    }
}
