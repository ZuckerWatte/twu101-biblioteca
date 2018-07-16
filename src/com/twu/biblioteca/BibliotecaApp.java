package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    private ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {


    }

    public static String welcome() {
        return WELCOME_MSG;
    }

    public List<Book> listBooks() {
        return books;
    }

    public void addBook(String book) {
        books.add(new Book());
    }
}

