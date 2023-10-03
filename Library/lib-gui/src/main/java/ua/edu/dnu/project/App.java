package ua.edu.dnu.project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ua.edu.dnu.project.model.Book;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 600);
        stage.setTitle("Бібліотека");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
//        Storage storage = new Storage();
//        storage.books().create(
//                new Book("BookTitle", "BookAuthor", "BookGenre", 100, 200));
//        storage.save();
//        System.out.println(storage.books().getAll());
        launch();
    }
}
