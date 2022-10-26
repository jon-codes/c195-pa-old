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
        goToLoginForm();
    }

    /* Navigation utilities (package-private, should only be used by controllers) */

    void goToLoginForm() throws IOException {
        NodeWithControllerResult<LoginFormController> result = createContentNode(LOGIN_FORM);
        renderContentNode(result.node());
    }

    void goToCustomerTable() throws IOException {
        NodeWithControllerResult<CustomerTableController> result = createContentNode(CUSTOMER_TABLE);
        renderContentNode(result.node());
    }

    void goToCustomerForm() throws IOException {
        NodeWithControllerResult<CustomerFormController> result = createContentNode(CUSTOMER_FORM);
        renderContentNode(result.node());
    }

    void goToCustomerForm(int customerId) throws IOException {
        NodeWithControllerResult<CustomerFormController> result = createContentNode(CUSTOMER_FORM);
        result.controller().setCustomerId(customerId);
        renderContentNode(result.node());
    }

    void goToAppointmentTable() throws IOException {
        NodeWithControllerResult<AppointmentTableController> result = createContentNode(APPOINTMENT_TABLE);
        renderContentNode(result.node());
    }

    void goToAppointmentForm() throws IOException {
        NodeWithControllerResult<AppointmentFormController> result = createContentNode(APPOINTMENT_FORM);
        renderContentNode(result.node());
    }

    void goToAppointmentForm(int appointmentId) throws IOException {
        NodeWithControllerResult<AppointmentFormController> result = createContentNode(APPOINTMENT_FORM);
        result.controller().setAppointmentId(appointmentId);
        renderContentNode(result.node());
    }

    /* Navigation implementation utilities */

    private <C extends ContentController> NodeWithControllerResult<C> createContentNode(String location) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
        Node node = loader.load();
        C controller = loader.getController();
        controller.setParentController(this);
        return new NodeWithControllerResult<>(node, controller);
    }

    private void renderContentNode(Node node) {
        contentPane.getChildren().setAll(node);
    }
}

record NodeWithControllerResult<C extends ContentController>(Node node, C controller) { }