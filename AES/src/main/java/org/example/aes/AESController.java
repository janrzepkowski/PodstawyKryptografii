package org.example.aes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class AESController implements Initializable {
    private final FileChooser fileChooser = new FileChooser();
    private int keyLength = 0;

    private AES aes = new AES();

    @FXML
    private TextArea keyInput;

    @FXML
    private TextArea unecryptedText;

    @FXML
    private TextArea ecryptedText;

    @FXML
    private ChoiceBox<Integer> keyLengthChoice;


    private final Integer[] keyLengths = {128, 192, 256};

    private byte[] fileContent;
    boolean isFileloaded = false;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        keyLengthChoice.getItems().addAll(keyLengths);
        keyLengthChoice.setOnAction(this::getKeyLength);
        ecryptedText.setEditable(false);
    }

    private void getKeyLength(javafx.event.ActionEvent actionEvent) {
        keyLength = keyLengthChoice.getValue();
    }

    @FXML
    protected void keyGenerator() throws NoSuchAlgorithmException {
        if (this.keyLength == 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Dlugość klucza nie została wybrana");
            alert.show();
        }
        aes = new AES();
        byte[] key = aes.generateKey(keyLength);
        String keyString = aes.byteArraytoString(key);
        keyInput.setText(keyString);
    }


    @FXML
    protected void loadFile(ActionEvent event) throws IOException {
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null) {
            String path = file.getPath();
            fileContent = Files.readAllBytes(Paths.get(path));
            isFileloaded = true;
        }
    }
}