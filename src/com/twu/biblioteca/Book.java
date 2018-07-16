package com.twu.biblioteca;

public class Book {

    private String title;
    private boolean available;

    public Book(String title) {
        this.title = title;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
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
}
