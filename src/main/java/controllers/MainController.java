package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ResourceBundle;

public class MainController {

    /* Primary stage (set in application main method, allows setting the title) */
    private Stage primaryStage;

    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;
    }

    private void updateTitle(String screenName) {
    }

    /* Navigation properties */
    public static final String LOGIN_FORM = "/fxml/loginFormView.fxml";
    public static final String CUSTOMER_TABLE = "/fxml/customerTableView.fxml";
    public static final String CUSTOMER_FORM = "/fxml/customerFormView.fxml";
    public static final String APPOINTMENT_TABLE = "/fxml/appointmentTableView.fxml";
    public static final String APPOINTMENT_FORM = "/fxml/appointmentFormView.fxml";
    public static final String REPORT_TABLE = "/fxml/reportTableView.fxml";

    @FXML
    private StackPane contentPane;

    /* Navigation button handlers */
    @FXML
    private Button customerNavButton;
    @FXML
    private Button appointmentNavButton;
    @FXML
    public Button reportNavButton;

    @FXML
    private void handleCustomerNav(ActionEvent event) throws IOException {
        goToCustomerTable();
        event.consume();
    }

    @FXML
    private void handleAppointmentNav(ActionEvent event) throws IOException {
        goToAppointmentTable();
        event.consume();
    }

    public void handleReportNav(ActionEvent event) throws IOException {
        goToReportTable();
        event.consume();
    }

    @FXML
    private void initialize() throws IOException {
        goToLoginForm();
    }

    public void onLogin() throws IOException {
        goToCustomerTable();
    }

    /* Navigation utilities (package-private, should only be used by controllers) */

    void goToLoginForm() throws IOException {
        NodeControllerResult<LoginFormController> result = createContentNode(LOGIN_FORM);
        renderContentNode(result.node());
    }

    void goToCustomerTable() throws IOException {
        NodeControllerResult<CustomerTableController> result = createContentNode(CUSTOMER_TABLE);
        renderContentNode(result.node());

        ResourceBundle content = ResourceBundle.getBundle("properties.content");
        primaryStage.setTitle(content.getString("app_title") + " - " + content.getString("customer_title"));

        // TODO: handle this via some observable property?
        customerNavButton.disableProperty().set(true);
        appointmentNavButton.disableProperty().set(false);
        reportNavButton.disableProperty().set(false);
    }

    void goToCustomerForm() throws IOException {
        NodeControllerResult<CustomerFormController> result = createContentNode(CUSTOMER_FORM);
        renderContentNode(result.node());
    }

    void goToCustomerForm(int customerId) throws IOException {
        NodeControllerResult<CustomerFormController> result = createContentNode(CUSTOMER_FORM);
        result.controller().setCustomerId(customerId);
        renderContentNode(result.node());
    }

    void goToAppointmentTable() throws IOException {
        NodeControllerResult<AppointmentTableController> result = createContentNode(APPOINTMENT_TABLE);
        renderContentNode(result.node());

        ResourceBundle content = ResourceBundle.getBundle("properties.content");
        primaryStage.setTitle(content.getString("app_title") + " - " + content.getString("appointment_title"));

        customerNavButton.disableProperty().set(false);
        appointmentNavButton.disableProperty().set(true);
        reportNavButton.disableProperty().set(false);
    }

    void goToAppointmentForm() throws IOException {
        NodeControllerResult<AppointmentFormController> result = createContentNode(APPOINTMENT_FORM);
        renderContentNode(result.node());
    }

    void goToAppointmentForm(int appointmentId) throws IOException {
        NodeControllerResult<AppointmentFormController> result = createContentNode(APPOINTMENT_FORM);
        result.controller().setAppointmentId(appointmentId);
        renderContentNode(result.node());
    }

    void goToReportTable() throws IOException {
        NodeControllerResult<ReportTableController> result = createContentNode(REPORT_TABLE);
        renderContentNode(result.node());

        ResourceBundle content = ResourceBundle.getBundle("properties.content");
        primaryStage.setTitle(content.getString("app_title") + " - " + content.getString("report_title"));

        customerNavButton.disableProperty().set(false);
        appointmentNavButton.disableProperty().set(false);
        reportNavButton.disableProperty().set(true);
    }

    /* Navigation implementation utilities */

    private static record NodeControllerResult<C extends ContentController>(Node node, C controller) { }

    private <C extends ContentController> NodeControllerResult<C> createContentNode(String location) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
        Node node = loader.load();
        C controller = loader.getController();
        controller.setParentController(this);
        return new NodeControllerResult<>(node, controller);
    }

    private void renderContentNode(Node node) {
        contentPane.getChildren().setAll(node);
        contentPane.requestFocus();
    }
}