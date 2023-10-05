package ua.edu.dnu.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.model.User;


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
            Storage strg = new Storage();
            strg.users().create(user);
            //убрать в конце
            strg.save();
            MainPaneController.getInstance().setContent("users.fxml");
        } else {
            //handle
        }
    }

    private boolean areAllValidatedFieldsOkay() {
        boolean nameValid = isBorderColorGreen(name);
        boolean surnameValid = isBorderColorGreen(surname);
        boolean patronymicValid = isBorderColorGreen(patronymic);
        boolean addressValid = isBorderColorGreen(address);
        boolean phoneValid = isBorderColorGreen(phone);

        return nameValid && surnameValid && patronymicValid && addressValid && phoneValid;
    }

    private boolean isBorderColorGreen(TextField textField) {
        String borderColor = textField.getStyle();
        return borderColor == null || borderColor.isEmpty() || borderColor.contains("-fx-border-color: green;");
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
