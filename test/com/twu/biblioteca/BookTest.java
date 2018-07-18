package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void testGetBookDetails() {
        Book book = new Book("Harry Potter", "1997", "JK Rowling");
        assertEquals("Harry Potter", book.getPropertyByID(Constants.PI_TITLE));
        assertEquals("1997", book.getPropertyByID(Constants.PI_YEAR));
        assertEquals("JK Rowling", book.getPropertyByID(Constants.PI_AUTHOR));
    }
}
