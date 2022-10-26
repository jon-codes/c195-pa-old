package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class AppointmentFormController extends ContentController {
    private int appointmentId;

    /* Set by calling controller when an existing appointment is being edited. */
    void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) throws IOException {
        parentController.goToAppointmentTable();
    }

    @FXML
    private void handleSubmit(ActionEvent actionEvent) {
        // TODO: implement
    }
}
