package ua.edu.dnu.project;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.exception.ServiceException;
import ua.edu.dnu.project.filter.UserFilter;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.RecordStatus;
import ua.edu.dnu.project.model.User;
import ua.edu.dnu.project.service.RecordService;
import ua.edu.dnu.project.service.UserService;

import java.sql.Date;
import java.util.List;

public class BorrowBookController {
    public Book book;
    private Storage stg;

    UserFilter filter;

    @FXML
    TextField phoneField;
    User user;

    boolean valid = false;

    public void loadBook(Book book){
        this.book = book;
    }

    @FXML
    private void initialize(){
        phoneField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (newValue.matches("^[0-9]{10}$")) {
                    User foundUser = findUser(newValue);
                    if (foundUser != null) {
                        phoneField.setStyle("-fx-border-color: green;");
                        this.user = foundUser;
                        valid = true;
                    } else {
                        phoneField.setStyle("-fx-border-color: red;");
                        valid = false;
                    }
                } else {
                    phoneField.setStyle("-fx-border-color: red;");
                    valid = false;
                }
            } else {
                phoneField.setStyle("-fx-border-color: none");
            }
        });
    }

    public User findUser(String number){
        List<User> users = new UserService().getAll();
        for (User user : users) {
            if (user.getPhone().substring(3).matches(number)) {
                return user;
            }
        }
        return null;
    }

    public void addRecord() throws ServiceException {
        if(valid){
            Record record = new Record(book, user, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), RecordStatus.RENTED);
            RecordService service = new RecordService();
            service.create(record);
            MainPaneController.getInstance().setContent("books.fxml");
        }
        else {

        }
    }
}
