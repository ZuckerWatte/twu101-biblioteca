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

    public void mainMenu() {
        Helper.print("\n" + MAINMENU_MSG);
        menuOptions.forEach(m -> Helper.print(m.toString()));
        String userinput = Helper.readUserInput();
        selectMenuOption(userinput);
        mainMenu();
    }

    private void selectMenuOption(String userinput) {
        Optional<MenuOption> menuOption = menuOptions.stream().filter(m -> m.getCommand().equals(userinput)).findAny();
        if (menuOption.isPresent()) {
            menuOption.get().execute(this);
        } else {
            Helper.print(INVALIDOPT_MSG);
        }
    }

    public Library getLibrary() {
        return library;
    }

    private void initLibrary() {
        library = new Library();
        library.addBook("Harry Potter", "JK Rowling", 1997);
        library.addBook("Lord of the Rings", "JRR Tolkien", 1954);
        library.addBook("Book 3", "Jack Doe", 2001);
    }

    private void initMenuOptions() {
        menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption("List Books", "L",
                (Object bib) -> {((BibliotecaApp) bib).getLibrary().listAvailableBooks(); return true;}));
        menuOptions.add(new MenuOption("Checkout Book", "C", "Thank you enjoy the book!",
                "That book is not available.", "Which book do you want to checkout?",
                (Object bookName) -> library.checkoutBook((String) bookName)));
        menuOptions.add(new MenuOption("Return Book", "R", "Thank you for returning the book!",
                "That is not a valid book to return.", "Which book do you want to return?",
                (Object bookName) -> library.returnBook((String) bookName)));
        menuOptions.add(new MenuOption("Quit", "Q",
                (Object o) -> {System.exit(0); return true;}));
    }
}
