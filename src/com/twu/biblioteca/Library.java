package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Library {

    private List<Book> books = new ArrayList<>();

    public void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }


    public boolean listAvailableBooks() {
        List<Book> listOfBooks = getAvailableBooks();
        if (listOfBooks.isEmpty())
            return false;

        printBookTable(listOfBooks);
        return true;
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(book -> book.isAvailable()).collect(Collectors.toList());
    }

    private void printBookTable(List<Book> listOfBooks) {
        int longestTitle = Helper.getLongestStringLength(listOfBooks.stream().map(book -> book.getTitle()));
        int longestAuthor = Helper.getLongestStringLength(listOfBooks.stream().map(book -> book.getAuthor()));
        printBookRow(longestTitle, longestAuthor, "Title", "Author", "Year");
        listOfBooks.forEach(book -> printBookRow(longestTitle, longestAuthor, book.getTitle(), book.getAuthor(), book.getYear() + ""));
    }

    private void printBookRow(int longestTitle, int longestAuthor, String title, String author, String year) {
        StringBuilder builder = new StringBuilder();
        builder.append(propertyWithSpaces(longestTitle, title));
        builder.append(propertyWithSpaces(longestAuthor, author));
        builder.append(year);
        Helper.print(builder.toString());
    }

    private String propertyWithSpaces(int longestProperty, String property) {
        int numSpaces = longestProperty - property.length() + 1;
        return property + IntStream.range(0, numSpaces).mapToObj(i -> " ").collect(Collectors.joining());
    }


    public boolean checkoutBook(String title) {
        return handleBookTransaction(title, (Object book) -> ((Book) book).checkout());
    }

    public boolean returnBook(String title) {
        return handleBookTransaction(title, (Object book) -> ((Book) book).giveBack());
    }

    private boolean handleBookTransaction(String title, Function function) {
        Optional<Book> opBook = books.stream().filter(book -> book.getTitle().equals(title)).findAny();
        return opBook.isPresent() && (Boolean) function.apply(opBook.get());
    }


}
