module com.example.fincance_app {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.example.fincance_app to javafx.fxml;
    exports com.example.fincance_app;
}