package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class MainController {
    public static final String LOGIN_FORM = "/fxml/loginFormView.fxml";
    public static final String CUSTOMER_TABLE = "/fxml/customerTableView.fxml";
    public static final String CUSTOMER_FORM = "/fxml/customerFormView.fxml";
    public static final String APPOINTMENT_TABLE = "/fxml/appointmentTableView.fxml";
    public static final String APPOINTMENT_FORM = "/fxml/appointmentFormView.fxml";

    @FXML
    private StackPane contentPane;

    @FXML
    private void initialize() throws IOException {
        renderLoginForm();
    }

    private void renderLoginForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(LOGIN_FORM));
        Node pane = loader.load();
        LoginFormController controller = loader.getController();
        controller.setParentController(this);
        contentPane.getChildren().setAll(pane);
    }

    public void renderCustomerTable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CUSTOMER_TABLE));
        Node pane = loader.load();
        CustomerTableController controller = loader.getController();
        controller.setParentController(this);
        contentPane.getChildren().setAll(pane);
    }

    public void renderCustomerForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(CUSTOMER_FORM));
        Node pane = loader.load();
        CustomerFormController controller = loader.getController();
        controller.setParentController(this);
        contentPane.getChildren().setAll(pane);
    }

    public void renderAppointmentTable() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(APPOINTMENT_TABLE));
        Node pane = loader.load();
        AppointmentTableController controller = loader.getController();
        controller.setParentController(this);
        contentPane.getChildren().setAll(pane);
    }

    public void renderAppointmentForm() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(APPOINTMENT_FORM));
        Node pane = loader.load();
        AppointmentFormController controller = loader.getController();
        controller.setParentController(this);
        contentPane.getChildren().setAll(pane);
    }
}
