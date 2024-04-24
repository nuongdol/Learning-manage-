package com.example.btl_ltnc;

import com.example.btl_ltnc.controllers.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
         showLoginView();
         //AdminView();
         //showGVView();

    }
    public void start2(Stage primaryStage) {
        this.primaryStage = primaryStage;
        AdminView();
    }
    public void start3(Stage primaryStage) {
        this.primaryStage = primaryStage;
        GVView();
    }
    public void start4(Stage primaryStage){
        this.primaryStage = primaryStage;
        TGBGV();
    }
    public void start5(Stage primaryStage){
        this.primaryStage = primaryStage;
        Diemdanh();
    }
    public void start6(Stage primaryStage){
        this.primaryStage = primaryStage;
        Diem();
    }
    public void showLoginView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Đăng nhập");
            primaryStage.show();

            // Gán controller cho LoginView.fxml
            LoginController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMainMenuView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MainApp.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            MainMenuViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showGVView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("GVView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            GVViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSVView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SVView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            SVViewController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showDiemdanhView(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DiemdanhView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            GVDiemDanhController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showTGBGVView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ThoikhoabieuGVView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            GVTGianBieuController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showDiemView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DiemView.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý Chức Năng");
            primaryStage.show();

            // Gán controller cho MainMenuView.fxml
            DiemController controller = loader.getController();
            controller.setMainApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  void AdminView() {
        showMainMenuView();
    }
    public  void GVView() {
        showGVView();
    }
    public void TGBGV(){showTGBGVView();}
    public void Diemdanh(){showDiemdanhView();}
    public void Diem(){showDiemView();}
    public  void SVView() {
        showSVView();
    }

    // Các phương thức mở giao diện chức năng tương ứng
    public void openHocPhanView() {
        loadView("HocPhanView.fxml");
    }

    public void openNopHocPhiView() {
        loadView("NopHocPhiView.fxml");
    }

    public void openGiangVienView() {
        loadView("GiangVienView.fxml");
    }
    public void openSinhVienView() {
        loadView("SinhVienView.fxml");
    }
    public void openKyHocView() {
        loadView("KyHocView.fxml");
    }
    public void openChuongTrinhDaoTaoView() {
        loadView("ChuongTrinhDaoTaoView.fxml");
    }
    public void openLopView() {
        loadView("LopView.fxml");
    }
    public void openLopHocPhanView() {
        loadView("LopHocPhanView.fxml");
    }
    public void openTaiKhoanView() {
        loadView("TaiKhoanView.fxml");
    }
    public void openKhoaVienView() {
        loadView("KhoaVienView.fxml");
    }
    public void openPhongHocView() {
        loadView("PhongHocView.fxml");
    }
    public void openDoAnView() {loadView("DoAnView.fxml");
    }
    public void openDiemdanhView(){loadView("DiemdanhView.fxml");}
    public void openDiemView(){loadView("DiemView.fxml");}
    public void openTGBGV(){loadView("ThoikhoabieuGVView.fxml");}
    public void openGVChuongTrinhDaoTao(){loadView("GVChuongTrinhDaoTaoView.fxml");}

    public void loadView(String viewFileName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFileName));
            Parent view = loader.load();
            Scene scene = new Scene(view);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
