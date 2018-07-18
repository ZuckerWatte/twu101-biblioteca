package com.twu.biblioteca;

public class Book extends Media {

    public Book(String title, String year, String author) {
        super(title, year);
        this.propertyList.add(new Property(Constants.PI_AUTHOR, author));
    }
}
