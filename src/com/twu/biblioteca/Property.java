package com.twu.biblioteca;

public class Property {

    private String identifier;
    private String value;

    public Property(String identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValue() {
        return value;
    }
}
