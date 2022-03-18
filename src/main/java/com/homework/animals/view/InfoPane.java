package com.homework.animals.view;

import com.homework.animals.controller.AnimalController;
import com.homework.animals.model.Animal;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

public class InfoPane extends VBox {

    //JavaFX elements
    private final String kindFormat = "Вид: %s";
    private final Label kindLabel = new Label();
    private final String nameFormat = "Кличка: %s";
    private final Label nameLabel = new Label();
    private final String ageFormat = "Возраст: %.2f лет";
    private final Label ageLabel = new Label();
    private final String ownerNameFormat = "Хозяин: %s";
    private final Label ownerNameLabel = new Label();

    public InfoPane(AnimalController animalController) {
        setAnimalController(animalController);

        this.setSpacing(7);

        this.getChildren().add(kindLabel);
        kindLabel.setWrapText(true);
        this.getChildren().add(nameLabel);
        nameLabel.setWrapText(true);
        this.getChildren().add(ageLabel);
        ageLabel.setWrapText(true);
        this.getChildren().add(ownerNameLabel);
        ownerNameLabel.setWrapText(true);
    }

    public void setAnimalController(AnimalController animalController) {
        Animal animal = animalController.getAnimal();
        animal.kindProperty().addListener((obs, oldValue, newValue) -> {
            kindLabel.setText(String.format(kindFormat, newValue));
        });
        kindLabel.setText(String.format(kindFormat, animal.getKind()));
        animal.nameProperty().addListener((obs, oldValue, newValue) -> {
            nameLabel.setText(String.format(nameFormat, newValue));
        });
        nameLabel.setText(String.format(nameFormat, animal.getName()));
        animal.ageProperty().addListener((obs, oldValue, newValue) -> {
            int newAge = Integer.parseInt(String.valueOf(newValue));
            ageLabel.setText(String.format(ageFormat, newAge / 12.0));
        });
        ageLabel.setText(String.format(ageFormat, animal.getAge() / 12.0));
        animal.ownerNameProperty().addListener((obs, oldValue, newValue) -> {
            ownerNameLabel.setText(String.format(ownerNameFormat, newValue));
        });
        ownerNameLabel.setText(String.format(ownerNameFormat, animal.getOwnerName()));
    }
}
