module org.example.storeclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens org.example.storeclient.model to com.google.gson;

    exports org.example.storeclient;
    exports org.example.storeclient.model;
    opens org.example.storeclient to com.google.gson, javafx.fxml;
}