package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    private ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {


    }

    public static String welcome() {
        return WELCOME_MSG;
    }

    public List<Book> listBooks() {
        return books.stream().filter(book -> book.isAvailable()).collect(Collectors.toList());
    }

    public void addBook(String title) {
        books.add(new Book(title));
    }
}

