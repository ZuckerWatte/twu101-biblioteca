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

    public void addBook(String title, String year, String author) {
        library.getBooks().add(new Book(title, year, author));
    }

    public void addMovie(String title, String year, String director, String rating) {
        library.getMovies().add(new Movie(title, year, director, rating));
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
        return handleMediaTransaction(title, (Media media) -> media.checkout(), listOfMedia);
    }

    private boolean returnMedia(String title, List<? extends Media> listOfMedia) {
        return handleMediaTransaction(title, (Media media) -> media.giveBack(), listOfMedia);
    }

    private boolean handleMediaTransaction(String title, Function<Media, Boolean> function, List<? extends Media> listOfMedia) {
        Optional<? extends Media> opMedia = listOfMedia.stream().filter(media -> media.getPropertyByID("Title").equals(title)).findAny();
        return opMedia.isPresent() && function.apply(opMedia.get());
    }

    public Library getLibrary() {
        return library;
    }
}
