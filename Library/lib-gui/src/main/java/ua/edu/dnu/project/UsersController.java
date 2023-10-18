package ua.edu.dnu.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.edu.dnu.project.exception.ServiceException;
import ua.edu.dnu.project.filter.UserFilter;
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
    private ComboBox<String> sortList;
    @FXML
    private TextField searchField;
    UserFilter filter;

    @FXML
    public void Sort(){
        switch (sortList.getValue()){
            case "Ім'я": SortByName(); break;
            case "Прізвище": ; SortByLastname(); break;
            case "По батькові": SortByPatronymic(); break;
            case "Адреса": SortByAddress(); break;
        }
    }

    @FXML
    public void openFillUserMenu(ActionEvent actionEvent) throws IOException {
        MainPaneController.getInstance().setContent("fillUser.fxml");
    }

    @FXML
    private void EditUser(){
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            MainPaneController.getInstance().setContent("editUser.fxml");
            ((EditUserController)MainPaneController.getInstance().getContentController()).loadUser(user);
            System.out.println(user);
        }
    }

    @FXML
    private void DeleteUser() throws ServiceException {
        Storage storage = new Storage();
        User user = usersTable.getSelectionModel().getSelectedItem();
        if (user != null) {
            usersTable.getItems().remove(user);
            storage.users().delete(user.getId());
        }
    }

    @FXML
    private void initialize() throws IOException {
        ObservableList<String> sorts = FXCollections.observableArrayList("Ім'я", "Прізвище", "По батькові", "Адреса");
        sortList.setItems(sorts);
        sortList.setValue("За замовченням");
        usersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        List<User> userList = new UserService().getAll();
        filter = new UserFilter(new UserService().getAll());
        ObservableList<User> observableUserList = FXCollections.observableArrayList(userList);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        usersTable.setItems(observableUserList);

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                usersTable.setItems(observableUserList);
            } else {
                ObservableList<User> observableSearchUserList = FXCollections.observableArrayList(filter.setContains(newValue).select());
                usersTable.setItems(observableSearchUserList);
            }
        });
    }

    private void SortByName(){
        usersTable.setItems(FXCollections.observableArrayList(filter.setSortedByName().select()));
    }

    private void SortByLastname(){
        usersTable.setItems(FXCollections.observableArrayList(filter.setSortedByLastname().select()));
    }

    private void SortByAddress(){
        usersTable.setItems(FXCollections.observableArrayList(filter.setSortedByAddress().select()));
    }

    private void SortByPatronymic(){
        usersTable.setItems(FXCollections.observableArrayList(filter.setSortedByPatronymic().select()));
    }
}