package ua.edu.dnu.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import ua.edu.dnu.project.model.User;


public class MenuController{
    // its not seen cuz its in menu.fxml
    @FXML
    private TableView<User> usersTable;
    @FXML
    public void openUsers(ActionEvent actionEvent) {
        MainPaneController.getInstance().setContent("users.fxml");
    }
    @FXML
    public void openRecords(ActionEvent actionEvent) { MainPaneController.getInstance().setContent("records.fxml"); }
    @FXML
    public void openBooks(ActionEvent actionEvent) {
        MainPaneController.getInstance().setContent("books.fxml");
    }
}
