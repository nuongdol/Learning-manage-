package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.getData.DataDA;
import com.example.btl_ltnc.getData.DataDiem;
import com.example.btl_ltnc.getData.DataDiemDanh;
import com.example.btl_ltnc.getData.DataTimeTable;
import com.example.btl_ltnc.models.DoAn;
import com.example.btl_ltnc.models.Grade;
import com.example.btl_ltnc.models.LopHocPhan;
import com.example.btl_ltnc.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class DoAnController implements Initializable {

    @FXML
    private TableView<DoAn> tableView;

    @FXML
    private TableColumn<DoAn, Integer> maHocPhanColumn;

    @FXML
    private TableColumn<DoAn, Integer> soTinChiColumn;

    @FXML
    private TableColumn<DoAn, String> tenHocPhanColumn;

    @FXML
    private TableColumn<DoAn, String> dangKiDeTaiColumn;

    @FXML
    private TableColumn<DoAn, String> nguyenVongHuongDanColumn;

    @FXML
    private TableColumn<DoAn, String> trangThaiDangKyColumn;
    @FXML
    private TextField maHocPhanField;
    @FXML
    private TextField soTinChiField;
    @FXML
    private TextField tenHocPhanField;
    @FXML
    private TextField dangKiDeTaiField;
    @FXML
    private TextField nguyenVongHuongDanField;
    @FXML
    private TextField trangThaiDangKyField;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnBackToMenu;
    private DoAn doAn;
    @FXML
    private TextField fieldSearch;

    @FXML
    private static Stage stage;
    private MainApp mainApp;

    @FXML
    public void addDATab() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add confirmation");
        dialog.setHeaderText("Bạn có muốn lưu không?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label("Mã học phần:" + maHocPhanField.getText() + "\n" + "So tín chỉ:" + soTinChiField.getText() + "\n" +
                "Tên học phần:" + tenHocPhanField.getText() + "\n" + "Đăng kí đề tài:" + dangKiDeTaiField.getText() + "\n" +
                "Nguyen vọng dang kí:" + nguyenVongHuongDanField.getText() + "\n" + "Trạng thái dang kí:" + trangThaiDangKyField.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            DoAn student = new DoAn(Integer.parseInt(maHocPhanField.getText()),
                    Integer.parseInt(soTinChiField.getText()), tenHocPhanField.getText(),
                    dangKiDeTaiField.getText(),
                    nguyenVongHuongDanField.getText(), trangThaiDangKyField.getText());
            DataDA query = new DataDA();
            query.addDA(student);
            showDoAn();
        }
    }

    @FXML
    public void showDoAn() {
        DataDA query = new DataDA();
        ObservableList<DoAn> list = query.getDAList();
        maHocPhanColumn.setCellValueFactory(new PropertyValueFactory<DoAn, Integer>("MaHocPhan"));
        soTinChiColumn.setCellValueFactory(new PropertyValueFactory<DoAn, Integer>("SoTinChi"));
        tenHocPhanColumn.setCellValueFactory(new PropertyValueFactory<DoAn, String>("TenHocPhan"));
        dangKiDeTaiColumn.setCellValueFactory(new PropertyValueFactory<DoAn, String>("DangKiDeTai"));
        nguyenVongHuongDanColumn.setCellValueFactory(new PropertyValueFactory<DoAn, String>("NguyenVongHuongDan"));
        trangThaiDangKyColumn.setCellValueFactory(new PropertyValueFactory<DoAn, String>("TrangThaiDangKy"));
        tableView.setItems(list);
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showDoAn();
        clearFields();
        showDoAn();
        fieldSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue, newValue, newValue, newValue,newValue,newValue);
        });

    }

    @FXML
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            //chọn mot o trong bảng tableview
            DoAn student1 = tableView.getSelectionModel().getSelectedItem();
            student1 = new DoAn(student1.getMaHocPhan(),student1.getSoTinChi(),
                    student1.getTenHocPhan(),
                    student1.getDangKiDeTai(),
                    student1.getNguyenVongHuongDan(),
                    student1.getTrangThaiDangKy());
            this.doAn = student1;
            maHocPhanField.setText(Integer.toString(doAn.getMaHocPhan()));
            soTinChiField.setText(Integer.toString(doAn.getSoTinChi()));
            tenHocPhanField.setText(doAn.getTenHocPhan());
            trangThaiDangKyField.setText(doAn.getTrangThaiDangKy());
            dangKiDeTaiField.setText(doAn.getDangKiDeTai());
            nguyenVongHuongDanField.setText(doAn.getNguyenVongHuongDan());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateDA() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update confirmation");
            dialog.setHeaderText("Bạn có muốn cập nhật không?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(getStage());
            Label label = new Label("Mã học phần:" + maHocPhanField.getText() + "\n" + "So tín chỉ:" + soTinChiField.getText() + "\n" +
                    "Tên học phần:" + tenHocPhanField.getText() + "\n" + "Đăng kí đề tài:" + dangKiDeTaiField.getText()+"\n" +
                    "Nguyen vọng dang kí:" + nguyenVongHuongDanField.getText()+"\n" + "Trạng thái dang kí:" + trangThaiDangKyField.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                DataDA query = new DataDA();
                DoAn student = new DoAn(Integer.parseInt(maHocPhanField.getText()),
                        Integer.parseInt(soTinChiField.getText()),tenHocPhanField.getText(),
                        dangKiDeTaiField.getText(),
                        nguyenVongHuongDanField.getText(),trangThaiDangKyField.getText());
                query.updateDA(student);
                showDoAn();
                clearFields();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteDA() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Delete confirmation");
            dialog.setHeaderText("Bạn có muốn xoá không?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(getStage());
            Label label = new Label("Mã học phần:" + maHocPhanField.getText() + "\n" + "So tín chỉ:" + soTinChiField.getText() + "\n" +
                    "Tên học phần:" + tenHocPhanField.getText() + "\n" + "Đăng kí đề tài:" + dangKiDeTaiField.getText()+"\n" +
                    "Nguyen vọng dang kí:" + nguyenVongHuongDanField.getText()+"\n" + "Trạng thái dang kí:" + trangThaiDangKyField.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                DataDA query = new DataDA();
                DoAn student = new DoAn(Integer.parseInt(maHocPhanField.getText()),
                        Integer.parseInt(soTinChiField.getText()),tenHocPhanField.getText(),
                        dangKiDeTaiField.getText(),
                        nguyenVongHuongDanField.getText(),trangThaiDangKyField.getText());
                query.deleteDA(student);
                showDoAn();
                clearFields();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        dangKiDeTaiField.setText("");
        trangThaiDangKyField.setText("");
        nguyenVongHuongDanField.setText("");
        maHocPhanField.setText("");
        tenHocPhanField.setText("");
        soTinChiField.setText("");

    }

    @FXML
    private void clickNews() {

        clearFields();

    }

    @FXML
    private void filterData(String maHocPhan, String soTinChi, String tenHocPhan, String dangKiDeTai, String nguyenVongHuongDan, String trangThaiDangKy) {
        ObservableList<DoAn> filterDate1 = FXCollections.observableArrayList();
        DataDA query = new DataDA();
        ObservableList<DoAn> list = query.getDAList();
        for (DoAn student : list) {
            if (student.getTrangThaiDangKy().toLowerCase().contains(trangThaiDangKy.toLowerCase())) {
                filterDate1.add(student);
            } else if (Integer.toString(student.getMaHocPhan()).toLowerCase().contains(maHocPhan.toLowerCase())) {
                filterDate1.add(student);
            } else if (Integer.toString(student.getSoTinChi()).toLowerCase().contains(soTinChi.toLowerCase())) {
                filterDate1.add(student);
            } else if (student.getDangKiDeTai().toLowerCase().contains(dangKiDeTai.toLowerCase())) {
                filterDate1.add(student);
            } else if (student.getNguyenVongHuongDan().toLowerCase().contains(nguyenVongHuongDan.toLowerCase())) {
                filterDate1.add(student);
            } else if (student.getTenHocPhan().toLowerCase().contains(tenHocPhan.toLowerCase())) {
                filterDate1.add(student);
            }
            tableView.setItems(filterDate1);
        }
    }
    /**
     * @return the stage
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * @param aStage the stage to set
     */
    public static void setStage(Stage aStage) {
        stage = aStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    @FXML
    private void backToMenu() {
        //chuyển về tab trươc
        MainApp mainApp = new MainApp();
        mainApp.start3(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }

    // Các phương thức xử lý thêm, sửa, xóa...
}
