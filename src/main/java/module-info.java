module com.homework.animals {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires validatorfx;

    opens com.homework.animals to javafx.fxml;
    exports com.homework.animals;
}