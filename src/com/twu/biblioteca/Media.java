package com.twu.biblioteca;

public abstract class Media {
    private String title;
    private int year;
    protected boolean available;

    public Media(String title, int year) {
        this.title = title;
        this.year = year;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
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

    public abstract String[] getPropertyIDs();
    public abstract String[] getProperties();
    public abstract String getPropertyByID(String propertyID);
}
