package controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import models.Country;
import models.Customer;
import models.Division;
import repositories.CountryRepository;

import java.io.IOException;

public class CustomerFormController extends ContentController {
    private final SimpleIntegerProperty idValue = new SimpleIntegerProperty();
    private final SimpleStringProperty nameValue = new SimpleStringProperty();
    private final SimpleStringProperty addressValue = new SimpleStringProperty();
    private final SimpleStringProperty postalCodeValue = new SimpleStringProperty();
    private final ObjectProperty<Country> countryValue = new SimpleObjectProperty<>();
    private final ObjectProperty<Division> divisionValue = new SimpleObjectProperty<>();
    private final SimpleStringProperty phoneValue = new SimpleStringProperty();

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


    @FXML
    private void initialize() {
        /* Init text fields */
        nameField.textProperty().bindBidirectional(nameValue);
        addressField.textProperty().bindBidirectional(addressValue);
        postalCodeField.textProperty().bindBidirectional(postalCodeValue);
        phoneField.textProperty().bind(phoneValue);

        /* Init combo fields */
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

        countryComboBox.valueProperty().bindBidirectional(countryValue);
        countryComboBox.getSelectionModel().selectFirst();

        divisionComboBox.setItems(countryValue.get().getDivisions());
        Callback<ListView<Division>, ListCell<Division>> divisionFactory = lv -> new ListCell<>() {
            @Override
            protected void updateItem(Division division, boolean empty) {
                super.updateItem(division, empty);
                setText(empty ? "" : division.name());
            }
        };
        divisionComboBox.setButtonCell(divisionFactory.call(null));
        divisionComboBox.setCellFactory(divisionFactory);

        divisionComboBox.valueProperty().bindBidirectional(divisionValue);
        divisionComboBox.getSelectionModel().selectFirst();

        countryValue.addListener((obs, oldValue, newValue) -> {
            divisionComboBox.setItems(countryValue.get().getDivisions());
            divisionComboBox.getSelectionModel().selectFirst();
        });
    }

    public void setEdit(Customer customer) {
        /* Set (non-editable) id value */
        idValue.set(customer.id());
        idField.setText(customer.id().toString());

        /* Set editable values */
        nameValue.set(customer.name());
        addressValue.set(customer.address());
        postalCodeValue.set(customer.postalCode());
        customer.getCountry().ifPresent(countryValue::set);
        customer.getDivision().ifPresent(divisionValue::set);
        phoneValue.set(customer.phone());
    }

    @FXML
    private void handleCancel() throws IOException {
        parentController.goToCustomerTable();
    }

    @FXML
    private void handleSubmit() {
        // TODO: implement method
    }
}
