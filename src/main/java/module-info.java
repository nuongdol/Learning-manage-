module com.example.btl_ltnc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    exports com.example.btl_ltnc.controllers;
    opens com.example.btl_ltnc.controllers to javafx.fxml;


    opens com.example.btl_ltnc to javafx.fxml;
    exports com.example.btl_ltnc;


    // Mở gói com.example.btl_ltnc.models cho module javafx.base
    opens com.example.btl_ltnc.models to javafx.base;

}