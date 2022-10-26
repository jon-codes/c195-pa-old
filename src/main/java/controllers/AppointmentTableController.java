package controllers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AppointmentTableController extends ContentController {
    private final IntegerProperty selectedAppointmentId = new SimpleIntegerProperty();

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleNew(ActionEvent actionEvent) throws IOException {
        parentController.goToAppointmentForm();
    }

    @FXML
    private void handleEdit(ActionEvent actionEvent) {
        // TODO: implement
    }

    @FXML
    private void handleDelete(ActionEvent actionEvent) {
        // TODO: implement
    }
}
