package ua.edu.dnu.project;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.edu.dnu.project.filter.RecordFilter;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;
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
    private TextField searchField;
    @FXML
    private ComboBox<String> sortList;

    RecordFilter filter;

    @FXML
    private void initialize(){
        recordsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        List<Record> records = new RecordService().getAll();
        ObservableList<String> sorts = FXCollections.observableArrayList("Ім'я", "Прізвище", "Назва книги", "Дата позичення", "Дата повернення");
        sortList.setItems(sorts);
        ObservableList<Record> observableRecordsList = FXCollections.observableArrayList(records);
        filter = new RecordFilter(new RecordService().getAll());
        nameColumn.setCellValueFactory(record -> new SimpleStringProperty(record.getValue().getUser().getName()));
        surnameColumn.setCellValueFactory(record -> new SimpleStringProperty(record.getValue().getUser().getLastname()));
        booknameColumn.setCellValueFactory(record -> new SimpleStringProperty(record.getValue().getBook().getTitle()));
        dateOfReturnColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        dateOfBorrowColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfReturn"));
        statusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(getStatusInRussian(cellData.getValue().getStatus())));
        recordsTable.setItems(observableRecordsList);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                recordsTable.setItems(observableRecordsList);
            } else {
                ObservableList<Record> observableSearchRecordList = FXCollections.observableArrayList(filter.setContains(newValue).select());
                recordsTable.setItems(observableSearchRecordList);
            }
        });
    }

    @FXML
    private void ReturnBook(){
        if( recordsTable.getSelectionModel().getSelectedItem() != null){
            Record record = recordsTable.getSelectionModel().getSelectedItem();
            Book book = record.getBook();
            if(record.getStatus() != RecordStatus.RETURNED){
                book.setStatus(BookStatus.AVAILABLE);
                record.setStatus(RecordStatus.RETURNED);
                MainPaneController.getInstance().setContent("records.fxml");
            }
        }
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
    public void Sort(){
        switch (sortList.getValue()){
            case "Ім'я": SortByName(); break;
            case "Прізвище": ; SortByLastname(); break;
            case "Назва книги": SortByTitle(); break;
            case "Дата позичення": SortByDateOfIssue(); break;
            case "Дата повернення": SortByDateOfReturn(); break;
        }
    }

    private void SortByName(){
        recordsTable.setItems(FXCollections.observableArrayList(filter.setSortedByName().select()));
    }
    private void SortByLastname(){
        recordsTable.setItems(FXCollections.observableArrayList(filter.setSortedByLastname().select()));
    }
    private void SortByTitle(){
        recordsTable.setItems(FXCollections.observableArrayList(filter.setSortedByTitle().select()));
    }
    private void SortByDateOfIssue(){
        recordsTable.setItems(FXCollections.observableArrayList(filter.setSortedByDateOfIssue().select()));
    }
    private void SortByDateOfReturn(){
        recordsTable.setItems(FXCollections.observableArrayList(filter.setSortedByDateOfReturn().select()));
    }
}


