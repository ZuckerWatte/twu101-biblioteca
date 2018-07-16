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
        assertEquals(true, biblioteca.listBooks().isEmpty());
        biblioteca.addBook("book1");
        assertEquals(false, biblioteca.listBooks().isEmpty());
    }
}
