package org.example.storeclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.storeclient.model.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.example.storeclient.model.Client.sendToServer;

public class DeleteRecipeAdminController {
    @FXML
    private TextField deleteIdField;
    @FXML
    private void handleDeleteRecipeButtonClick(ActionEvent event) {
        // Resetting style
        deleteIdField.setStyle("");

        try {
            // Parsing recipe ID
            Long recipeId = Long.parseLong(deleteIdField.getText().trim());

            // Create a body map containing the ID of the recipe
            Map<String, Object> body = new HashMap<>();
            body.put("id", recipeId);

            // Create a Request object with action "deleteRecipe" and the body containing the recipe ID
            Request request = new Request("deleteRecipe", body);

            // Send the request to the server
            sendToServer(request);

            // Clearing field
            deleteIdField.clear();
        } catch (NumberFormatException e) {
            // Handling invalid number format
            deleteIdField.setStyle("-fx-border-color: red;");
            showError("Please enter a valid number for Recipe ID.");
        }
    }

    @FXML
    private void handleBackButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Recipes and Admin Application");
    }

    private void showError(String message) {
        // Displaying error message in a little window
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
