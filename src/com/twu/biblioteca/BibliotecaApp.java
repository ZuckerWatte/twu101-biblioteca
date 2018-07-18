package com.twu.biblioteca;

import java.util.*;

public class BibliotecaApp {


    private LibraryControl libraryControl;
    private List<MenuOption> menuOptions;

    public static void main(String[] args) {
        BibliotecaApp biblioteca = new BibliotecaApp();
        Helper.print(Constants.WELCOME_MSG);
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
        libraryControl.addUser("111-1111", "pw1", "Hans Peter","hp@baxter.com", "+49175456789");
        libraryControl.addUser("222-2222", "pw2", "Hanna Petra","hp@boxter.com", "+4329887663");
    }

    private void initMenuOptions() {
        menuOptions = new ArrayList<>();
        menuOptions.add(new MenuOption(
                Constants.MO_LOGIN_NAME,
                Constants.MO_LOGIN_COMMAND,
                Constants.MO_LOGIN_SUCCESS_MSG,
                Constants.MO_LOGIN_FAILURE_MSG,
                Constants.MO_LOGIN_INQUIRY_MSG,
                (String loginCredentials) -> libraryControl.loginUser(loginCredentials.split(";")[0], loginCredentials.split(";")[1]),
                false,
                true));
        menuOptions.add(new MenuOption(
                Constants.MO_LOGOUT_NAME,
                Constants.MO_LOGOUT_COMMAND,
                Constants.MO_LOGOUT_SUCCESS_MSG,
                Constants.MO_LOGOUT_FAILURE_MSG,
                (String s) -> libraryControl.logoutUser(),
                true,
                false));
        menuOptions.add(new MenuOption(
                Constants.MO_LISTBOOKS_NAME,
                Constants.MO_LISTBOOKS_COMMAND,
                Constants.MO_LISTBOOKS_SUCCESS_MSG,
                Constants.MO_LISTBOOKS_FAILURE_MSG,
                (String s) -> libraryControl.listAvailableBooks(),
                true,
                true));
        menuOptions.add(new MenuOption(
                Constants.MO_LISTBORROWEDBOOKS_NAME,
                Constants.MO_LISTBORROWEDBOOKS_COMMAND,
                Constants.MO_LISTBORROWEDBOOKS_SUCCESS_MSG,
                Constants.MO_LISTBORROWEDBOOKS_FAILURE_MSG,
                (String s) -> libraryControl.listBorrowedBooks(),
                true,
                true));
        menuOptions.add(new MenuOption(
                Constants.MO_CHECKOUTBOOK_NAME,
                Constants.MO_CHECKOUTBOOK_COMMAND,
                Constants.MO_CHECKOUTBOOK_SUCCESS_MSG,
                Constants.MO_CHECKOUTBOOK_FAILURE_MSG,
                Constants.MO_CHECKOUTBOOK_INQUIRY_MSG,
                (String bookName) -> libraryControl.checkoutBook(bookName),
                true,
                false));
        menuOptions.add(new MenuOption(
                Constants.MO_RETURNBOOK_NAME,
                Constants.MO_RETURNBOOK_COMMAND,
                Constants.MO_RETURNBOOK_SUCCESS_MSG,
                Constants.MO_RETURNBOOK_FAILURE_MSG,
                Constants.MO_RETURNBOOK_INQUIRY_MSG,
                (String bookName) -> libraryControl.returnBook(bookName),
                true,
                false));
        menuOptions.add(new MenuOption(
                Constants.MO_LISTMOVIES_NAME,
                Constants.MO_LISTMOVIES_COMMAND,
                Constants.MO_LISTMOVIES_SUCCESS_MSG,
                Constants.MO_LISTMOVIES_FAILURE_MSG,
                (String s) -> libraryControl.listAvailableMovies(),
                true,
                true));
        menuOptions.add(new MenuOption(
                Constants.MO_LISTBORROWEDMOVIES_NAME,
                Constants.MO_LISTBORROWEDMOVIES_COMMAND,
                Constants.MO_LISTBORROWEDMOVIES_SUCCESS_MSG,
                Constants.MO_LISTBORROWEDMOVIES_FAILURE_MSG,
                (String s) -> libraryControl.listBorrowedMovies(),
                true,
                true));
        menuOptions.add(new MenuOption(
                Constants.MO_CHECKOUTMOVIE_NAME,
                Constants.MO_CHECKOUTMOVIE_COMMAND,
                Constants.MO_CHECKOUTMOVIE_SUCCESS_MSG,
                Constants.MO_CHECKOUTMOVIE_FAILURE_MSG,
                Constants.MO_CHECKOUTMOVIE_INQUIRY_MSG,
                (String movieName) -> libraryControl.checkoutMovie(movieName),
                true,
                false));
        menuOptions.add(new MenuOption(
                Constants.MO_RETURNMOVIE_NAME,
                Constants.MO_RETURNMOVIE_COMMAND,
                Constants.MO_RETURNMOVIE_SUCCESS_MSG,
                Constants.MO_RETURNMOVIE_FAILURE_MSG,
                Constants.MO_RETURNMOVIE_INQUIRY_MSG,
                (String movieName) -> libraryControl.returnMovie(movieName),
                true,
                false));
        menuOptions.add(new MenuOption(
                Constants.MO_USERDETAILS_NAME,
                Constants.MO_USERDETAILS_COMMAND,
                (String s) -> libraryControl.showUserDetails(),
                false));
        menuOptions.add(new MenuOption(
                Constants.MO_QUIT_NAME,
                Constants.MO_QUIT_COMMAND,
                (String s) -> this.exit(),
                true));
    }

    public void mainMenu() {
        boolean loggedInUser = libraryControl.getLoggedInUser() != null;
        Helper.print("\n" + Constants.MAINMENU_MSG);
        menuOptions.forEach(menuOption -> Helper.print(menuOption.toString(loggedInUser)));
        String upperCaseUserInput = Helper.readUserInput().toUpperCase();
        selectMenuOption(upperCaseUserInput, loggedInUser);
        mainMenu();
    }

    private void selectMenuOption(String upperCaseUserInput, boolean loggedInUser) {
        Optional<MenuOption> opMenuOption = menuOptions.stream().filter(menuOption -> menuOption.getCommand().equals(upperCaseUserInput)).findAny();
        opMenuOption.ifPresentOrElse(menuOption -> menuOption.execute(loggedInUser), () -> Helper.print(Constants.INVALIDOPT_MSG));
    }

    public boolean exit() {
        System.exit(0);
        return false;
    }

}
