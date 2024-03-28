module org.example.aes {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.aes to javafx.fxml;
    exports org.example.aes;
}