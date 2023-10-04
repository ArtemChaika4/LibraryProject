package ua.edu.dnu.project;

import javafx.fxml.FXML;
import ua.edu.dnu.project.db.DBSet;
import ua.edu.dnu.project.model.User;

public class FillUserController {

    //TODO: VALIDATION
    @FXML
    private void addUser(){
        User user = new User("", "", "", "", "");
        Storage strg = new Storage();
        strg.users().create(user);
        strg.save();
    }
}
