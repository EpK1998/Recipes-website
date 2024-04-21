package org.example.storeclient;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.storeclient.model.Recipe;
import org.example.storeclient.model.Request;
import org.example.storeclient.model.User;
import org.example.storeclient.model.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import static org.example.storeclient.model.Client.sendToServer;

public class AdminViewController {

    @FXML
    private void handleSearchButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Adminsearch-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("Search recipe Functions");
    }
    @FXML
    private void handleAddRecipe1ButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("addrecipe-view.fxml"));
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
    public void handleDeleteRecipeButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("deleterecipe-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("delete recipe Functions");
    }

    public void handleshowallrecipesButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("Adminshowall-view.fxml"));
        stage.setScene(new Scene(root, 1000, 600));
        stage.setTitle("delete recipe Functions");
    }

}
