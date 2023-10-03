package ua.edu.dnu.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuController {
    public void openUsers(ActionEvent actionEvent) {
        MainPaneController.getInstance().setContent("users.fxml");
    }
    public void openBooks(ActionEvent actionEvent) {
        MainPaneController.getInstance().setContent("books.fxml");
    }
}
