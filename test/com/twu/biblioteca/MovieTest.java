package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void testCheckoutMovie() {
        Movie movie = new Movie("movie1", 2000, "dir1", "1");
        movie.checkout();
        assertEquals(false, movie.isAvailable());
    }
}
