package org.example.aes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AESController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}