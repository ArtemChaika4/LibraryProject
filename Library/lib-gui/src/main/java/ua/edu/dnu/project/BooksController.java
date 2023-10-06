package ua.edu.dnu.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.edu.dnu.project.model.User;
import ua.edu.dnu.project.service.UserService;

import java.io.IOException;
import java.util.List;

public class BooksController {

    @FXML
    private TableColumn<User, String> titleColumn;
    @FXML
    private TableColumn<User, String> authorColumn;
    @FXML
    private TableColumn<User, String> genreColumn;
    @FXML
    private TableColumn<User, String> bailColumn;
    @FXML
    private TableColumn<User, String> rentalColumn;
    @FXML
    private TableView<User> booksTable;
    @FXML
    public void openFillUserMenu(ActionEvent actionEvent) throws IOException {
        MainPaneController.getInstance().setContent("fillBooks.fxml");
    }

    @FXML
    private void initialize() throws IOException {
        booksTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                User user = booksTable.getSelectionModel().getSelectedItem();
                if (user != null) {
                    MainPaneController.getInstance().setContent("editUser.fxml");
                    ((EditUserController)MainPaneController.getInstance().getContentController()).loadUser(user);
                    System.out.println(user.toString());
                }
            }
        });
        List<User> userList = new UserService().getAll();
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        bailColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        rentalColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        booksTable.setItems(observableUserList);
    }
}
