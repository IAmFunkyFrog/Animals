package com.homework.animals.view;

import com.homework.animals.controller.AnimalController;
import com.homework.animals.model.Animal;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import net.synedra.validatorfx.Validator;

import java.util.stream.Collectors;

public class EditPane extends VBox {

    private AnimalController animalController;
    //JavaFX elements
    private Validator validator = new Validator();
    private final Label kindLabel = new Label("Вид");
    private final TextField kindTextField = new TextField();
    private final Label nameLabel = new Label("Кличка");
    private final TextField nameTextField = new TextField();
    private final Label ageYearLabel = new Label("Количество лет");
    private final TextField ageYearTextField = new TextField();
    private final Label ageMonthLabel = new Label("Количество месяцев");
    private final TextField ageMonthTextField = new TextField();
    private final Label ownerNameLabel = new Label("Имя владельца");
    private final TextField ownerNameTextField = new TextField();
    private final Button commitButton = new Button("Ввести новые данные");
    private final TextArea problemOutputTextField = new TextArea();

    public EditPane(AnimalController animalController) {
        setAnimalController(animalController);

        this.setSpacing(10);

        this.getChildren().add(kindLabel);
        this.getChildren().add(kindTextField);
        this.getChildren().add(nameLabel);
        this.getChildren().add(nameTextField);
        this.getChildren().add(ageYearLabel);
        this.getChildren().add(ageYearTextField);
        this.getChildren().add(ageMonthLabel);
        this.getChildren().add(ageMonthTextField);
        this.getChildren().add(ownerNameLabel);
        this.getChildren().add(ownerNameTextField);
        this.getChildren().add(commitButton);
        problemOutputTextField.setEditable(false);
        problemOutputTextField.setPrefHeight(80);
        problemOutputTextField.setBackground(Background.EMPTY);
        problemOutputTextField.setFocusTraversable(false);
        this.getChildren().add(problemOutputTextField);

        this.setOnKeyPressed((event) -> {
            if(event.getCode() == KeyCode.ENTER) {
                commit();
            }
        });
    }

    private String getProblemText() {
        return validator.validationResultProperty().get().getMessages().stream()
                .map(msg -> msg.getSeverity().toString() + ": " + msg.getText())
                .collect(Collectors.joining("\n"));
    }

    public void setAnimalController(AnimalController animalController) {
        this.validator = new Validator();
        StringBinding problemsText = Bindings.createStringBinding(this::getProblemText, validator.validationResultProperty());
        problemOutputTextField.textProperty().bind(problemsText);
        this.animalController = animalController;
        Animal animal = animalController.getAnimal();
        kindTextField.setText(animal.getKind());
        validator.createCheck()
                .dependsOn("kind", kindTextField.textProperty())
                .withMethod(ctx -> {
                    String newKind = ctx.get("kind");
                    if (!newKind.matches("\\D+")) ctx.error("Вид должен содержать только буквы");
                })
                .decorates(kindTextField)
                .immediate();
        nameTextField.setText(animal.getName());
        validator.createCheck()
                .dependsOn("name", nameTextField.textProperty())
                .withMethod(ctx -> {
                    String newKind = ctx.get("name");
                    if (!newKind.matches("\\D+")) ctx.error("Кличка должна содержать только буквы");
                })
                .decorates(nameTextField)
                .immediate();
        ageYearTextField.setText(String.valueOf(animal.getAge() / 12));
        validator.createCheck()
                .dependsOn("ageYear", ageYearTextField.textProperty())
                .withMethod(ctx -> {
                    String newKind = ctx.get("ageYear");
                    if (!newKind.matches("\\d+")) ctx.error("Количество лет должно быть натуральным числом");
                })
                .decorates(ageYearTextField)
                .immediate();
        ageMonthTextField.setText(String.valueOf(animal.getAge() % 12));
        validator.createCheck()
                .dependsOn("ageMonth", ageMonthTextField.textProperty())
                .withMethod(ctx -> {
                    String newKind = ctx.get("ageMonth");
                    if (!newKind.matches("\\d+")) ctx.error("Количество месяцев должно быть натуральным числом");
                })
                .decorates(ageMonthTextField)
                .immediate();
        ownerNameTextField.setText(animal.getOwnerName());
        validator.createCheck()
                .dependsOn("ownerName", ownerNameTextField.textProperty())
                .withMethod(ctx -> {
                    String newKind = ctx.get("ownerName");
                    if (!newKind.matches("\\D+")) ctx.error("Имя владельца должно содержать только буквы");
                })
                .decorates(ownerNameTextField)
                .immediate();
        commitButton.setOnAction((event) -> {
            commit();
        });
    }

    private void commit() {
        if (validator.validate()) {
            animalController.updateKind(kindTextField.getText());
            animalController.updateName(nameTextField.getText());
            animalController.updateOwnerName(ownerNameTextField.getText());
            animalController.updateAge(Integer.parseInt(ageYearTextField.getText()) * 12 + Integer.parseInt(ageMonthTextField.getText()));
        }
    }
}
