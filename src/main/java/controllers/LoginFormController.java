package controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import repositories.UserRepository;

import java.io.IOException;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LoginFormController extends ContentController {
    private final SimpleStringProperty candidateUsername = new SimpleStringProperty();
    private final SimpleStringProperty candidatePassword = new SimpleStringProperty();

    @FXML
    private Label userNameLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label errorLabel;
    @FXML
    private Label locationLabel;
    @FXML
    private Button submitButton;

    private String credentialsErrorMsg;
    private String validationErrorMsg;

    private boolean usernameChanged = false;
    private boolean passwordChanged = false;

    @FXML
    private void initialize() {
        /* Set location */
        locationLabel.setText("\uD83C\uDF0E " + ZoneId.systemDefault());

        /* Content language */
        ResourceBundle content = ResourceBundle.getBundle("properties.content");
        userNameLabel.setText(content.getString("login_username"));
        passwordLabel.setText(content.getString("login_password"));
        submitButton.setText(content.getString("login_submit"));

        validationErrorMsg = content.getString("login_validation_error");
        credentialsErrorMsg = content.getString("login_credentials_error");

        /* Init data bindings */
        usernameField.textProperty().bindBidirectional(candidateUsername);
        passwordField.textProperty().bindBidirectional(candidatePassword);

        /* Register listeners */
        usernameField.textProperty().addListener((obs, oldValue, newValue) -> validate("username"));
        passwordField.textProperty().addListener((obs, oldValue, newValue) -> validate("password"));

    }

    public void handleSubmit(ActionEvent event) throws IOException {
        usernameField.disableProperty().set(true);
        passwordField.disableProperty().set(true);

        if (UserRepository.authenticate(candidateUsername.get(), candidatePassword.get())) {
            parentController.onLogin();
        } else {
            errorLabel.setText(credentialsErrorMsg);
        }

        usernameField.disableProperty().set(false);
        passwordField.disableProperty().set(false);

        event.consume();
    }

    private void setInvalid(String message) {
        errorLabel.setText(message);
        submitButton.disableProperty().set(true);
    }

    private void setValid() {
        errorLabel.setText(null);
        submitButton.disableProperty().set(false);
    }

    private void validate(String src) {
        if (src.equals("username")) {
            usernameChanged = true;
        }
        if (src.equals("password")) {
            passwordChanged = true;
        }

        if (usernameChanged && passwordChanged) {
            if (candidateUsername.get() == null || candidateUsername.get().length() == 0) {
                setInvalid(validationErrorMsg);
                return;
            }

            if (candidatePassword.get() == null || candidatePassword.get().length() == 0) {
                setInvalid(validationErrorMsg);
                return;
            }

            setValid();
        }
    }
}
