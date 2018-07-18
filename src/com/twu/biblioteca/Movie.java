package com.twu.biblioteca;

public class Movie extends Media {

    public Movie(String title, String year, String director, String rating) {
        super(title, year);
        this.propertyList.add(new Property(Constants.PI_DIRECTOR, director));
        this.propertyList.add(new Property(Constants.PI_RATING, rating));
    }
}
