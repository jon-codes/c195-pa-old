package controllers;

import javafx.beans.property.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import models.Customer;
import repositories.CustomerRepository;

import java.io.IOException;

public class CustomerTableController extends ContentController {
    private final ObjectProperty<Customer> selectedCustomer = new SimpleObjectProperty<>();

    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> idColumn;
    @FXML
    private TableColumn<Customer, String> nameColumn;
    @FXML
    private TableColumn<Customer, String> addressColumn;
    @FXML
    private TableColumn<Customer, String> postalCodeColumn;
    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;

    @FXML
    private void initialize() {
        customerTable.setItems(CustomerRepository.list());

        idColumn.setCellValueFactory(p -> new SimpleIntegerProperty(p.getValue().id()).asObject());
        nameColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().name()));
        addressColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().address()));
        postalCodeColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().postalCode()));
        phoneColumn.setCellValueFactory(p -> new SimpleStringProperty(p.getValue().phone()));

        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldCustomer, newCustomer) -> {
            if (newCustomer == null) {
                selectedCustomer.set(null);
                return;
            }
            selectedCustomer.set(newCustomer);
        });

        editButton.disableProperty().bind(selectedCustomer.isNull());
        deleteButton.disableProperty().bind(selectedCustomer.isNull());
    }

    @FXML
    private void handleNew() throws IOException {
        parentController.goToCustomerForm();
    }

    @FXML
    private void handleEdit() throws IOException {
        parentController.goToCustomerForm(selectedCustomer.get().id());
    }

    @FXML
    private void handleDelete(ActionEvent actionEvent) {
        String confirmMessage = "Are you sure you want to delete customer " + selectedCustomer.get().id() + "?";
        String successMessage = "Customer " + selectedCustomer.get().id() + " successfully deleted.";

        Alert confirmAlert = new Alert(AlertType.WARNING, confirmMessage, ButtonType.YES, ButtonType.CANCEL);
        confirmAlert.showAndWait()
                .filter(response -> response == ButtonType.YES)
                .ifPresent(response -> {
                    boolean deleted = CustomerRepository.delete(selectedCustomer.get().id());
                    if (deleted) new Alert(AlertType.INFORMATION, successMessage).showAndWait();
                });
    }
}
