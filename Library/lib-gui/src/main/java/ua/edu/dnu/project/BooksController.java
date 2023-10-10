package ua.edu.dnu.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ua.edu.dnu.project.exception.ServiceException;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.service.BookService;

import java.io.IOException;
import java.util.List;

public class BooksController {
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
    public void openFillBookMenu(ActionEvent actionEvent) throws IOException {
        MainPaneController.getInstance().setContent("fillBooks.fxml");
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
        booksTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        List<Book> bookList = new BookService().getAll();
        ObservableList<Book> observableBookList = FXCollections.observableArrayList(bookList);
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        genreColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        bailColumn.setCellValueFactory(new PropertyValueFactory<>("bailPrice"));
        rentalColumn.setCellValueFactory(new PropertyValueFactory<>("rentalPrice"));
        booksTable.setItems(observableBookList);
    }
}
