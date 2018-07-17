package com.twu.biblioteca;

public class Book extends Media {

    private String author;

    public Book(String title, String author, int year) {
        super(title, year);
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public String getPropertyByID(String propertyID) {
        switch(propertyID) {
            case "Title":
                return this.getTitle();

            case "Author":
                return this.getAuthor();

            case "Year":
                return this.getYear() + "";

            default:
                return "";
        }
    }

    public String[] getProperties() {
        return new String[]{this.getTitle(), this.getAuthor(), this.getYear() + ""};
    }

    public String[] getPropertyIDs() {
        return new String[]{"Title", "Author", "Year"};
    }
}
