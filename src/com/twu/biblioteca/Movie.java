package com.twu.biblioteca;

public class Movie extends Media {

    private String director;
    private String rating;

    public Movie(String title, int year, String director, String rating) {
        super(title, year);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public String getRating() {
        return rating;
    }

    public String getPropertyByID(String propertyID) {
        switch(propertyID) {
            case "Title":
                return this.getTitle();

            case "Year":
                return this.getYear() + "";

            case "Director":
                return this.getDirector();

            case "Rating":
                return this.getRating();

            default:
                return "";
        }
    }

    public String[] getProperties() {
        return new String[]{this.getTitle(), this.getYear() + "", this.getDirector(), this.getRating()};
    }

    public String[] getPropertyIDs() {
        return new String[]{"Title", "Year", "Director", "Rating"};
    }
}
