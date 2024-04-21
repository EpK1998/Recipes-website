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

public class AddRatingUserController {
    @FXML
    private TextField Rate;
    @FXML
    private TextField IdField;
    @FXML
    public void handleAddRatingRecipeButtonClick(ActionEvent actionEvent) {
        // Resetting styles
        Rate.setStyle("");
        IdField.setStyle("");

        try {
            // Parsing rating and recipe ID
            int rating = Integer.parseInt(Rate.getText().trim());
            Long recipeId = Long.parseLong(IdField.getText().trim());

            // Validating rating range
            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5.");
            }

            // Create a body map containing the ID and rating of the recipe
            Map<String, Object> body = new HashMap<>();
            body.put("rating", rating);
            body.put("id", recipeId);

            // Create a Request object with action "AddRating" and the body containing the recipe ID and rating
            Request request = new Request("AddRating", body);

            // Send the request to the server
            sendToServer(request);

            // Clearing fields
            Rate.clear();
            IdField.clear();
        } catch (NumberFormatException e) {
            // Handling invalid number format
            showError("Please enter valid numbers for Rating and Recipe ID.");
        } catch (IllegalArgumentException e) {
            // Handling rating out of range
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
        stage.setTitle("Recipes and User Application");
    }


}
