package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.getData.DataTimeTable;
import com.example.btl_ltnc.models.LopHocPhan;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class GVTGianBieuController implements Initializable {
    @FXML
    private TextField fieldSearch;
    @FXML
    private TextField fieldKy;
    @FXML
    private TableView<LopHocPhan> tableView;
    @FXML
    private TableColumn<LopHocPhan, Integer> colMLGD;
    @FXML
    private TableColumn<LopHocPhan, LocalDateTime> colTGbd;
    @FXML
    private TableColumn<LopHocPhan, LocalDateTime> colTGkt;
    @FXML
    private TableColumn<LopHocPhan, Integer> colmhp;
    @FXML
    private TableColumn<LopHocPhan, String> colTenmonhoc;
    @FXML
    private TableColumn<LopHocPhan, Integer> colSL;
    @FXML
    private TableColumn<LopHocPhan, Integer> colMGV;
    @FXML
    private TableColumn<LopHocPhan, String> colTenGV;
    @FXML
    private TableColumn<LopHocPhan, String> colph;
    @FXML
    private Button btnBackToMenu;
    @FXML
    private Button btndiemdanh;
    @FXML
    private Label label;
    @FXML
    private ComboBox comboBoxKi;


    private MainApp mainApp;

//thuc hien chay toan chương trình
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTableTimes();
        fieldSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue, newValue,newValue, newValue, newValue, newValue,
                    newValue, newValue, newValue);
        });
        setComboBoxKi();
    }
    //show bảng
    @FXML
    public void showTableTimes() {
        DataTimeTable query = new DataTimeTable();
        //kiểu list lưu tru bang
        ObservableList<LopHocPhan> list = query.getTableTimeList();
        colMLGD.setCellValueFactory(new PropertyValueFactory<LopHocPhan, Integer>("maLopGiangDay"));
        colmhp.setCellValueFactory(new PropertyValueFactory<LopHocPhan, Integer>("maHocPhan"));
        colSL.setCellValueFactory(new PropertyValueFactory<LopHocPhan, Integer>("soLuongSinhVien"));
        colTenmonhoc.setCellValueFactory(new PropertyValueFactory<LopHocPhan, String>("tenMonHoc"));
        colTGbd.setCellValueFactory(new PropertyValueFactory<LopHocPhan, LocalDateTime>("thoiGianBatDau"));
        colMGV.setCellValueFactory(new PropertyValueFactory<LopHocPhan, Integer>("msgv"));
        colTenGV.setCellValueFactory(new PropertyValueFactory<LopHocPhan, String>("tenGiangVien"));
        colTGkt.setCellValueFactory(new PropertyValueFactory<LopHocPhan, LocalDateTime>("thoiGianKetThuc"));
        colph.setCellValueFactory(new PropertyValueFactory<LopHocPhan, String>("phongHoc"));
        tableView.setItems(list);
    }
    //tìm kiém bảng
    @FXML
    private void filterData(String maLopGiangDay, String thoiGianBatDau, String thoiGianKetThuc,
                            String phongHoc, String tenMonHoc, String maHocPhan, String soLuongSinhVien,
                            String msgv, String tenGiangVien) {
        ObservableList<LopHocPhan> filterDate1 = FXCollections.observableArrayList();
        DataTimeTable query = new DataTimeTable();
        ObservableList<LopHocPhan> list = query.getTableTimeList();
        for (LopHocPhan l : list) {
            if (l.getPhongHoc().toLowerCase().contains(phongHoc.toLowerCase())) {
                filterDate1.add(l);
            } else if (l.getThoiGianBatDau().toString().toLowerCase().contains(thoiGianBatDau)){
                filterDate1.add(l);
            } else if (l.getThoiGianKetThuc().toString().toLowerCase().contains(thoiGianKetThuc)) {
                filterDate1.add(l);
            } else if (l.getTenGiangVien().toLowerCase().contains(tenGiangVien.toLowerCase())) {
                filterDate1.add(l);
            } else if (l.getTenMonHoc().toLowerCase().contains(tenMonHoc.toLowerCase())) {
                filterDate1.add(l);
            } else if (Integer.toString(l.getMsgv()).contains(msgv)) {
                filterDate1.add(l);
            } else if (Integer.toString(l.getMaHocPhan()).contains(maHocPhan)) {
                filterDate1.add(l);
            } else if (Integer.toString(l.getMaLopGiangDay()).contains(maLopGiangDay)) {
                filterDate1.add(l);
            } else if (Integer.toString(l.getSoLuongSinhVien()).contains( soLuongSinhVien)) {
                filterDate1.add(l);
            }
        }
        tableView.setItems(filterDate1);
    }
//click chuột lấy du lieu
    @FXML
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            //thực hiện lấy giá trị cho vào bảng
             //getQLlop();
              getItem1();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //lấy giá trị của bảng gán vào giá trị của bảng du lieu trong mysql
    @FXML
    public void getItem1() {
        LopHocPhan lopHocPhan1 = tableView.getSelectionModel().getSelectedItem();
        lopHocPhan1 = new LopHocPhan(lopHocPhan1.getMaLopGiangDay(), lopHocPhan1.getTenMonHoc(), lopHocPhan1.getMaHocPhan());
        //lay mã lớp giảng dạy
        label.setText(Integer.toString(lopHocPhan1.getMaLopGiangDay()));
            DataTimeTable t = new DataTimeTable();
            t.insertSQL1(lopHocPhan1);


            //getQLlop();

    }

    //chuyển trang
    public void getQLlop () {

        //thuc hien nhay sang trang diem danh
        if(label.getText()!= "Malopgiangday"){
            //chuyển về tab trước
            MainApp mainApp = new MainApp();
            mainApp.start5(new Stage());
            Stage stage = (Stage) btndiemdanh.getScene().getWindow();
            stage.close();
        }

        }
    @FXML
    private void backToMenu() {
        //xoá dữ liệu trong bảng lophocphancon
        DataTimeTable query = new DataTimeTable();
        ObservableList<LopHocPhan> list = query.getDataLHP();
        for (LopHocPhan l : list) {
            query.deleteLHP(l);
        }

        //chuyển về tab trước
        MainApp mainApp = new MainApp();
        mainApp.start3(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    // học kỳ
    public void setComboBoxKi(){
        String[] items= {"2021.2","2021.3","2022.1","2022.2","2022.3"};
        comboBoxKi.getItems().addAll(items);
    }

}

