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

public class UsersController{

    @FXML
    private TableColumn<User, String> nameColumn;
    @FXML
    private TableColumn<User, String> lastnameColumn;
    @FXML
    private TableColumn<User, String> patronymicColumn;
    @FXML
    private TableColumn<User, String> addressColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableView<User> usersTable;
    @FXML
    public void openFillUserMenu(ActionEvent actionEvent) throws IOException {
        MainPaneController.getInstance().setContent("fillUser.fxml");
    }
    @FXML
    private void initialize() throws IOException {
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        usersTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                User user = usersTable.getSelectionModel().getSelectedItem();
                if (user != null) {
                    MainPaneController.getInstance().setContent("editUser.fxml");
                    ((EditUserController)MainPaneController.getInstance().getContentController()).loadUser(user);
                    System.out.println(user.toString());
                }
            }
        });
        List<User> userList = new UserService().getAll();
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        usersTable.setItems(observableUserList);
    }
}