package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.getData.DataCTDT;
import com.example.btl_ltnc.models.ChuongTrinhDaoTao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class GVChuongTrinhDaoTaoController implements Initializable {
    @FXML
    private TableView<ChuongTrinhDaoTao> tableView;
    @FXML
    private TableColumn<ChuongTrinhDaoTao, Integer> msctdtColumn;
    @FXML
    private TableColumn<ChuongTrinhDaoTao, String> tenChuongTrinhColumn;
    @FXML
    private TableColumn<ChuongTrinhDaoTao, Integer> mshpColumn;
    @FXML
    private TableColumn<ChuongTrinhDaoTao, String> tenHocPhanColumn;

    @FXML
    private Button btnBackToMenu;
    @FXML
    private TextField searchField;
    @FXML
    public void showCTDT() {
        DataCTDT query = new DataCTDT();
        ObservableList<ChuongTrinhDaoTao> list = query.getCTDTList();
        msctdtColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao, Integer>("msctdt"));
        tenChuongTrinhColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao, String>("tenChuongTrinh"));
        mshpColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao,Integer>("mshp"));
        tenHocPhanColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao, String>("tenHocPhan"));
        tableView.setItems(list);
    }
    @FXML
    private void filterData(String msctdt, String tenChuongTrinh, String mshp, String tenHocPhan) {
        ObservableList<ChuongTrinhDaoTao> filterDate1 = FXCollections.observableArrayList();
        DataCTDT query = new DataCTDT();
        ObservableList<ChuongTrinhDaoTao> list = query.getCTDTList();
        for (ChuongTrinhDaoTao student : list) {
            if (student.getTenChuongTrinh().toLowerCase().contains(tenChuongTrinh.toLowerCase())) {
                filterDate1.add(student);
            } else if (student.getTenHocPhan().toLowerCase().contains(tenHocPhan.toLowerCase())) {
                filterDate1.add(student);
            } else if (Integer.toString(student.getMsctdt()).equals(msctdt.toLowerCase())) {
                filterDate1.add(student);
            } else if (Integer.toString(student.getMshp()).equals(mshp.toLowerCase())) {
                filterDate1.add(student);
            }
        }
        tableView.setItems(filterDate1);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCTDT();
        searchField.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue, newValue, newValue, newValue);
        });

    }
    @FXML
    private void backToMenu() {
        //chuyển về tab trươc
        MainApp mainApp = new MainApp();
        mainApp.start3(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }
}
