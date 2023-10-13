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
import ua.edu.dnu.project.filter.BookFilter;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;
import ua.edu.dnu.project.service.BookService;

import java.io.IOException;
import java.util.List;

public class BooksController {

    @FXML
    private TextField searchField;
    @FXML
    private TableColumn<Book, String> titleColumn;
    @FXML
    private TableColumn<Book, String> authorColumn;
    @FXML
    private TableColumn<Book, String> genreColumn;
    @FXML
    private TableColumn<Book, String> bailColumn;
    @FXML
    private TableColumn<Book, String> rentalColumn;
    @FXML
    private TableView<Book> booksTable;
    @FXML
    private ComboBox<String> sortList;
    BookFilter filter;
    @FXML
    public void openFillBookMenu(ActionEvent actionEvent) throws IOException {
        MainPaneController.getInstance().setContent("fillBooks.fxml");
    }

    public void Sort(){
        switch (sortList.getValue()){
            case "Назва": SortByTitle(); break;
            case "Автор": ; SortByAuthor(); break;
            case "Жанр": SortByGenre(); break;
            case "Ціна прокату": SortByPrice(); break;
        }
    }


    @FXML
    private void EditBook(){
        Book book = booksTable.getSelectionModel().getSelectedItem();
        if (book != null) {
            MainPaneController.getInstance().setContent("editBook.fxml");
            ((EditBookController)MainPaneController.getInstance().getContentController()).loadBook(book);
            System.out.println(book);
        }
    }

    @FXML
    private void DeleteBook() throws ServiceException {
        Storage storage = new Storage();
        Book book = booksTable.getSelectionModel().getSelectedItem();
        if (book != null) {
            booksTable.getItems().remove(book);
            storage.books().delete(book.getId());
        }
    }

    @FXML
    private void initialize() throws IOException {
        ObservableList<String> sorts = FXCollections.observableArrayList("Назва", "Автор", "Жанр", "Ціна прокату");
        sortList.setItems(sorts);
        sortList.setValue("За замовченням");
        booksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        filter = new BookFilter(new BookService().getAll());
        List<Book> bookList = filter.setHasStatus(BookStatus.AVAILABLE).select();
        ObservableList<Book> observableBookList = FXCollections.observableArrayList(bookList);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bailColumn.setCellValueFactory(new PropertyValueFactory<>("bailPrice"));
        rentalColumn.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        booksTable.setItems(observableBookList);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null || newValue.isEmpty()) {
                booksTable.setItems(observableBookList);
            } else {
                ObservableList<Book> observableSearchBookList = FXCollections.observableArrayList(filter.setContains(newValue).select());
                booksTable.setItems(observableSearchBookList);
            }
        });
    }

    public void BorrowBook(){
        Book book = booksTable.getSelectionModel().getSelectedItem();
        if (book != null) {
            MainPaneController.getInstance().setContent("borrowBook.fxml");
            ((BorrowBookController)MainPaneController.getInstance().getContentController()).loadBook(book);
            System.out.println(book);
        }
    }

    public void SortByTitle(){
        booksTable.setItems(FXCollections.observableArrayList(filter.setSortedByTitle().select()));
    }

    public void SortByAuthor(){
        booksTable.setItems(FXCollections.observableArrayList(filter.setSortedByAuthor().select()));
    }

    public void SortByPrice(){
        booksTable.setItems(FXCollections.observableArrayList(filter.setSortedByPrice().select()));
    }
    public void SortByGenre(){
        booksTable.setItems(FXCollections.observableArrayList(filter.setSortedByGenre().select()));
    }
}
