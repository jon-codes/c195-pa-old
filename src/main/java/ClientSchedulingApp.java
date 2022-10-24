import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.DbConnection;

import java.io.IOException;
import java.util.ResourceBundle;

public class ClientSchedulingApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        DbConnection.open();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginView.fxml"));
        root.getStylesheets().add("/css/styles.css");

        ResourceBundle content = ResourceBundle.getBundle("properties.content");
        primaryStage.setTitle(content.getString("app_title"));
        primaryStage.setScene(new Scene(root, 300, 400));
        primaryStage.show();
    }

    @Override
    public void stop() {
        DbConnection.close();
    }
}
