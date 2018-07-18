package com.twu.biblioteca;

public abstract class Media extends Listable {

    private boolean available;

    private User holder;

    public Media(String title, String year) {
        this.propertyList.add(new Property(Constants.PI_HOLDER, Constants.PV_LIBRARY));
        this.propertyList.add(new Property(Constants.PI_TITLE, title));
        this.propertyList.add(new Property(Constants.PI_YEAR, year));
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean checkout(User user) {
        if (!available)
            return false;

        updateHolder(user);
        return changeAvailability(true);
    }

    public boolean giveBack(User user) {
        if (this.holder != user)
            return false;

        updateHolder(null);
        return changeAvailability(false);
    }

    private void updateHolder(User user) {
        this.holder = user;
        Property holder = propertyList.stream().filter(property -> property.getIdentifier().equals(Constants.PI_HOLDER)).findAny().get();

        holder.setValue(user == null ? Constants.PV_LIBRARY : user.getPropertyByID(Constants.PI_LIBRARYNUMBER));
    }

    private boolean changeAvailability(boolean checkout) {
        boolean successful = checkout ? available : !available;
        available = !checkout;
        return successful;
    }

    public User getHolder() {
        return holder;
    }
}
