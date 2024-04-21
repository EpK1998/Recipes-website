package org.example.storeclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AdminLoginController {

    private String adminUsername;
    private String adminPassword;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    public void setAdminCredentials(String username, String password) {
        adminUsername = username;
        adminPassword = password;
    }

    public boolean isAdminAuthenticated() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    @FXML
    private void handleLoginButton(ActionEvent event) {
        if (isAdminAuthenticated()) {
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
