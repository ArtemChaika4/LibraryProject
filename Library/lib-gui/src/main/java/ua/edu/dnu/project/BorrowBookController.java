package ua.edu.dnu.project;


import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.exception.ServiceException;
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

    @FXML
    TextField phoneField;
    @FXML
    private DatePicker dp1;
    @FXML
    private DatePicker dp2;

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

        dp1.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && dp2.getValue() != null) {
                if (newValue.isBefore(dp2.getValue())) {
                    dp1.setStyle("-fx-border-color: green;");
                    dp2.setStyle("-fx-border-color: green;");
                } else {
                    dp1.setStyle("-fx-border-color: red;");
                    dp2.setStyle("-fx-border-color: red;");
                }
            } else {
                dp1.setStyle("");
                dp2.setStyle("");
            }
        });

        dp2.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && dp1.getValue() != null) {
                if (newValue.isAfter(dp1.getValue())) {
                    dp1.setStyle("-fx-border-color: green;");
                    dp2.setStyle("-fx-border-color: green;");
                } else {
                    dp1.setStyle("-fx-border-color: red;");
                    dp2.setStyle("-fx-border-color: red;");
                }
            } else {
                dp1.setStyle("");
                dp2.setStyle("");
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
        if(valid && dp1.getValue() != null && dp2.getValue() != null){
            java.time.LocalDate dateOfIssue = dp1.getValue();
            java.time.LocalDate dateOfReturn = dp2.getValue();
            if(dateOfIssue.isBefore(dateOfReturn)){
                Record record = new Record(book, user, Date.valueOf(dateOfIssue), Date.valueOf(dateOfReturn), RecordStatus.RENTED);
                RecordService service = new RecordService();
                service.create(record);
                MainPaneController.getInstance().setContent("books.fxml");
            }
        }
    }
}
