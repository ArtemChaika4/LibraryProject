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

    public void loadUser(User user) {
        this.user = user;
        name.setText(user.getName());
        surname.setText(user.getLastname());
        patronymic.setText(user.getPatronymic());
        address.setText(user.getAddress());
        phone.setText(user.getPhone().substring(3));
    }

    public void saveUser() {
        if(Validation.areAllValidatedFieldsUser(name, surname, patronymic, address, phone)){
            user.setName(name.getText());
            user.setLastname(name.getText());
            user.setPatronymic(patronymic.getText());
            user.setAddress(address.getText());
            user.setPhone("+38" + phone.getText());
            MainPaneController.getInstance().setContent("users.fxml");
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
