package controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import models.Appointment;

import java.io.IOException;

public class AppointmentTableController extends ContentController {
    private final ObjectProperty<Appointment> selectedAppointment = new SimpleObjectProperty<>();

    @FXML
    private void initialize() {
        // TODO: implement method
    }

    @FXML
    private void handleNew() throws IOException {
        parentController.goToAppointmentForm();
    }

    @FXML
    private void handleEdit() throws IOException {
        parentController.goToAppointmentForm(selectedAppointment.get());
    }

    @FXML
    private void handleDelete() {
        // TODO: implement method
    }
}
