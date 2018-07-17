package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    @Test
    public void testListBooks() {
        Library library = new Library();
        assertEquals(true, library.filterForAvailableMedia(library.getBooks()).isEmpty());
        library.addBook("book1", "2000", "");
        assertEquals("book1", library.filterForAvailableMedia(library.getBooks()).get(0).getPropertyByID("Title"));
        library.addBook("book2", "2000", "");
        assertEquals("book2", library.filterForAvailableMedia(library.getBooks()).get(1).getPropertyByID("Title"));
    }

    @Test
    public void testOnlyAvailableBooksInList() {
        Library library = new Library();
        library.addBook("book1", "2000", "");
        library.checkoutBook("book1");
        assertEquals(true, library.filterForAvailableMedia(library.getBooks()).isEmpty());
    }

    @Test
    public void testCheckoutBookFromLibrary() {
        Library library = new Library();
        assertEquals(false, library.checkoutBook("book1"));
        library.addBook("book1", "2000", "");
        assertEquals(true, library.checkoutBook("book1"));
    }

    @Test
    public void testReturnBookToLibrary() {
        Library library = new Library();
        assertEquals(false, library.returnBook("book1"));
        library.addBook("book1", "2000", "");
        library.checkoutBook("book1");
        assertEquals(true, library.returnBook("book1"));
    }

    /* Biblioteca Release 2 */

    @Test
    public void testListMovies() {
        Library library = new Library();
        assertEquals(true, library.filterForAvailableMedia(library.getMovies()).isEmpty());
        library.addMovie("movie1", "2000", "director1", "1");
        assertEquals("movie1", library.filterForAvailableMedia(library.getMovies()).get(0).getPropertyByID("Title"));
        library.addMovie("movie2", "2000", "director1", "1");
        assertEquals("movie2", library.filterForAvailableMedia(library.getMovies()).get(1).getPropertyByID("Title"));
    }

    @Test
    public void testOnlyAvailableMovies() {
        Library library = new Library();
        library.addMovie("movie1", "2000", "director1", "1");
        assertEquals(true, library.filterForAvailableMedia(library.getMovies()).get(0).isAvailable());
        library.checkoutMovie("movie1");
        assertEquals(true, library.filterForAvailableMedia(library.getMovies()).isEmpty());
    }
}
