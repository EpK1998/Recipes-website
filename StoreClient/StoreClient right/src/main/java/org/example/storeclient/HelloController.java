package org.example.storeclient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.storeclient.model.Client;
import org.example.storeclient.model.Request;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HelloController {

    private final String adminUsername = "nissim";
    private final String adminPassword = "barami";

    @FXML
    private void handleAdminButtonClick(ActionEvent event) throws IOException {
        // Create a new stage for the login window
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setTitle("Admin Login");

        // Load the login window FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin-login.fxml"));
        Parent root = fxmlLoader.load();
        AdminLoginController controller = fxmlLoader.getController();
        controller.setAdminCredentials(adminUsername, adminPassword);

        // Set the scene and show the login window
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.showAndWait();

        // Check if login was successful
        if (controller.isAdminAuthenticated()) {
            // Open the admin view
            Stage mainStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent adminRoot = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
            mainStage.setScene(new Scene(adminRoot, 1000, 600));
            mainStage.setTitle("Admin Functions");
        } else {
            // Show error message if login failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("application.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
    }


    @FXML
    private void handleUserButtonClick(ActionEvent event) throws IOException {
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.setTitle("User Login");

        // Load the login window FXML file
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-login.fxml"));
        Parent root = fxmlLoader.load();
        UserloginController controller = fxmlLoader.getController();
        controller.setUserCredentials("","");

        // Set the scene and show the login window
        Scene scene = new Scene(root);
        loginStage.setScene(scene);
        loginStage.showAndWait();

        // Check if login was successful
        if (controller.isUserAuthenticated()) {
            // Open the admin view
            Stage mainStage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent UserRoot = FXMLLoader.load(getClass().getResource("User-view.fxml"));
            mainStage.setScene(new Scene(UserRoot, 1000, 600));
            mainStage.setTitle( controller.getusername() + " Functions");
        } else {
            // Show error message if login failed
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password.");
            DialogPane dialogPane = alert.getDialogPane();
            dialogPane.getStylesheets().add(
                    getClass().getResource("application.css").toExternalForm());
            dialogPane.getStyleClass().add("myDialog");
            alert.showAndWait();
        }
    }
    @FXML
    private void handleAddUserButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AddUser.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Add User Functions");
    }

    // Dummy authentication method (replace with actual authentication logic)
}
