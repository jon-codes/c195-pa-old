import controllers.MainController;
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainView.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add("/css/styles.css");

        MainController controller = loader.getController();
        controller.setPrimaryStage(primaryStage);

        ResourceBundle content = ResourceBundle.getBundle("properties.content");
        primaryStage.setTitle(content.getString("app_title") + " - " + content.getString("login_title"));
        primaryStage.setResizable(false);

        Scene scene = new Scene(root, 400, 500);
        scene.getStylesheets().add("/css/styles.css");

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() {
        DbConnection.close();
    }
}
