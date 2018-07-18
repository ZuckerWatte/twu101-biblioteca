package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Media {

    protected List<Property> propertyList = new ArrayList<>();
    private boolean available;

    private User holder;

    public Media(String title, String year) {
        this.propertyList.add(new Property("Title", title));
        this.propertyList.add(new Property("Year", year));
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean checkout(User user) {
        if (!available)
            return false;

        this.holder = user;
        return changeAvailability(true);
    }

    public boolean giveBack(User user) {
        if (this.holder != user)
            return false;

        this.holder = null;
        return changeAvailability(false);
    }

    private boolean changeAvailability(boolean checkout) {
        boolean successful = checkout ? available : !available;
        available = !checkout;
        return successful;
    }

    public List<String> getPropertyIDs() {
        return this.propertyList.stream().map(property -> property.getIdentifier()).collect(Collectors.toList());
    }

    public List<String> getProperties() {
        return this.propertyList.stream().map(property -> property.getValue()).collect(Collectors.toList());
    }

    public String getPropertyByID(String propertyID) {
        return this.propertyList.stream().filter(property -> property.getIdentifier().equals(propertyID))
                .map(property -> property.getValue()).findAny().orElse("");
    }

    public User getHolder() {
        return holder;
    }
}
