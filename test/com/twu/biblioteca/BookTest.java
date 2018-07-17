package com.twu.biblioteca;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTest {

    @Test
    public void testGetBookDetails() {
        Book book = new Book("Harry Potter", "JK Rowling", 1997);
        assertEquals("Harry Potter", book.getTitle());
        assertEquals("JK Rowling", book.getAuthor());
        assertEquals(1997, book.getYear());
    }
}
