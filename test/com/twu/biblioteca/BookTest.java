package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void testCheckoutBooks() {
        Book book = new Book("book1", "", 2000);
        assertEquals(true, book.isAvailable());
        book.checkout();
        assertEquals(false, book.isAvailable());
    }

    @Test
    public void testSuccessfulCheckout() {
        Book book = new Book("book1", "", 2000);
        assertEquals(true, book.checkout());
        assertEquals(false, book.checkout());
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
    public void testGetBookDetails() {
        Book book = new Book("Harry Potter", "JK Rowling", 1997);
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("JK Rowling", book.getAuthor());
        assertEquals(1997, book.getYear());
    }
}
