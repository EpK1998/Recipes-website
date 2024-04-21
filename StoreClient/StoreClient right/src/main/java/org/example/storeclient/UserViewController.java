package org.example.storeclient;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.storeclient.model.Client;
import org.example.storeclient.model.Recipe;
import org.example.storeclient.model.Request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.storeclient.model.Client.sendToServer;

public class UserViewController {

    @FXML
    private void handleSearchButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Usersearch-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Search recipe Functions");
    }
    @FXML
    private void handleAddRecipe1ButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Useraddrecipe-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Add recipe Functions");
    }

    @FXML
    private void handleBackButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Recipes and Users Application");
    }
    @FXML
    public void handleshowallrecipesButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Usershowall-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Show All recipes Functions");
    }
    @FXML
    public void handleRateButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("addrating-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Add Rating recipe Functions");
    }
}
