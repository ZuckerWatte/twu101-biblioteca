package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void testGetMovieDetails() {
        Movie movie = new Movie("Harry Potter", 2001, "Chris Columbus", "3");
        assertEquals("Harry Potter", movie.getTitle());
        assertEquals(2001, movie.getYear());
        assertEquals("Chris Columbus", movie.getDirector());
        assertEquals("3", movie.getRating());
    }
}
