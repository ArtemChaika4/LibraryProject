package ua.edu.dnu.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.model.Book;

public class EditBookController {
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

    private Book book;

    public void loadBook(Book book){
        this.book = book;
        title.setText(book.getTitle());
        author.setText(book.getAuthor());
        genre.setText(book.getGenre());
        bailPrice.setText(String.valueOf(book.getBailPrice()));
        rentalPrice.setText(String.valueOf(book.getRentalPrice()));
    }

    public void saveBook(){
        if(Validation.areAllValidatedFieldsBook(title, author, genre, bailPrice, rentalPrice)){
            book.setTitle(title.getText());
            book.setAuthor(author.getText());
            book.setGenre(genre.getText());
            book.setBailPrice(Integer.parseInt(bailPrice.getText()));
            book.setRentalPrice(Integer.parseInt(rentalPrice.getText()));
            MainPaneController.getInstance().setContent("books.fxml");
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
