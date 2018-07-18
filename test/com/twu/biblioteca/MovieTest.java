package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void testGetMovieDetails() {
        Movie movie = new Movie("Harry Potter", "2001", "Chris Columbus", "3");
        assertEquals("Harry Potter", movie.getPropertyByID(Constants.PI_TITLE));
        assertEquals("2001", movie.getPropertyByID(Constants.PI_YEAR));
        assertEquals("Chris Columbus", movie.getPropertyByID(Constants.PI_DIRECTOR));
        assertEquals("3", movie.getPropertyByID(Constants.PI_RATING));
    }
}
