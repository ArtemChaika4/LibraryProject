package ua.edu.dnu.project;

import javafx.scene.control.TextField;

public class Validation {

    public static boolean areAllValidatedFieldsUser(TextField name, TextField surname, TextField patronymic, TextField address, TextField phone){
        return Validation.isMatchesRegexNSP(name) && Validation.isMatchesRegexNSP(surname) && Validation.isMatchesRegexNSP(patronymic) && Validation.isMatchesRegexAddress(address) && Validation.isMatchesRegexPhone(phone);
    }
    public static boolean areAllValidatedFieldsBook(TextField title, TextField author, TextField genre, TextField bailPrice, TextField rentalPrice){
        return Validation.isMatchesRegexNSP(title) && Validation.isMatchesRegexNSP(author) && Validation.isMatchesRegexNSP(genre) && Validation.isMatchesRegexPrice(bailPrice) && Validation.isMatchesRegexPrice(rentalPrice);
    }
    public static boolean isMatchesRegexPrice(TextField value){
        return value.getText().matches("^[0-9+]{0,3}$");

    }
    public static boolean isMatchesRegexNSP(TextField value){
        return value.getText().matches("^[А-ЯЇҐЄІ]('?[а-яїієґ]){1,30}$");
    }

    public static boolean isMatchesRegexAddress(TextField value){
        return value.getText().matches("^[А-ЯЇҐЄІ]('?[а-яїієґ0-9\\s]){6,50}$");
    }

    public static boolean isMatchesRegexPhone(TextField value){
        return value.getText().matches("^[0-9]+$");
    }

    public static void addPriceValidation(TextField textField) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                if (newValue.matches("^[1-9]\\d*$")) {
                    textField.setStyle("-fx-border-color: green;");
                } else {
                    textField.setStyle("-fx-border-color: red;");
                }
            } else {
                textField.setStyle("-fx-border-color: none;");
            }
        });
    }

    public static void addAddressValidation(TextField textField){
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
    public static void addPhoneNumberValidation(TextField textField) {
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
    public static void addUppercaseValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                String text = newValue;
                if (!text.matches("^[А-ЯЇҐЄІ]('?[а-яїієґ]){1,30}$")) {
                    textField.setStyle("-fx-border-color: red;");
                }
                else {
                    textField.setStyle("-fx-border-color: green;");
                    textField.setText(text);
                }
            } else {
                textField.setStyle("");
            }
        });
    }
}
