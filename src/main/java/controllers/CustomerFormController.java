package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class CustomerFormController extends ContentController {
    private int customerId;

    /* Set by calling controller when an existing customer is being edited. */
    void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @FXML
    private void initialize() {
    }

    @FXML
    private void handleCancel(ActionEvent actionEvent) throws IOException {
        parentController.goToCustomerTable();
    }

    @FXML
    private void handleSubmit(ActionEvent actionEvent) {
        // TODO: implement
    }
}
