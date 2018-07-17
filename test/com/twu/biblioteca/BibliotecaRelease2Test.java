package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BibliotecaRelease2Test {

    @Test
    public void testListMovies() {
        Library library = new Library();
        assertEquals(true, library.filterForAvailableMovies().isEmpty());
        library.addMovie("movie1", 2000, "director1", "1");
        assertEquals("movie1", library.filterForAvailableMovies().get(0).getTitle());
        library.addMovie("movie2", 2000, "director1", "1");
        assertEquals("movie2", library.filterForAvailableMovies().get(1).getTitle());
    }

    @Test
    public void testOnlyAvailableMovies() {
        Library library = new Library();
        library.addMovie("movie1", 2000, "director1", "1");
        assertEquals(true, library.filterForAvailableMovies().get(0).isAvailable());
    }

    @Test
    public void testCheckoutMovie() {
        Movie movie = new Movie("movie1", 2000, "dir1", "1");
        movie.checkout();
        assertEquals(false, movie.isAvailable());
    }
}
