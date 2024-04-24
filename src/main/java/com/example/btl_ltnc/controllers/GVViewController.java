package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GVViewController {

    private MainApp mainApp;
    private Button btnBackToMenu;


    @FXML
    private AnchorPane rootLayout;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @FXML
    private void handleDoAnView(ActionEvent event) {
        mainApp.openDoAnView();
    }
    @FXML
    private void handleDiemView(ActionEvent event){mainApp.openDiemView();}
    @FXML
    private void handleDiemdanhView(ActionEvent event){mainApp.openDiemdanhView();}
    @FXML
    private void handleTGBGVView(ActionEvent event){mainApp.openTGBGV();}
    @FXML
    private void handleGVChuongTrinhDaoTao(ActionEvent event){mainApp.openGVChuongTrinhDaoTao();}

    @FXML
    private void backToMenu() {
        mainApp.showLoginView();
    }

}
