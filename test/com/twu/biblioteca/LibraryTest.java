package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LibraryTest {

    @Test
    public void testListBooks() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        assertTrue("Initial book list not empty", library.filterForAvailableMedia(library.getBooks()).isEmpty());
        libraryControl.addBook("book1", "2000", "");
        assertEquals("book1", library.filterForAvailableMedia(library.getBooks()).get(0).getPropertyByID("Title"));
        libraryControl.addBook("book2", "2000", "");
        assertEquals("book2", library.filterForAvailableMedia(library.getBooks()).get(1).getPropertyByID("Title"));
    }

    @Test
    public void testOnlyAvailableBooksInList() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.loginUser("111-2222", "password12");
        Library library = libraryControl.getLibrary();
        libraryControl.addBook("book1", "2000", "");
        libraryControl.checkoutBook("book1");
        assertTrue("Checked out books are listed", library.filterForAvailableMedia(library.getBooks()).isEmpty());
    }

    @Test
    public void testCheckoutBookFromLibrary() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.loginUser("111-2222", "password12");
        assertFalse("Can check out non-existing book", libraryControl.checkoutBook("book1"));
        libraryControl.addBook("book1", "2000", "");
        assertTrue("Can't check out available book", libraryControl.checkoutBook("book1"));
    }

    @Test
    public void testReturnBookToLibrary() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.loginUser("111-2222", "password12");
        assertFalse("Can return non-existing book", libraryControl.returnBook("book1"));
        libraryControl.addBook("book1", "2000", "");
        libraryControl.checkoutBook("book1");
        assertTrue("Can't return checked out book", libraryControl.returnBook("book1"));
    }

    /* Biblioteca Release 2 */

    @Test
    public void testListMovies() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        assertTrue("Initial movie list not empty", library.filterForAvailableMedia(library.getMovies()).isEmpty());
        libraryControl.addMovie("movie1", "2000", "director1", "1");
        assertEquals("movie1", library.filterForAvailableMedia(library.getMovies()).get(0).getPropertyByID("Title"));
        libraryControl.addMovie("movie2", "2000", "director1", "1");
        assertEquals("movie2", library.filterForAvailableMedia(library.getMovies()).get(1).getPropertyByID("Title"));
    }

    @Test
    public void testOnlyAvailableMovies() {
        LibraryControl libraryControl = new LibraryControl();
        Library library = libraryControl.getLibrary();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.loginUser("111-2222", "password12");
        libraryControl.addMovie("movie1", "2000", "director1", "1");
        assertTrue("Does not list available movie", library.filterForAvailableMedia(library.getMovies()).get(0).isAvailable());
        libraryControl.checkoutMovie("movie1");
        assertTrue("Checked out movie is listed", library.filterForAvailableMedia(library.getMovies()).isEmpty());
    }

    @Test
    public void testUserCanLogin() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("123-4567", "password2");
        assertFalse("Non-existing user could login", libraryControl.loginUser("111-2222", "password2"));
        assertTrue("User could not login", libraryControl.loginUser("123-4567", "password2"));
    }

    @Test
    public void testUserCanLogout() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.loginUser("111-2222", "password12");
        libraryControl.logoutUser();
        assertTrue(libraryControl.getLoggedInUser() == null);
    }

    @Test
    public void testOnlyOneUserCanBeLoggedIn() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.addUser("333-4444", "password34");
        libraryControl.loginUser("111-2222", "password12");
        assertFalse("Second user could login", libraryControl.loginUser("333-4444", "password34"));
    }

    @Test
    public void testCanOnlyCheckoutAndReturnWhenUserIsLoggedIn() {
        LibraryControl libraryControl = new LibraryControl();
        libraryControl.addUser("111-2222", "password12");
        libraryControl.addMovie("movie1", "2000", "director1", "1");
        assertFalse("No user logged in", libraryControl.checkoutMovie("movie1"));
        libraryControl.loginUser("111-2222", "password12");
        libraryControl.checkoutMovie("movie1");
        libraryControl.logoutUser();
        assertFalse("No user logged in", libraryControl.returnMovie("movie1"));
    }
}
