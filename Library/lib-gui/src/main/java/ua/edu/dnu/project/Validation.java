package ua.edu.dnu.project;

import javafx.scene.control.TextField;

public class Validation {

    public static boolean areAllValidatedFieldsUser(TextField name, TextField surname, TextField patronymic, TextField address, TextField phone) {
        return Validation.isMatchesRegexNSP(name) && Validation.isMatchesRegexNSP(surname) && Validation.isMatchesRegexNSP(patronymic) && Validation.isMatchesRegexAddress(address) && Validation.isMatchesRegexPhone(phone);
    }

    public static boolean areAllValidatedFieldsBook(TextField title, TextField author, TextField genre, TextField bailPrice, TextField rentalPrice) {
        return Validation.isMatchesRegexTA(title) && Validation.isMatchesRegexTA(author) && Validation.isMatchesRegexNSP(genre) && Validation.isMatchesRegexPrice(bailPrice) && Validation.isMatchesRegexPrice(rentalPrice);
    }

    public static boolean isMatchesRegexPrice(TextField value) {
        return value.getText().matches("^[0-9+]{0,5}$");
    }

    public static boolean isMatchesRegexTA(TextField value) {
        return value.getText().matches("^[А-ЯЇҐЄІа-яїієґ ]{3,30}$");
    }

    public static boolean isMatchesRegexNSP(TextField value) {
        return value.getText().matches("^[А-ЯЇҐЄІ]('?[а-яїієґ]){1,30}$");
    }

    public static boolean isMatchesRegexAddress(TextField value) {
        return value.getText().matches("^[А-ЯЇҐЄІ]('?[а-яїієґ0-9\\s]){6,50}$");
    }

    public static boolean isMatchesRegexPhone(TextField value) {
        return value.getText().matches("^[0-9]{10}$");
    }

    public static boolean isMatchesRegexPhone(String value) {
        return value.matches("^[0-9]+$");
    }

    public static void addPriceValidation(TextField textField) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
            if (newValue != null && !newValue.isEmpty()) {
                if (newValue.matches("^[0-9+]{0,5}$")) {
                    textField.setStyle("-fx-border-color: green;");
                } else {
                    textField.setStyle("-fx-border-color: red;");
                }
            } else {
                textField.setStyle("-fx-border-color: none;");
            }
        });
    }

    public static void addAddressValidation(TextField textField) {
        textField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (!newValue.matches(("^[А-ЯЇҐЄІ]('?[а-яїієґ0-9\\s]){6,50}$"))) {
                    textField.setStyle("-fx-border-color: red;");
                } else {
                    textField.setStyle("-fx-border-color: green;");
                    textField.setText(newValue);
                }
            } else {
                textField.setStyle("-fx-border-color: none");
            }
        }));
    }

    public static void addPhoneNumberValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (!newValue.matches("^[0-9]+$") || newValue.length() != 10 || newValue.contains(" ")) {
                    textField.setStyle("-fx-border-color: red;");
                } else {
                    textField.setStyle("-fx-border-color: green;");
                    textField.setText(newValue);
                }
            } else {
                textField.setStyle("-fx-border-color: none");
            }
        });
    }

    public static void addUppercaseValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.isEmpty()) {
                if (!newValue.matches("^[А-ЯЇҐЄІ]('?[а-яїієґ]){1,30}$")) {
                    textField.setStyle("-fx-border-color: red;");
                } else {
                    textField.setStyle("-fx-border-color: green;");
                    textField.setText(newValue);
                }
            } else {
                textField.setStyle("");
            }
        });
    }

    public static void addTitleAndAuthorValidation(TextField textField) {
        textField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                textField.setStyle("");
            } else if (!newValue.matches("^[А-ЯЇҐЄІа-яїієґ ]{3,30}$") || newValue.endsWith(" ")) {
                textField.setStyle("-fx-border-color: red;");
            } else {
                textField.setStyle("-fx-border-color: green;");
            }
        });
    }
}
