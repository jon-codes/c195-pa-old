package controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import models.Country;
import models.Division;
import repositories.CountryRepository;

import java.io.IOException;

public class CustomerFormController extends ContentController {
    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private ComboBox<Division> divisionComboBox;
    @FXML
    private ComboBox<Country> countryComboBox;
    @FXML
    private TextField phoneField;

    private final ObjectProperty<Country> selectedCountry = new SimpleObjectProperty<>();
    private final ObjectProperty<Division> selectedDivision = new SimpleObjectProperty<>();

    private int customerId;

    /* Set by calling controller when an existing customer is being edited. */
    void setCustomerId(int customerId) { this.customerId = customerId; }

    @FXML
    private void initialize() {
        countryComboBox.setItems(CountryRepository.list());
        Callback<ListView<Country>, ListCell<Country>> countryFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Country country, boolean empty) {
                super.updateItem(country, empty);
                setText(empty ? "" : country.name());
            }
        };
        countryComboBox.setButtonCell(countryFactory.call(null));
        countryComboBox.setCellFactory(countryFactory);

        countryComboBox.valueProperty().bindBidirectional(selectedCountry);
        countryComboBox.getSelectionModel().selectFirst();

        divisionComboBox.setItems(selectedCountry.get().getDivisions());
        Callback<ListView<Division>, ListCell<Division>> divisionFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Division division, boolean empty) {
                super.updateItem(division, empty);
                setText(empty ? "" : division.name());
            }
        };
        divisionComboBox.setButtonCell(divisionFactory.call(null));
        divisionComboBox.setCellFactory(divisionFactory);

        divisionComboBox.valueProperty().bindBidirectional(selectedDivision);
        divisionComboBox.getSelectionModel().selectFirst();

        selectedCountry.addListener((obs, oldValue, newValue) -> {
            divisionComboBox.setItems(selectedCountry.get().getDivisions());
            divisionComboBox.getSelectionModel().selectFirst();
        });

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
