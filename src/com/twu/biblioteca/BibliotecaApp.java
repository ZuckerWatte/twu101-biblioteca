package com.twu.biblioteca;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String MAINMENU_MSG = "Type option command";
    public static final String INVALIDOPT_MSG = "Select a valid option!";
    public static final String CHECKOUT_MSG = "Which book do you want to checkout?";
    public static final String RETURN_MSG = "Which book do you want to return?";
    public static final String OPT_LISTBOOKS = "List Books";
    public static final String CMD_LISTBOOKS = "L";
    public static final String OPT_CHECKOUT = "Checkout Book";
    public static final String CMD_CHECKOUT = "C";
    public static final String OPT_RETURN = "Return Book";
    public static final String CMD_RETURN = "R";
    public static final String OPT_QUIT = "Quit";
    public static final String CMD_QUIT = "Q";
    public static final String SEPARATOR = " - ";
    public static final String CHECKOUT_SUCCESS_MSG = "Thank you enjoy the book!";
    public static final String CHECKOUT_FAILURE_MSG = "That book is not available.";
    public static final String RETURN_SUCCESS_MSG = "Thank you for returning the book.";
    public static final String RETURN_FAILURE_MSG = "That is not a valid book to return.";

    private Library library;

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp();
        print(biblioteca.welcome());
        biblioteca.initLibrary();
        biblioteca.mainMenu();
    }

    public String welcome() {
        return WELCOME_MSG;
    }

    public void mainMenu() {
        print("\n" + MAINMENU_MSG);
        printMenuOption(CMD_LISTBOOKS, OPT_LISTBOOKS);
        printMenuOption(CMD_CHECKOUT, OPT_CHECKOUT);
        printMenuOption(CMD_RETURN, OPT_RETURN);
        printMenuOption(CMD_QUIT, OPT_QUIT);
        String userinput = readUserInput();
        selectMenuOption(userinput);
        mainMenu();
    }

    private void printMenuOption(String cmd, String opt) {
        print(cmd + SEPARATOR + opt);
    }

    private void selectMenuOption(String userinput) {
        switch (userinput.toUpperCase()) {
            case CMD_LISTBOOKS:
                printLibrary();
                break;
            case CMD_CHECKOUT:
                handleCheckout();
                break;
            case CMD_RETURN:
                handleReturn();
                break;
            case CMD_QUIT:
                System.exit(0);
            default:
                print(INVALIDOPT_MSG);
        }
    }

    private void handleReturn() {
        print("\n" + RETURN_MSG);
        String userinput = readUserInput();
        print(library.returnBook(userinput) ? RETURN_SUCCESS_MSG : RETURN_FAILURE_MSG);
    }

    private void handleCheckout() {
        print("\n" + CHECKOUT_MSG);
        String userinput = readUserInput();
        print(library.checkoutBook(userinput) ? CHECKOUT_SUCCESS_MSG : CHECKOUT_FAILURE_MSG);
    }

    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
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
        appendSpaces(builder, longestTitle - title.length() + 1);
        builder.append(author);
        appendSpaces(builder, longestAuthor - author.length() + 1);
        builder.append(year);
        print(builder.toString());
    }

    private static void appendSpaces(StringBuilder builder, int numSpaces) {
        for (int i = 0; i < numSpaces; i++)
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
