package ua.edu.dnu.project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.Record;
import ua.edu.dnu.project.model.RecordStatus;
import ua.edu.dnu.project.service.RecordService;

import java.util.List;

public class RecordsController {

    @FXML
    private TableView<Record> recordsTable;
    @FXML
    private TableColumn<Record, String> nameColumn;
    @FXML
    private TableColumn<Record, String> surnameColumn;
    @FXML
    private TableColumn<Record, String> booknameColumn;
    @FXML
    private TableColumn<Record, String> dateOfReturnColumn;
    @FXML
    private TableColumn<Record, String> dateOfBorrowColumn;
    @FXML
    private TableColumn<Record, String> statusColumn;
    @FXML
    private javafx.scene.control.TableView<Book> booksTable;

    Storage storage;

    Record record;

    @FXML
    private void initialize(){
        List<Record> records = new RecordService().getAll();
        ObservableList<Record> observableRecordsList = FXCollections.observableArrayList(records);

        nameColumn.setCellValueFactory(record -> new SimpleStringProperty(record.getValue().getUser().getName()));
        surnameColumn.setCellValueFactory(record -> new SimpleStringProperty(record.getValue().getUser().getLastname()));
        booknameColumn.setCellValueFactory(record -> new SimpleStringProperty(record.getValue().getBook().getTitle()));
        dateOfReturnColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        dateOfBorrowColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getStatusInRussian(cellData.getValue().getStatus())));
        recordsTable.setItems(observableRecordsList);
    }
    private String getStatusInRussian(RecordStatus status) {
        switch (status) {
            case OVERDUE:
                return "ПРОСТРОЧЕНИЙ";
            case RENTED:
                return "АРЕНДОВАНИЙ";
            case RETURNED:
                return "ПОВЕРНЕНИЙ";
            default:
                return "?";
        }
    }

}


