package ua.edu.dnu.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import ua.edu.dnu.project.model.User;

public class UsersController {
    public void openFillUserMenu(ActionEvent actionEvent) {
        MainPaneController.getInstance().setContent("fillUser.fxml");
    }
}
