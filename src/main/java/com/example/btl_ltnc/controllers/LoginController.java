package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    private DBConnection conn = new DBConnection();

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Establish database connection
        try {
            Connection connection = conn.getConnection();
            //Connection connection = DBConnection.getConnection();
            String query = "SELECT * FROM taikhoan WHERE Username = ? AND Password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String phanQuyen = resultSet.getString("PhanQuyen");
                // Check user's role and open appropriate view
                if ("SV".equals(phanQuyen)) {
                    //mainApp.SinhVienView();
                    mainApp.showSVView();
                } else if ("Admin".equals(phanQuyen)) {
                    mainApp.AdminView();
                } else if ("GV".equals(phanQuyen)) {
                    //mainApp.GiangVienView();
                    mainApp.showGVView();
                }
            } else {
                // Show an error message for invalid login
                showAlert("Đăng nhập không thành công", "Tên đăng nhập hoặc mật khẩu không đúng.");
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Lỗi", "Không thể kết nối đến cơ sở dữ liệu.");
        }
    }


    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
