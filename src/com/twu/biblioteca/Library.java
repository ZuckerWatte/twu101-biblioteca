package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();

    public void addBook(String title, String author, int year) {
        books.add(new Book(title, author, year));
    }

    public void addMovie(String title, int year, String director, String rating) {
        movies.add(new Movie(title, year, director, rating));
    }


    public boolean listAvailableBooks() {
        return listAvailableMedia(books);
    }

    public boolean listAvailableMovies() {
        return listAvailableMedia(movies);
    }

    public boolean listAvailableMedia(List<? extends Media> listOfMedia) {
        List<? extends Media> listOfAvailableMedia = filterForAvailableMedia(listOfMedia);
        if (listOfAvailableMedia.isEmpty())
            return false;

        printMediaTable(listOfAvailableMedia);
        return true;
    }

    public List<? extends Media> filterForAvailableMedia(List<? extends Media> media) {
        return media.stream().filter(movie -> movie.isAvailable()).collect(Collectors.toList());
    }

    private void printMediaTable(List<? extends Media> listOfMedia) {
        String[] mediaPropertyIDs = listOfMedia.get(0).getPropertyIDs();
        int[] longestMediaProperty = new int[mediaPropertyIDs.length];
        for (int i = 0; i < mediaPropertyIDs.length; i++) {
            String currentMediaPropertyID = mediaPropertyIDs[i];
            longestMediaProperty[i] = Math.max(Helper.getLongestStringLength(listOfMedia.stream().map(media -> media.getPropertyByID(currentMediaPropertyID))), currentMediaPropertyID.length());
        }
        printMediaRow(longestMediaProperty, mediaPropertyIDs);
        listOfMedia.forEach(media -> printMediaRow(longestMediaProperty, media.getProperties()));
    }

    private void printMediaRow(int[] longestProperties, String[] properties) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, longestProperties.length).forEach(i -> builder.append(propertyWithSpaces(longestProperties[i], properties[i])));
        Helper.print(builder.toString());
    }

    private String propertyWithSpaces(int longestProperty, String property) {
        int numSpaces = longestProperty - property.length() + 1;
        return property + IntStream.range(0, numSpaces).mapToObj(i -> " ").collect(Collectors.joining());
    }


    public boolean checkoutBook(String title) {
        return checkoutMedia(title, books);
    }

    public boolean checkoutMovie(String title) {
        return checkoutMedia(title, movies);
    }

    public boolean returnBook(String title) {
        return returnMedia(title, books);
    }

    public boolean returnMovie(String title) {
        return returnMedia(title, movies);
    }

    private boolean checkoutMedia(String title, List<? extends Media> listOfMedia) {
        return handleMediaTransaction(title, (Media media) -> media.checkout(), listOfMedia);
    }

    private boolean returnMedia(String title, List<? extends Media> listOfMedia) {
        return handleMediaTransaction(title, (Media media) -> media.giveBack(), listOfMedia);
    }

    private boolean handleMediaTransaction(String title, Function<Media, Boolean> function, List<? extends Media> listOfMedia) {
        Optional<? extends Media> opMedia = listOfMedia.stream().filter(media -> media.getTitle().equals(title)).findAny();
        return opMedia.isPresent() && function.apply(opMedia.get());
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}
