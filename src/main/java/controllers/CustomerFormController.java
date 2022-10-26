package controllers;

import javafx.fxml.FXML;

public class CustomerFormController extends ContentController {
    private int customerId;

    @FXML
    private void initialize() {
    }

    /* Set by calling controller when an existing customer is being edited. */
    void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
