package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testListBooks() {
        Library library = new Library();
        assertEquals(true, library.filterBooks().isEmpty());
        library.addBook("book1", "", 2000);
        assertEquals("book1", library.filterBooks().get(0).getTitle());
        library.addBook("book2", "", 2000);
        assertEquals("book2", library.filterBooks().get(1).getTitle());
    }

    @Test
    public void testCheckoutBooks() {
        Book book = new Book("book1", "", 2000);
        assertEquals(true, book.isAvailable());
        book.checkout();
        assertEquals(false, book.isAvailable());
    }

    @Test
    public void testOnlyAvailableBooksInList() {
        Library library = new Library();
        library.addBook("book1", "", 2000);
        library.checkoutBook("book1");
        assertEquals(true, library.filterBooks().isEmpty());
    }

    @Test
    public void testSuccessfulCheckout() {
        Book book = new Book("book1", "", 2000);
        assertEquals(true, book.checkout());
        assertEquals(false, book.checkout());
    }

    @Test
    public void testCheckoutBookFromLibrary() {
        Library library = new Library();
        assertEquals(false, library.checkoutBook("book1"));
        library.addBook("book1", "", 2000);
        assertEquals(true, library.checkoutBook("book1"));
    }

    @Test
    public void testReturnBooks() {
        Book book = new Book("book1", "", 2000);
        book.checkout();
        book.giveBack();
        assertEquals(true, book.isAvailable());
    }

    @Test
    public void testSuccessfulReturn() {
        Book book = new Book("book1", "", 2000);
        assertEquals(false, book.giveBack());
        book.checkout();
        assertEquals(true, book.giveBack());
    }

    @Test
    public void testReturnBookToLibrary() {
        Library library = new Library();
        assertEquals(false, library.returnBook("book1"));
        library.addBook("book1", "", 2000);
        library.checkoutBook("book1");
        assertEquals(true, library.returnBook("book1"));
    }

    @Test
    public void testGetBookDetails() {
        Book book = new Book("Harry Potter", "JK Rowling", 1997);
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("JK Rowling", book.getAuthor());
        assertEquals(1997, book.getYear());
    }

    @Test
    public void testGetLongestString() {
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.add("a");
        listOfStrings.add("abc");
        listOfStrings.add("abc");
        assertEquals(3, Helper.getLongestString(listOfStrings.stream()));
        listOfStrings.add("abcde");
        assertEquals(5, Helper.getLongestString(listOfStrings.stream()));
        listOfStrings = new ArrayList<>();
        assertEquals(0, Helper.getLongestString(listOfStrings.stream()));
    }
}
