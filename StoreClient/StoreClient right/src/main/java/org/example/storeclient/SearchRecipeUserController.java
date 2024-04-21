package org.example.storeclient;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.storeclient.model.Client;
import org.example.storeclient.model.Recipe;
import org.example.storeclient.model.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchRecipeUserController {


        @FXML
        private TextField searchField;
        @FXML
        private void handleBackButtonClick(ActionEvent event) throws IOException {
            Stage stage = (Stage)((javafx.scene.Node) event.getSource()).getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("User-view.fxml"));
            stage.setScene(new Scene(root, 1000, 600));
            stage.setTitle("Recipes and User Application");
        }
        @FXML
        private TableView<Recipe> recipeTable;

        @FXML
        private TableColumn<Recipe, Long> idColumn;

        @FXML
        private TableColumn<Recipe, String> chefColumn;

        @FXML
        private TableColumn<Recipe, String> titleColumn;

        @FXML
        private TableColumn<Recipe, String> descriptionColumn;
        @FXML
        private TableColumn<Recipe, String> meanRatingColumn;

        @FXML
        private void initialize() {
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            chefColumn.setCellValueFactory(new PropertyValueFactory<>("chef"));
            titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
            meanRatingColumn.setCellValueFactory(new PropertyValueFactory<>("meanRating"));
            // Set the description column to auto-resize to fit content
            descriptionColumn.setPrefWidth(400); // Set a default width or adjust as needed
            descriptionColumn.setMinWidth(200); // Set a minimum width to prevent too narrow resizing
            descriptionColumn.setMaxWidth(800); // Set a maximum width to prevent too wide resizing
            // Define a custom cell factory for the description column
            descriptionColumn.setCellFactory(column -> {
                return new TableCell<Recipe, String>() {
                    @Override
                    protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setText(null);
                            setTooltip(null);
                        } else {
                            // Split the description into chunks of 10 words
                            String[] words = item.split("\\s+");
                            StringBuilder wrappedDescription = new StringBuilder();
                            int wordCount = 0;
                            for (String word : words) {
                                wrappedDescription.append(word).append(" ");
                                wordCount++;
                                // Add a line break after every 10 words
                                if (wordCount % 12 == 0) {
                                    wrappedDescription.append("\n");
                                }
                            }
                            setText(wrappedDescription.toString().trim());
                            setTooltip(new Tooltip(item)); // Show full text as tooltip
                        }
                    }
                };
            });
        }
    @FXML
    public void handleSearchButtonClick(ActionEvent actionEvent) {
        // Resetting style
        searchField.setStyle("");

        try {
            String searchTitle = searchField.getText().trim();

            if (searchTitle.isEmpty()) {
                throw new IllegalArgumentException("Please enter a search title.");
            }

            // Create a body map containing the title to search for
            Map<String, Object> body = new HashMap<>();
            body.put("title", searchTitle);
            Request request = new Request("SearchByTitle", body);

            // Receive and process response from server
            List<Recipe> recipes = Client.sendToServerSearch(request);
            if (recipes != null) {
                // Sort recipes by title
                recipes.sort((r1, r2) -> r1.getTitle().compareToIgnoreCase(r2.getTitle()));

                // Update table view with fetched recipes
                ObservableList<Recipe> recipeList = FXCollections.observableArrayList(recipes);
                recipeTable.setItems(recipeList); // Set the items of the TableView

                if (recipeList.isEmpty()) {
                    showAlert("No recipes found with the given title.");
                }
            } else {
                System.out.println("No response from server.");
            }
        } catch (IllegalArgumentException e) {
            // Handling empty search field
            searchField.setStyle("-fx-border-color: red;");
            showError(e.getMessage());
        }
    }

    private void showAlert(String message) {
        // Displaying alert message to the user
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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
