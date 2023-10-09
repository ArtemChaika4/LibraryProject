package ua.edu.dnu.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.model.Book;
import ua.edu.dnu.project.model.BookStatus;
import ua.edu.dnu.project.service.BookService;


// number has 13 symbols
public class FillBookController {
    @FXML
    private TextField title;
    @FXML
    private TextField author;
    @FXML
    private TextField genre;
    @FXML
    private TextField bailPrice;
    @FXML
    private TextField rentalPrice;
    @FXML
    private void addBook() {
        if (Validation.areAllValidatedFieldsBook(title, author, genre, bailPrice, rentalPrice)) {

            Book book = new Book(title.getText(), author.getText(), genre.getText(), Integer.parseInt(bailPrice.getText()), Integer.parseInt(rentalPrice.getText()), BookStatus.AVAILABLE);

            //Storage strg = new Storage(); --> DEPRECATED
            //strg.users().create(user);
            //убрать в конце
            //strg.save(); --> unnecessary

            BookService bookService = new BookService();
            bookService.create(book);
            MainPaneController.getInstance().setContent("books.fxml");

//            } catch (ServiceException e) {  //User already added
//                //view massage
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText(e.getMessage());
//                alert.showAndWait();
//            }
        } else {
            return;
        }
    }

    @FXML
    private void initialize() {
        Validation.addUppercaseValidation(title);
        Validation.addUppercaseValidation(author);
        Validation.addUppercaseValidation(genre);
        Validation.addPriceValidation(bailPrice);
        Validation.addPriceValidation(rentalPrice);
    }
}
