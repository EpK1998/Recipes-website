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

public class AddUserController {
        @FXML
        private TextField UserName;
        @FXML
        private TextField Password;


        @FXML
        private void handleAddUserButtonClick(ActionEvent event) {
            // Resetting styles
            UserName.setStyle("");
            Password.setStyle("");

            // Validating inputs
            String Name = UserName.getText().trim();
            Long ID = Long.parseLong(Password.getText().trim());
            try {
                // Checking if fields are empty
                if (Name.isEmpty() || ID.toString().isEmpty() ) {
                    throw new IllegalArgumentException("All fields must be filled.");
                }

                // Creating request body
                Map<String, Object> body = new HashMap<>();
                body.put("name", Name);
                body.put("id", ID);

                // Sending request
                Request request = new Request("AddUser", body);
                sendToServer(request);

                // Clearing fields
                UserName.clear();
                Password.clear();
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
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("Recipes Admin Application");
        }
    }


