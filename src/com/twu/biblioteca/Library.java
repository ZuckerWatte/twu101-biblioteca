package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Library {

    private List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    public boolean checkoutBook(String title) {
        return handleBookTransaction(title, true);
    }

    public boolean returnBook(String title) {
        return handleBookTransaction(title, false);
    }

    private boolean handleBookTransaction(String title, boolean checkout) {
        Optional<Book> opBook = books.stream().filter(book -> book.getTitle().equals(title)).findAny();
        return opBook.isPresent() && (checkout ? opBook.get().checkout() : opBook.get().giveBack());
    }



    public void listAvailableBooks() {
        List<Book> listOfBooks = filterBooks();
        Helper.print("List of books:");
        int longestTitle = Helper.getLongestString(listOfBooks.stream().map(book -> book.getTitle()));
        int longestAuthor = Helper.getLongestString(listOfBooks.stream().map(book -> book.getAuthor()));
        printHeader(longestTitle, longestAuthor);
        printBooks(longestTitle, longestAuthor, listOfBooks);
    }

    private List<Book> filterBooks() {
        return books.stream().filter(book -> book.isAvailable()).collect(Collectors.toList());
    }

    private void printBooks(int longestTitle, int longestAuthor, List<Book> listOfBooks) {
        for (Book book : listOfBooks) {
            printBookListLine(longestTitle, longestAuthor, book.getTitle(), book.getAuthor(), book.getYear() + "");
        }
    }

    private void printHeader(int longestTitle, int longestAuthor) {
        printBookListLine(longestTitle, longestAuthor, "Title", "Author", "Year");
    }

    private void printBookListLine(int longestTitle, int longestAuthor, String title, String author, String year) {
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        Helper.appendSpaces(builder, longestTitle - title.length() + 1);
        builder.append(author);
        Helper.appendSpaces(builder, longestAuthor - author.length() + 1);
        builder.append(year);
        Helper.print(builder.toString());
    }
}
