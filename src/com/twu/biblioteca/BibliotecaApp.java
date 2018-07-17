package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String MAINMENU_MSG = "Type option command";
    public static final String INVALIDOPT_MSG = "Select a valid option!";


    private Library library;
    private List<MenuOption> menuOptions;

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp();
        Helper.print(WELCOME_MSG);
        biblioteca.initLibrary();
        biblioteca.initMenuOptions();
        biblioteca.mainMenu();
    }

    private void initLibrary() {
        library = new Library();
        library.addBook("Harry Potter", "JK Rowling", 1997);
        library.addBook("Lord of the Rings", "JRR Tolkien", 1954);
        library.addBook("Book 3", "Jack Doe", 2001);
    }

    private void initMenuOptions() {
        menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption("List Books", "L", "These are all books that are available.","There are no books available.",
                (String s) -> library.listAvailableBooks()));
        menuOptions.add(new MenuOption("Checkout Book", "C", "Thank you enjoy the book!",
                "That book is not available.", "Which book do you want to checkout?",
                (String bookName) -> library.checkoutBook(bookName)));
        menuOptions.add(new MenuOption("Return Book", "R", "Thank you for returning the book!",
                "That is not a valid book to return.", "Which book do you want to return?",
                (String bookName) -> library.returnBook(bookName)));
        menuOptions.add(new MenuOption("Quit", "Q",
                (String s) -> this.exit()));
    }

    public void mainMenu() {
        Helper.print("\n" + MAINMENU_MSG);
        menuOptions.forEach(m -> Helper.print(m.toString()));
        String upperCaseUserInput = Helper.readUserInput().toUpperCase();
        selectMenuOption(upperCaseUserInput);
        mainMenu();
    }

    private void selectMenuOption(String upperCaseUserInput) {
        Optional<MenuOption> menuOption = menuOptions.stream().filter(m -> m.getCommand().equals(upperCaseUserInput)).findAny();
        menuOption.ifPresentOrElse(mo -> mo.execute(), () -> Helper.print(INVALIDOPT_MSG));
    }

    public boolean exit() {
        System.exit(0);
        return false;
    }

}
