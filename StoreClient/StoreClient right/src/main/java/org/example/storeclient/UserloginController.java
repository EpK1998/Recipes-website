package org.example.storeclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.example.storeclient.model.Client;
import org.example.storeclient.model.Request;

import java.util.HashMap;
import java.util.Map;

public class UserloginController {

    private String UserUsername;
    private String UserPassword;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void setUserCredentials(String username, String password) {
        UserUsername = username;
        UserPassword = password;
    }
    public String getusername()
    {
        return UserUsername;
    }


    public boolean isUserAuthenticated() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (!username.isEmpty() && !password.isEmpty()) {
            Map<String, Object> body = new HashMap<>();
            body.put("id", password);
            body.put("name", username);
            Request request = new Request("LogIn", body);

            // Receive and process response from server
            boolean answer = Client.sendToServerLogin(request);
            if (answer == true) {
                UserUsername = username;
                return true;
            }
        }
        return false;
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        if (isUserAuthenticated()) {
            // Close the login window if login is successful
            usernameField.getScene().getWindow().hide();
        } else {
            // Show error message for invalid username or password
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid username or password.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
