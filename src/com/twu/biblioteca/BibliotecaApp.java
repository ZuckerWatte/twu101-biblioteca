package com.twu.biblioteca;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    private Library library;

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp();
        print(biblioteca.welcome());
        biblioteca.initLibrary();
        biblioteca.printLibrary();
    }

    public String welcome() {
        return WELCOME_MSG;
    }

    public void printLibrary() {
        List<Book> listOfBooks = library.listAvailableBooks();
        print("List of books:");
        int longestTitle = getLongestString(listOfBooks.stream().map(book -> book.getTitle()));
        int longestAuthor = getLongestString(listOfBooks.stream().map(book -> book.getAuthor()));
        printHeader(longestTitle, longestAuthor);
        printBooks(longestTitle, longestAuthor, listOfBooks);
    }

    private void printBooks(int longestTitle, int longestAuthor, List<Book> listOfBooks) {
        for(Book book : listOfBooks) {
            printBookListLine(longestTitle, longestAuthor, book.getTitle(), book.getAuthor(), book.getYear() + "");
        }
    }

    private void printHeader(int longestTitle, int longestAuthor) {
        printBookListLine(longestTitle, longestAuthor, "Title", "Author", "Year");
    }

    private void printBookListLine(int longestTitle, int longestAuthor, String title, String author, String year) {
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        appendSpaces(builder, longestTitle - title.length() + 1);
        builder.append(author);
        appendSpaces(builder, longestAuthor - author.length() + 1);
        builder.append(year);
        print(builder.toString());
    }

    private static void appendSpaces(StringBuilder builder, int numSpaces) {
        for(int i = 0; i < numSpaces; i++)
            builder.append(" ");
    }

    public static int getLongestString(Stream<String> streamOfStrings) {
        return streamOfStrings.max(Comparator.comparing(String::length)).orElse("").length();
    }

    private static void print(String stringToPrint) {
        System.out.println(stringToPrint);
    }

    private void initLibrary() {
        library = new Library();
        library.addBook("Harry Potter", "JK Rowling", 1997);
        library.addBook("Lord of the Rings", "JRR Tolkien", 1954);
        library.addBook("Book 3", "Jack Doe", 2001);
    }
}
