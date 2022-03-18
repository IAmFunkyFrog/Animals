package com.homework.animals.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal {
    private final StringProperty kind;
    private final StringProperty name;
    private final IntegerProperty age; // in months
    private final StringProperty ownerName;

    public Animal(String kind, String name, int months, String ownerName) {
        this.kind = new SimpleStringProperty(kind);
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleIntegerProperty(months);
        this.ownerName = new SimpleStringProperty(ownerName);
    }

    public String getKind() {
        return kind.get();
    }

    public StringProperty kindProperty() {
        return kind;
    }

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public String getOwnerName() {
        return ownerName.get();
    }

    public StringProperty ownerNameProperty() {
        return ownerName;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }
    @Override
    public String toString() {
        return String.format("%s %s", this.getKind(), this.getName());
    }
}
