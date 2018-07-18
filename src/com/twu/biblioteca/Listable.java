package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Listable {
    protected List<Property> propertyList = new ArrayList<>();

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
}
