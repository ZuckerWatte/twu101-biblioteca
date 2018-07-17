package com.twu.biblioteca;

public class Book {

    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public boolean checkout() {
        return changeAvailability(true);
    }

    public boolean giveBack() {
        return changeAvailability(false);
    }

    private boolean changeAvailability(boolean checkout) {
        boolean successful = checkout ? available : !available;
        available = !checkout;
        return successful;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return available;
    }
}
