package ua.edu.dnu.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class BooksController {
    @FXML
    public void openFillUserMenu(ActionEvent actionEvent) throws IOException {
        MainPaneController.getInstance().setContent("fillBooks.fxml");
    }
}
