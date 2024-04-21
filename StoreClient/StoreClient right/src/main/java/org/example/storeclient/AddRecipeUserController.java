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

public class AddRecipeUserController {
        @FXML
        private TextField recipeTitleField;
        @FXML
        private TextField recipeDescriptionField;
        @FXML
        private TextField cheffield;

    @FXML
    private void handleAddRecipeButtonClick(ActionEvent event) {
        // Resetting styles
        recipeTitleField.setStyle("");
        recipeDescriptionField.setStyle("");
        cheffield.setStyle("");

        // Validating inputs
        String recipeName = recipeTitleField.getText().trim();
        String recipeDescription = recipeDescriptionField.getText().trim();
        String chefName = cheffield.getText().trim();
        try {
            // Checking if fields are empty
            if (recipeName.isEmpty() || recipeDescription.isEmpty() || chefName.isEmpty()) {
                throw new IllegalArgumentException("All fields must be filled.");
            }

            // Creating request body
            Map<String, Object> body = new HashMap<>();
            body.put("chef", chefName);
            body.put("title", recipeName);
            body.put("description", recipeDescription);

            // Sending request
            Request request = new Request("addRecipe", body);
            sendToServer(request);

            // Clearing fields
            cheffield.clear();
            recipeTitleField.clear();
            recipeDescriptionField.clear();
        } catch (IllegalArgumentException e) {
            // Handling empty fields
            showError(e.getMessage());
        }
    }

    private void showError(String message) {
        // Displaying error message in a little window
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
        @FXML
        private void handleBackButtonClick(ActionEvent event) throws IOException {
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("User-view.fxml"));
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("Recipes User Application");
        }
}
