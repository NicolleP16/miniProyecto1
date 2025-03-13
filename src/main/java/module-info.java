module com.example.miniproyecto1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.miniproyecto1 to javafx.fxml;
    exports com.example.miniproyecto1;
}