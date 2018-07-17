package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void testGetBookDetails() {
        Book book = new Book("Harry Potter", "1997", "JK Rowling");
        assertEquals("Harry Potter", book.getPropertyByID("Title"));
        assertEquals("1997", book.getPropertyByID("Year"));
        assertEquals("JK Rowling", book.getPropertyByID("Author"));
    }
}
