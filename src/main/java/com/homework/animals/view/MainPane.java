package com.homework.animals.view;

import com.homework.animals.controller.AnimalController;
import com.homework.animals.model.Animal;
import com.homework.animals.model.AnimalDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane {

    private static final ComboBox<AnimalController> animalComboBox = new ComboBox<>();

    static {
        ObservableList<AnimalController> animalControllers = FXCollections.observableArrayList();
        for (Animal animal : AnimalDatabase.animals) {
            AnimalController animalController = new AnimalController(animal);
            animalControllers.add(animalController);
        }
        animalComboBox.setItems(animalControllers);
        animalComboBox.getSelectionModel().selectFirst();
    }

    private final InfoPane infoPane = new InfoPane(new AnimalController(animalComboBox.getSelectionModel().getSelectedItem().getAnimal()));
    private final EditPane editPane = new EditPane(new AnimalController(animalComboBox.getSelectionModel().getSelectedItem().getAnimal()));

    public MainPane() {
        this.setMargin(animalComboBox, new Insets(10, 0, 0, 0));
        this.setAlignment(animalComboBox, Pos.CENTER);

        HBox hbox = new HBox();
        hbox.setPadding(new Insets(40, 40, 40, 40));
        hbox.setSpacing(20);
        hbox.getChildren().add(infoPane);
        hbox.getChildren().add(editPane);
        this.setCenter(hbox);

        animalComboBox.setOnAction((event) -> {
            infoPane.setAnimalController(animalComboBox.getSelectionModel().getSelectedItem());
            editPane.setAnimalController(animalComboBox.getSelectionModel().getSelectedItem());
        });
        this.setTop(animalComboBox);
    }

}
