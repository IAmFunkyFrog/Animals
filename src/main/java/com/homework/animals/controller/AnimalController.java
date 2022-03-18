package com.homework.animals.controller;

import com.homework.animals.model.Animal;

public class AnimalController {
    private Animal animal;

    public AnimalController(Animal animal) {
        this.animal = animal;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public void updateKind(String kind) {
        animal.kindProperty().setValue(kind);
    }

    public void updateName(String name) {
        animal.nameProperty().setValue(name);
    }

    public void updateAge(int age) {
        animal.ageProperty().setValue(age);
    }

    public void updateOwnerName(String ownerName) {
        animal.ownerNameProperty().setValue(ownerName);
    }

    @Override
    public String toString() {
        return animal.toString();
    }
}
