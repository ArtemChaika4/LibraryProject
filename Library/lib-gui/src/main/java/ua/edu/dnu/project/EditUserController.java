package ua.edu.dnu.project;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import ua.edu.dnu.project.model.User;

public class EditUserController {
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

    private User user;

    // add validation
    public void loadUser(User _user) {
        user = _user;
        name.setText(user.getName());
        surname.setText(user.getLastname());
        patronymic.setText(user.getPatronymic());
        address.setText(user.getAddress());
        phone.setText(user.getPhone().substring(3));
    }

    public void saveUser() {
        if(areAllValidatedFieldsOkay()){
            user.setName(name.getText());
            user.setLastname(name.getText());
            user.setPatronymic(patronymic.getText());
            user.setAddress(address.getText());
            user.setPhone("+38" + phone.getText());
            MainPaneController.getInstance().setContent("users.fxml");
        }
        else{
            return;
        }
    }

    private boolean areAllValidatedFieldsOkay() {
        boolean nameValid = Validation.isMatchesRegexNSP(name);
        boolean surnameValid = Validation.isMatchesRegexNSP(surname);
        boolean patronymicValid = Validation.isMatchesRegexNSP(patronymic);
        boolean addressValid = Validation.isMatchesRegexAddress(address);
        boolean phoneValid = Validation.isMatchesRegexPhone(phone);

        return nameValid && surnameValid && patronymicValid && addressValid && phoneValid;
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
