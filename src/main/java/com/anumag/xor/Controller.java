package com.anumag.xor;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {
    @FXML
    public TextField number_T0;
    @FXML
    public TextField number_A;
    @FXML
    public TextField A;
    @FXML
    public TextField number_C;
    @FXML
    public TextField encryptedText;
    @FXML
    public TextField generatedGamma;
    @FXML
    public Button helpButton;
    @FXML
    public TextField textDecimal;
    @FXML
    public TextField encryptedTextDecimal;
    @FXML
    public Button checkA;
    @FXML
    public Button checkAButton;
    @FXML
    public Text result;
    @FXML
    TextField text;

    @FXML
    Button encryptButton;

    @FXML
    public void encrypt() {
        if (!text.getText().equals("") && !number_T0.getText().equals("") && !number_A.getText().equals("") && !number_C.getText().equals("")) {
            Cipher xor = new Cipher(text.getText(), Integer.parseInt(number_T0.getText()), Integer.parseInt(number_A.getText()), Integer.parseInt(number_C.getText()));
            generatedGamma.setText(xor.generatedGamma());
            textDecimal.setText(xor.getLettersPosition());
            encryptedTextDecimal.setText(xor.getEncryptedDecimal());
            encryptedText.setText(xor.getEncryptedText());
        } else {
            empty();
        }
    }

    public void empty() {
        Alert empty = new Alert(Alert.AlertType.INFORMATION);
        empty.setTitle("Увага!");
        empty.setHeaderText(null);
        empty.setContentText("Поля мають бути заповненими!");
        empty.showAndWait();
    }

    @FXML
    public void help() {
        Alert help = new Alert(Alert.AlertType.INFORMATION);
        help.setTitle("HELP");
        help.setHeaderText(null);
        help.setContentText("Умови для чисел, що вводяться:\nT0 – початкова величина, вибрана в якості початкового числа." +
                "\nA mod 4 = 1;" +
                "\nC – непарне;");
        help.showAndWait();
    }

    @FXML
    public void checkALauncher() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("checkAview.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Parameter check");
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void checkAMethod() {
        int number;
        if (!A.getText().equals("")) {
            number = Integer.parseInt(A.getText());
            if (number % 4 == 1) {
                result.setText("Число підходить!");
            } else {
                result.setText("Оберіть інше число, щоб виконувалась умова A mod 4 = 1");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Увага!");
            alert.setHeaderText(null);
            alert.setContentText("Поле має бути заповненим!");
            alert.showAndWait();
        }
    }
}