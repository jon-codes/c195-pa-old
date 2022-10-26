package controllers;

import javafx.fxml.FXML;

public class AppointmentFormController extends ContentController {
    private int appointmentId;

    @FXML
    private void initialize() {
    }

    /* Set by calling controller when an existing appointment is being edited. */
    void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}
