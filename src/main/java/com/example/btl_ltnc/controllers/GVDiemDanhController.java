package com.example.btl_ltnc.controllers;


import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.getData.DataDiem;
import com.example.btl_ltnc.getData.DataDiemDanh;
import com.example.btl_ltnc.getData.DataTimeTable;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class GVDiemDanhController implements Initializable {
    private static Stage stage;
    @FXML
    private TextField fieldMSSV;
    @FXML
    private TextField fieldHovaten;
    @FXML
    private TextField fieldVang;
    @FXML
    private TextField fieldNgay;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Student> tableView;
    @FXML
    private TableColumn<Student, String> colMSSV;
    @FXML
    private TableColumn<Student, String> colHovaten;
    @FXML
    private TableColumn<Student, String> colVang;
    @FXML
    private TableColumn<Student, String> colNgay;
    @FXML
    private TextField fieldSearch;
    @FXML
    private Button btnnhapdiem;
    private Student student;
    private LopHocPhan lophocphancon;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    @FXML
    private Button btnBackToMenu;
    @FXML
    private Button btnBackPage;
    @FXML
    private Label label;
    private MainApp mainApp;


    @FXML
    public void addStudentTab() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Add confirmation");
        dialog.setHeaderText("Bạn có muốn lưu không?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label = new Label("MSSV:" + fieldMSSV.getText() + "\n" + "Họ và tên:"
                + fieldHovaten.getText() + "\n" + "Vắng:" + fieldVang.getText() + "\n"
                + "Ngày vắng:" + fieldNgay.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == okButton) {
            Student student = new Student(fieldMSSV.getText(), fieldHovaten.getText(), fieldVang.getText(), fieldNgay.getText());
            DataDiemDanh query = new DataDiemDanh();
            query.addStudent(student);
            showStudents();
            //getItem1();
        }
    }

    @FXML
    public void showStudents() {
        DataDiemDanh query = new DataDiemDanh();
        ObservableList<Student> list = query.getStudentList();
        colMSSV.setCellValueFactory(new PropertyValueFactory<Student, String>("MSSV"));
        colHovaten.setCellValueFactory(new PropertyValueFactory<Student, String>("Hovaten"));
        colVang.setCellValueFactory(new PropertyValueFactory<Student, String>("Vang"));
        colNgay.setCellValueFactory(new PropertyValueFactory<Student, String>("Ngay"));
        tableView.setItems(list);
        label.setText(Integer.toString(query.siSo));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLopHocPhan();
        showStudents();
        clearFields();
        fieldSearch.textProperty().addListener((ObservableList, oldValue, newValue) -> {
            filterData(newValue, newValue, newValue, newValue);
        });

    }

    @FXML
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) {
        try {
            //chọn mot o trong bảng tableview
            Student student1 = tableView.getSelectionModel().getSelectedItem();
            student1 = new Student(Integer.toString(student1.getMSSV()), student1.getHovaten(), student1.getVang(), student1.getNgay());
            this.student = student1;
            fieldMSSV.setText(Integer.toString(student.getMSSV()));
            fieldHovaten.setText(student.getHovaten());
            fieldVang.setText(student.getVang());
            fieldNgay.setText(student.getNgay());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateStudent() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Update confirmation");
            dialog.setHeaderText("Bạn có muốn cập nhật không?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(getStage());
            Label label = new Label("MSSV:" + fieldMSSV.getText() + "\n" + "Họ và tên:" + fieldHovaten.getText() + "\n" + "Vắng:" + fieldVang.getText() + "\n" + "Ngày:" + fieldNgay.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                DataDiemDanh query = new DataDiemDanh();
                Student student = new Student(Integer.toString(this.student.getMSSV()), fieldHovaten.getText(), fieldVang.getText(), fieldNgay.getText());
                query.updateStudents(student);
                showStudents();
                clearFields();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void deleteStudent() {
        try {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Delete confirmation");
            dialog.setHeaderText("Bạn có muốn xoá không?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(getStage());
            Label label = new Label("MSSV:" + fieldMSSV.getText() + "\n" + "Họ và tên:" + fieldHovaten.getText() + "\n" + "Vắng:" + fieldVang.getText() + "\n" + "Ngày:" + fieldNgay.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton, cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == okButton) {
                DataDiemDanh query = new DataDiemDanh();
                Student student = new Student(Integer.toString(this.student.getMSSV()), fieldHovaten.getText(), fieldVang.getText(), fieldNgay.getText());
                query.deleteStudents(student);
                showStudents();
                clearFields();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields() {
        fieldMSSV.setText("");
        fieldHovaten.setText("");
        fieldVang.setText("");
        fieldNgay.setText("");

    }

    @FXML
    private void clickNews() {
        clearFields();
    }

    @FXML
    private void filterData(String searchName, String MSSVSearch, String VangSearch, String NgaySearch) {
        ObservableList<Student> filterDate1 = FXCollections.observableArrayList();
        DataDiemDanh query = new DataDiemDanh();
        ObservableList<Student> list = query.getStudentList();
        for (Student student : list) {
            if (student.getHovaten().toLowerCase().contains(searchName.toLowerCase())) {
                filterDate1.add(student);
            } else if (Integer.toString(student.getMSSV()).toLowerCase().contains(MSSVSearch.toLowerCase())) {
                filterDate1.add(student);
            } else if (student.getVang().equals(VangSearch)) {
                filterDate1.add(student);
            } else if (student.getNgay().equals(NgaySearch)) {
                filterDate1.add(student);
            }
        }
        tableView.setItems(filterDate1);
    }

    public void getQLdiem() {
        getItem1();
        MainApp mainApp = new MainApp();
        mainApp.start6(new Stage());
        Stage stage = (Stage) btnnhapdiem.getScene().getWindow();
        stage.close();
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

    //luu du lieu vao bang sqldiem MSSV, ten
    public void getItem1() {
        ObservableList<Student> data = tableView.getItems();
        for (Student s : data) {
            Grade g = new Grade(s.getMSSV(), s.getHovaten());
            DataDiem grade1 = new DataDiem();
            grade1.insertSQL(g);
        }
    }
//lay mã lớp giảng dạy, môn học, mã học phần

    @FXML
    public void getLopHocPhan() {
        DataTimeTable query = new DataTimeTable();
        ObservableList<LopHocPhan> list = query.getDataLHP();
        String t ="";
        String h ="";
        String k ="";
        for (LopHocPhan l : list) {
            t=Integer.toString(l.getMaHocPhan());
            h=l.getTenMonHoc();
            k=Integer.toString(l.getMaLopGiangDay());
            label1.setText(t);
            label2.setText(h);
            label3.setText(k);

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

        //chuyển về tab trươc
        MainApp mainApp = new MainApp();
        mainApp.start3(new Stage());
        Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backToPage() {
        //xoá dữ liệu trong bảng lophocphancon
        DataTimeTable query = new DataTimeTable();
        ObservableList<LopHocPhan> list = query.getDataLHP();
        for (LopHocPhan l : list) {
            query.deleteLHP(l);
        }

        //chuyển về tab trươc
        MainApp mainApp = new MainApp();
        mainApp.start4(new Stage());
        Stage stage = (Stage) btnBackPage.getScene().getWindow();
        stage.close();
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}


