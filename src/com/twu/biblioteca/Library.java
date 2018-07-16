package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books = new ArrayList<>();

    public List<Book> listAvailableBooks() {
        return books.stream().filter(book -> book.isAvailable()).collect(Collectors.toList());
    }

    public void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    public boolean checkoutBook(String title) {
        return handleBooks(title, true);
    }

    public boolean returnBook(String title) {
        return handleBooks(title, false);
    }

    private boolean handleBooks(String title, boolean checkout) {
        Optional<Book> opBook = books.stream().filter(book -> book.getTitle().equals(title)).findAny();
        return opBook.isPresent() && (checkout ? opBook.get().checkout() : opBook.get().giveBack());
    }
}
