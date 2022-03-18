package com.homework.animals;

import com.homework.animals.model.AnimalDatabase;
import com.homework.animals.view.MainPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(new MainPane()));
        stage.setTitle("Домашнаяя работа");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}