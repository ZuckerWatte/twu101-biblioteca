package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Library {

    private List<Book> books = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public boolean listAvailableMedia(List<? extends Media> listOfMedia) {
        List<? extends Media> listOfAvailableMedia = filterForAvailableMedia(listOfMedia);
        if (listOfAvailableMedia.isEmpty())
            return false;

        printItemTable(listOfAvailableMedia);
        return true;
    }

    public List<? extends Media> filterForAvailableMedia(List<? extends Media> media) {
        return media.stream().filter(movie -> movie.isAvailable()).collect(Collectors.toList());
    }

    public boolean listBorrowedMedia(List<? extends Media> listOfMedia) {
        List<? extends Media> listOfBorrowedMedia = filterForBorrowedMedia(listOfMedia);
        if (listOfBorrowedMedia.isEmpty())
            return false;

        printItemTable(listOfBorrowedMedia);
        return true;
    }

    public List<? extends Media> filterForBorrowedMedia(List<? extends Media> media) {
        return media.stream().filter(movie -> !movie.isAvailable()).collect(Collectors.toList());
    }

    public void showUserDetails(User user) {
        printItemTable(Arrays.asList(user));
    }

    private void printItemTable(List<? extends Listable> listOfItems) {
        List<String> propertyIDs = listOfItems.get(0).getPropertyIDs();
        List<Integer> columnWidths = propertyIDs.stream()
                .map(id -> Math.max(id.length(), Helper.getLongestStringLength(listOfItems.stream()
                        .map(item -> item.getPropertyByID(id))))).collect(Collectors.toList());

        printTableRow(columnWidths, propertyIDs);
        listOfItems.forEach(item -> printTableRow(columnWidths, item.getProperties()));
    }

    private void printTableRow(List<Integer> columnWidths, List<String> properties) {
        StringBuilder builder = new StringBuilder();
        IntStream.range(0, columnWidths.size()).forEach(i -> builder.append(propertyWithSpaces(columnWidths.get(i), properties.get(i))));
        Helper.print(builder.toString());
    }

    private String propertyWithSpaces(int longestProperty, String property) {
        int numSpaces = longestProperty - property.length() + 1;
        return property + IntStream.range(0, numSpaces).mapToObj(i -> " ").collect(Collectors.joining()) + "| ";
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<User> getUsers() {
        return users;
    }
}
