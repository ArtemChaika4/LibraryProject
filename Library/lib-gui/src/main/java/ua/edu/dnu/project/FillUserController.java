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
        if (areAllValidatedFieldsOkay()) {

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
            //ЕЩВЩ: handle
        }
    }

    private boolean isMatchesRegexNSP(TextField value){

        return value.getText().matches("^[А-ЯЇҐЄІ]('?[а-яїієґ]){1,30}$");
    }

    private boolean isMatchesRegexAddress(TextField value){
        return value.getText().matches("^[А-ЯЇҐЄІ]('?[а-яїієґ0-9\\s]){6,50}$");
    }

    private  boolean isMatchesRegexPhone(TextField value){
        return value.getText().matches("^[0-9]+$");
    }

    private boolean areAllValidatedFieldsOkay() {
        boolean nameValid = isMatchesRegexNSP(name);
        boolean surnameValid = isMatchesRegexNSP(surname);
        boolean patronymicValid = isMatchesRegexNSP(patronymic);
        boolean addressValid = isMatchesRegexAddress(address);
        boolean phoneValid = isMatchesRegexPhone(phone);

        return nameValid && surnameValid && patronymicValid && addressValid && phoneValid;
    }

    @FXML
    private void initialize() {
        addUppercaseValidation(name);
        addUppercaseValidation(surname);
        addUppercaseValidation(patronymic);
        addAddressValidation(address);
        addPhoneNumberValidation(phone);
    }

    private void addAddressValidation(TextField textField){
        textField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.isEmpty()){
                String address = newValue;
                if(!address.matches(("^[А-ЯЇҐЄІ]('?[а-яїієґ0-9\\s]){6,50}$"))){
                    textField.setStyle("-fx-border-color: red;");
                }
                else {
                    textField.setStyle("-fx-border-color: green;");
                    textField.setText(address);
                }
            }
            else{
                textField.setStyle("-fx-border-color: none");
            }
        }));
    }
    private void addPhoneNumberValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                String phoneNumber = newValue;
                if (!phoneNumber.matches("^[0-9]+$") || phoneNumber.length() != 10 || phoneNumber.contains(" ")) {
                    textField.setStyle("-fx-border-color: red;");
                } else {
                    textField.setStyle("-fx-border-color: green;");
                    textField.setText(phoneNumber);
                }
            } else {
                textField.setStyle("-fx-border-color: none");
            }
        });
    }
    private void addUppercaseValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                String text = newValue;
                if (!text.matches("^[А-ЯЇҐЄІ]('?[а-яїієґ]){1,30}$")) {
                    textField.setStyle("-fx-border-color: red;");
                    textField.setText(text);
                }
                else {
                    textField.setStyle("-fx-border-color: green;");
                }
            } else {
                textField.setStyle("");
            }
        });
    }
}
