package ua.edu.dnu.project;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import ua.edu.dnu.project.db.LibraryDB;
import ua.edu.dnu.project.exception.ServiceException;

import java.io.FileNotFoundException;

public class App extends Application
{
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("main.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 900, 600);
        stage.setTitle("Бібліотека");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
        loadDB();
    }

    //helper to create alert with error msg
    private static Alert createErrorAlert(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Отакої...");
        alert.setHeaderText("Помилка");
        alert.setContentText(msg);
        return alert;
    }

    @Override
    public void stop() {
        try {
            LibraryDB.getInstance().save();
        } catch (FileNotFoundException e) {
            createErrorAlert(e.getMessage())
                    .showAndWait();
        }
    }

    private static void loadDB(){
        LibraryDB libraryDB = LibraryDB.getInstance();
        try {
            libraryDB.load();
        } catch (ServiceException e) {
            e.toLog();
            libraryDB.clear();  //clears db as the file has been corrupted
        } catch (FileNotFoundException e) {
            createErrorAlert(e.getMessage())
                    .showAndWait()
                    .filter(resp -> resp == ButtonType.OK)
                    .ifPresent(resp -> Platform.exit());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
