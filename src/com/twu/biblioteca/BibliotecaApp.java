package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {

    public static final String WELCOME_MSG = "Welcome to Biblioteca!";
    public static final String MAINMENU_MSG = "Type option command";
    public static final String INVALIDOPT_MSG = "Select a valid option!";


    private LibraryControl libraryControl;
    private List<MenuOption> menuOptions;

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp();
        Helper.print(WELCOME_MSG);
        biblioteca.initLibrary();
        biblioteca.initMenuOptions();
        biblioteca.mainMenu();
    }

    private void initLibrary() {
        libraryControl = new LibraryControl();
        libraryControl.addBook("Harry Potter", "1997", "JK Rowling");
        libraryControl.addBook("Lord of the Rings", "1954", "JRR Tolkien");
        libraryControl.addBook("Book 3", "2001", "Jack Doe");
        libraryControl.addMovie("Pulp Fiction", "1994", "Quentin Tarantino", "9");
        libraryControl.addMovie("Lord of the Rings", "2001","Peter Jackson", "4");
        libraryControl.addMovie("Movie 3", "2000", "Arnie", "unrated");
        libraryControl.addUser("111-1111", "pw1");
        libraryControl.addUser("222-2222", "pw2");
    }

    private void initMenuOptions() {
        menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption("Login", "LI", "You are logged in!","Login failed. Wrong library number or password or somebody else is logged in.", "Please enter library number and password (separated by ;)",
                (String loginCredentials) -> libraryControl.loginUser(loginCredentials.split(";")[0], loginCredentials.split(";")[1])));
        menuOptions.add(new MenuOption("Logout", "LO", "You are logged out!", "Nobody was logged in!",
                (String s) -> libraryControl.logoutUser()));
        menuOptions.add(new MenuOption("List Books", "B", "These are all books that are available.","There are no books available.",
                (String s) -> libraryControl.listAvailableBooks()));
        menuOptions.add(new MenuOption("List Borrowed Books", "BB", "These are all books that belong to the library, but are currently unavailable.","All books are available. Use B-command to list available books.",
                (String s) -> libraryControl.listBorrowedBooks()));
        menuOptions.add(new MenuOption("Checkout Book", "CB", "Thank you enjoy the book!",
                "That book is not available.", "Which book do you want to checkout?",
                (String bookName) -> libraryControl.checkoutBook(bookName)));
        menuOptions.add(new MenuOption("Return Book", "RB", "Thank you for returning the book!",
                "That is not a valid book to return.", "Which book do you want to return?",
                (String bookName) -> libraryControl.returnBook(bookName)));
        menuOptions.add(new MenuOption("List Movies", "M", "These are all movies that are available.","There are no movies available.",
                (String s) -> libraryControl.listAvailableMovies()));
        menuOptions.add(new MenuOption("List Borrowed Movies", "BM", "These are all movies that belong to the library, but are currently unavailable.","All movies are available. Use B-command to list available movies.",
                (String s) -> libraryControl.listBorrowedMovies()));
        menuOptions.add(new MenuOption("Checkout Movie", "CM", "Thank you enjoy the movie!",
                "That movie is not available.", "Which movie do you want to checkout?",
                (String movieName) -> libraryControl.checkoutMovie(movieName)));
        menuOptions.add(new MenuOption("Return Movie", "RM", "Thank you for returning the movie!",
                "That is not a valid movie to return.", "Which movie do you want to return?",
                (String movieName) -> libraryControl.returnMovie(movieName)));
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
