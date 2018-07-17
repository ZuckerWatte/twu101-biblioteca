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
        library.addMovie("Pulp Fiction", 1994, "Quentin Tarantino", "9");
        library.addMovie("Lord of the Rings", 2001,"Peter Jackson", "4");
        library.addMovie("Movie 3", 2000, "Arnie", "unrated");
    }

    private void initMenuOptions() {
        menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption("List Books", "B", "These are all books that are available.","There are no books available.",
                (String s) -> library.listAvailableBooks()));
        menuOptions.add(new MenuOption("Checkout Book", "CB", "Thank you enjoy the book!",
                "That book is not available.", "Which book do you want to checkout?",
                (String bookName) -> library.checkoutBook(bookName)));
        menuOptions.add(new MenuOption("Return Book", "RB", "Thank you for returning the book!",
                "That is not a valid book to return.", "Which book do you want to return?",
                (String bookName) -> library.returnBook(bookName)));
        menuOptions.add(new MenuOption("List Movies", "M", "These are all movies that are available.","There are no movies available.",
                (String s) -> library.listAvailableMovies()));
        menuOptions.add(new MenuOption("Checkout Movie", "CM", "Thank you enjoy the movie!",
                "That movie is not available.", "Which movie do you want to checkout?",
                (String movieName) -> library.checkoutMovie(movieName)));
        menuOptions.add(new MenuOption("Return Movie", "RM", "Thank you for returning the movie!",
                "That is not a valid movie to return.", "Which movie do you want to return?",
                (String movieName) -> library.returnMovie(movieName)));
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
