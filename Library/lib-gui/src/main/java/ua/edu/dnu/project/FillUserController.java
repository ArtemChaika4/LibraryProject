package ua.edu.dnu.project;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.exception.ServiceException;
import ua.edu.dnu.project.model.User;
import ua.edu.dnu.project.service.UserService;


// number has 13 symbols
public class FillUserController {
    @FXML
    private TextField name;
    @FXML
    private TextField surname;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField address;
    @FXML
    private TextField phone;

    //TODO: VALIDATION
    @FXML
    private void addUser() {
        if (Validation.areAllValidatedFieldsUser(name, surname, patronymic, address, phone)) {
            User user = new User(surname.getText(), name.getText(), patronymic.getText(), address.getText(), "+38" + phone.getText());
            //Storage strg = new Storage(); --> DEPRECATED
            //strg.users().create(user);
            //убрать в конце
            //strg.save(); --> unnecessary
            UserService userService = new UserService();
            try {
                userService.create(user);
                MainPaneController.getInstance().setContent("users.fxml");
            } catch (ServiceException e) {  //User already added
                //view massage
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(e.getMessage());
                alert.showAndWait();
            }
        } else {
            return;
        }
    }

    @FXML
    private void initialize() {
        Validation.addUppercaseValidation(name);
        Validation.addUppercaseValidation(surname);
        Validation.addUppercaseValidation(patronymic);
        Validation.addAddressValidation(address);
        Validation.addPhoneNumberValidation(phone);
    }
}
