package com.twu.biblioteca;

public class User extends Listable {

    private boolean loginStatus;
    private String password;

    public User(String libraryNumber, String password, String name, String email, String phone) {
        this.password = password;
        this.propertyList.add(new Property(Constants.PI_LIBRARYNUMBER, libraryNumber));
        this.propertyList.add(new Property(Constants.PI_NAME, name));
        this.propertyList.add(new Property(Constants.PI_EMAIL, email));
        this.propertyList.add(new Property(Constants.PI_PHONE, phone));
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

}
