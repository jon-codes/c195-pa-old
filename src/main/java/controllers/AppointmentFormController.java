package controllers;

import javafx.fxml.FXML;
import models.Appointment;

import java.io.IOException;

public class AppointmentFormController extends ContentController {
    @FXML
    private void initialize() {
        // TODO: implement method
    }

    public void setEdit(Appointment appointment) {
        // TODO: implement method
    }

    @FXML
    private void handleCancel() throws IOException {
        parentController.goToAppointmentTable();
    }

    @FXML
    private void handleSubmit() {
        // TODO: implement method
    }
}
