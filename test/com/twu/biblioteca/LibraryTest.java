package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LibraryTest {

    @Test
    public void testListBooks() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        assertEquals(true, library.filterForAvailableMedia(library.getBooks()).isEmpty());
        libraryControl.addBook("book1", "2000", "");
        assertEquals("book1", library.filterForAvailableMedia(library.getBooks()).get(0).getPropertyByID("Title"));
        libraryControl.addBook("book2", "2000", "");
        assertEquals("book2", library.filterForAvailableMedia(library.getBooks()).get(1).getPropertyByID("Title"));
    }

    @Test
    public void testOnlyAvailableBooksInList() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        libraryControl.addBook("book1", "2000", "");
        libraryControl.checkoutBook("book1");
        assertEquals(true, library.filterForAvailableMedia(library.getBooks()).isEmpty());
    }

    @Test
    public void testCheckoutBookFromLibrary() {
        LibraryControl libraryControl = new LibraryControl();
        assertEquals(false, libraryControl.checkoutBook("book1"));
        libraryControl.addBook("book1", "2000", "");
        assertEquals(true, libraryControl.checkoutBook("book1"));
    }

    @Test
    public void testReturnBookToLibrary() {
        LibraryControl libraryControl = new LibraryControl();
        assertEquals(false, libraryControl.returnBook("book1"));
        libraryControl.addBook("book1", "2000", "");
        libraryControl.checkoutBook("book1");
        assertEquals(true, libraryControl.returnBook("book1"));
    }

    /* Biblioteca Release 2 */

    @Test
    public void testListMovies() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        assertEquals(true, library.filterForAvailableMedia(library.getMovies()).isEmpty());
        libraryControl.addMovie("movie1", "2000", "director1", "1");
        assertEquals("movie1", library.filterForAvailableMedia(library.getMovies()).get(0).getPropertyByID("Title"));
        libraryControl.addMovie("movie2", "2000", "director1", "1");
        assertEquals("movie2", library.filterForAvailableMedia(library.getMovies()).get(1).getPropertyByID("Title"));
    }

    @Test
    public void testOnlyAvailableMovies() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        libraryControl.addMovie("movie1", "2000", "director1", "1");
        assertEquals(true, library.filterForAvailableMedia(library.getMovies()).get(0).isAvailable());
        libraryControl.checkoutMovie("movie1");
        assertEquals(true, library.filterForAvailableMedia(library.getMovies()).isEmpty());
    }
}
