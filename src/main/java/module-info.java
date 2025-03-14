module com.example.miniproyecto1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.example.miniproyecto1.controllers to javafx.fxml;
    opens com.example.miniproyecto1.models to javafx.fxml;

    exports com.example.miniproyecto1;
    exports com.example.miniproyecto1.controllers;
    exports com.example.miniproyecto1.models;
}
