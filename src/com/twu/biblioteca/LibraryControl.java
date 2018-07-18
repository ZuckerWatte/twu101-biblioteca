package com.twu.biblioteca;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class LibraryControl {

    private Library library = new Library();

    public boolean listAvailableBooks() {
        return library.listAvailableMedia(library.getBooks());
    }

    public boolean listAvailableMovies() {
        return library.listAvailableMedia(library.getMovies());
    }

    public boolean listBorrowedBooks() {
        return library.listBorrowedMedia(library.getBooks());
    }

    public boolean listBorrowedMovies() {
        return library.listBorrowedMedia(library.getMovies());
    }

    public boolean showUserDetails() {
        if(getLoggedInUser() == null)
            return false;
        library.showUserDetails(getLoggedInUser());
        return true;
    }

    public void addBook(String title, String year, String author) {
        library.getBooks().add(new Book(title, year, author));
    }

    public void addMovie(String title, String year, String director, String rating) {
        library.getMovies().add(new Movie(title, year, director, rating));
    }

    public void addUser(String libraryNumber, String password, String name, String email, String phone) {
        library.getUsers().add(new User(libraryNumber, password, name, email, phone));
    }


    public boolean checkoutBook(String title) {
        return checkoutMedia(title, library.getBooks());
    }

    public boolean checkoutMovie(String title) {
        return checkoutMedia(title, library.getMovies());
    }

    public boolean returnBook(String title) {
        return returnMedia(title, library.getBooks());
    }

    public boolean returnMovie(String title) {
        return returnMedia(title, library.getMovies());
    }

    private boolean checkoutMedia(String title, List<? extends Media> listOfMedia) {
        return handleMediaTransaction(title, (Media media) -> media.checkout(getLoggedInUser()), listOfMedia);
    }

    private boolean returnMedia(String title, List<? extends Media> listOfMedia) {
        return handleMediaTransaction(title, (Media media) -> media.giveBack(getLoggedInUser()), listOfMedia);
    }

    private boolean handleMediaTransaction(String title, Function<Media, Boolean> function, List<? extends Media> listOfMedia) {
        if (getLoggedInUser() == null)
            return false;

        Optional<? extends Media> opMedia = listOfMedia.stream().filter(media -> media.getPropertyByID(Constants.PI_TITLE).equals(title)).findAny();
        return opMedia.isPresent() && function.apply(opMedia.get());
    }

    public boolean loginUser(String libraryNumber, String password) {
        if (getLoggedInUser() != null)
            return false;

        Optional<User> opUser = library.getUsers().stream().filter(user -> user.getPropertyByID(Constants.PI_LIBRARYNUMBER).equals(libraryNumber)).findAny();
        return opUser.isPresent() && opUser.get().login(password);
    }

    public boolean logoutUser() {
        if (getLoggedInUser() == null)
            return false;

        getLoggedInUser().logout();
        return true;
    }

    public User getLoggedInUser() {
        return library.getUsers().stream().filter(user -> user.isLoggedIn()).findAny().orElse(null);
    }

    public Library getLibrary() {
        return library;
    }
}
