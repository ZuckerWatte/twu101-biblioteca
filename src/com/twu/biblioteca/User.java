package com.twu.biblioteca;

public class User {

    private boolean loginStatus;
    private String libraryNumber;
    private String password;

    public User(String libraryNumber, String password) {
        this.libraryNumber = libraryNumber;
        this.password = password;
    }

    public boolean login(String password){
        if (loginStatus)
            return false;

        loginStatus = password.equals(this.password);
        return loginStatus;
    }

    public void logout() {
        loginStatus = false;
    }

    public boolean isLoggedIn() {
        return loginStatus;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }
}
