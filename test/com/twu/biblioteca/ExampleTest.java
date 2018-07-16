package com.twu.biblioteca;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ExampleTest {

    @Test
    public void testWelcomeMessage() {
        assertEquals("Welcome to Biblioteca!", BibliotecaApp.welcome());
    }

    @Test
    public void testListBooks() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        assertEquals(true, biblioteca.listAvailableBooks().isEmpty());
        biblioteca.addBook("book1");
        assertEquals("book1", biblioteca.listAvailableBooks().get(0).getTitle());
        biblioteca.addBook("book2");
        assertEquals("book2", biblioteca.listAvailableBooks().get(1).getTitle());
    }

    @Test
    public void testCheckoutBooks() {
        Book book = new Book("book1");
        assertEquals(true, book.isAvailable());
        book.checkout();
        assertEquals(false, book.isAvailable());
    }

    @Test
    public void testOnlyAvailableBooksInList() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        biblioteca.addBook("book1");
        biblioteca.checkoutBook("book1");
        assertEquals(true, biblioteca.listAvailableBooks().isEmpty());
    }

    @Test
    public void testSuccessfulCheckout() {
        Book book = new Book("book1");
        assertEquals(true, book.checkout());
        assertEquals(false, book.checkout());
    }

    @Test
    public void testCheckoutBookFromLibrary() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        assertEquals(false, biblioteca.checkoutBook("book1"));
        biblioteca.addBook("book1");
        assertEquals(true, biblioteca.checkoutBook("book1"));
    }

    @Test
    public void testReturnBooks() {
        Book book = new Book("book1");
        book.checkout();
        book.giveBack();
        assertEquals(true, book.isAvailable());
    }

    @Test
    public void testSuccessfulReturn() {
        Book book = new Book("book1");
        assertEquals(false, book.giveBack());
        book.checkout();
        assertEquals(true, book.giveBack());
    }

    @Test
    public void testReturnBookToLibrary() {
        BibliotecaApp biblioteca = new BibliotecaApp();
        assertEquals(false, biblioteca.returnBook("book1"));
        biblioteca.addBook("book1");
        biblioteca.checkoutBook("book1");
        assertEquals(true, biblioteca.returnBook("book1"));
    }
}
