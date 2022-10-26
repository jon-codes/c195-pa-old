package controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;

public class AppointmentTableController extends ContentController {
    private SimpleIntegerProperty selectedAppointmentId = new SimpleIntegerProperty();

    @FXML
    private void initialize() {
    }
}
