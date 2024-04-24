package com.example.btl_ltnc.controllers;


import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.getData.DataDiem;
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
import java.util.Optional;
import java.util.ResourceBundle;

public class DiemController implements Initializable {
    private static Stage stage;

    @FXML
    private TextField fieldMSSV1;

    @FXML
    private TextField fieldHovaten1;
    @FXML
    private TextField fieldDiemQT;
    @FXML
    private TextField fieldDiemCK;

    @FXML
    private Button btnNew4;
    @FXML
    private Button btnSave4;
    @FXML
    private Button btnUpdate4;
    @FXML
    private Button btnDelete4;
    @FXML
    private TableView tableView4;
    @FXML
    private TableColumn<Grade,String> colmssv;
    @FXML
    private TableColumn<Grade,String>colhovaten;
    @FXML
    private TableColumn<Grade,Float>colDiemQT4;
    @FXML
    private TableColumn<Grade,Float>colDiemCK4;
    @FXML
    private TableColumn<Grade,Float>colDiemTong;
    @FXML
    private TextField fieldSearch4;
    @FXML
    private Student student;
    private Grade grade;

    @FXML
    private Button btnBackToMenu;
    @FXML
    private Button btnBackPage;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    Label label3;
    private MainApp mainApp;


    public void getItem1(){
        ObservableList<Student> data = tableView4.getItems();
        for(Student s:data){
            Grade g =new Grade(s.getMSSV(),s.getHovaten());
            DataDiem grade1 = new DataDiem();
            grade1.insertSQL(g);
        }
    }
    public void addGradeTab() {
        Dialog<ButtonType>dialog = new Dialog<>();
        dialog.setTitle("Add confirmation");
        dialog.setHeaderText("Bạn có muốn lưu không?");
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(getStage());
        Label label=new Label("MSSV:"+fieldMSSV1.getText()+"\n"+ "Họ và tên:"+
                fieldHovaten1.getText()+"\n"+ "Điểm quá trình:"+
                fieldDiemQT.getText()+"\n"+"Điểm cuối kì:"+
                fieldDiemCK.getText());
        dialog.getDialogPane().setContent(label);
        ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
        Optional<ButtonType> result = dialog.showAndWait();
        if(result.isPresent()&&result.get()==okButton){
            Grade grade = new Grade(Integer.parseInt(fieldMSSV1.getText()),
                    fieldHovaten1.getText(),Float.parseFloat(fieldDiemQT.getText()),Float.parseFloat(fieldDiemCK.getText()));
            DataDiem query = new DataDiem();
            query.addGrade(grade);
            showGrades();
        }
    }
    @FXML
    public void showGrades(){
        DataDiem query = new DataDiem();
        ObservableList<Grade> list = query.getGradeList();
        //PropertyValueFactory corresponds to the new StudentModel search fields
        //the table column í the one you annotate above
        colmssv.setCellValueFactory(new PropertyValueFactory<Grade,String>("MSSV"));
        colhovaten.setCellValueFactory(new PropertyValueFactory<Grade,String>("Hovaten"));
        colDiemQT4.setCellValueFactory(new PropertyValueFactory<Grade,Float>("diemQT"));
        colDiemCK4.setCellValueFactory(new PropertyValueFactory<Grade,Float>("diemCK"));
        colDiemTong.setCellValueFactory(new PropertyValueFactory<Grade,Float>("tongDiem"));
        tableView4.setItems(list);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getLopHocPhan();
        showGrades();
        fieldSearch4.textProperty().addListener((ObservableList,oldValue,newValue)->{
            filterData1(newValue,newValue,newValue,newValue);
        });

    }

    @FXML
    public void mouseClick(javafx.scene.input.MouseEvent mouseEvent) {
        try{
            Grade grade1 = (Grade)tableView4.getSelectionModel().getSelectedItem();
            grade1 = new Grade(grade1.getMSSV(),grade1.getHovaten(),grade1.getDiemQT(),grade1.getDiemQT());
            this.grade = grade1;
            fieldMSSV1.setText(Integer.toString(grade.getMSSV()));
            fieldHovaten1.setText(grade.getHovaten());
            fieldDiemQT.setText(Float.toString(grade.getDiemQT()));
            fieldDiemCK.setText(Float.toString(grade.getDiemCK()));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    public void updateGrade(){
        try{
            Dialog<ButtonType>dialog = new Dialog<>();
            dialog.setTitle("Update confirmation");
            dialog.setHeaderText("Bạn có muốn cập nhật không?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(getStage());
            Label label=new Label("MSSV:"+fieldMSSV1.getText()+"\n"+ "Họ và tên:"+fieldHovaten1.getText()+"\n"+ "Điểm quá trình:"+fieldDiemQT.getText()+"\n"+"Điểm cuối kì:"+fieldDiemCK.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent()&&result.get()==okButton){
                DataDiem query = new DataDiem();
                Grade grade = new Grade(this.grade.getMSSV(),fieldHovaten1.getText(),
                        Float.parseFloat(fieldDiemQT.getText()),Float.parseFloat(fieldDiemCK.getText()));
                query.updateGrade(grade);
                showGrades();
                clearFields();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void deleteGrade(){
        try{
            Dialog<ButtonType>dialog = new Dialog<>();
            dialog.setTitle("Delete confirmation");
            dialog.setHeaderText("Bạn có muốn xoá không?");
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(getStage());
            Label label=new Label("MSSV:"+fieldMSSV1.getText()+"\n"+ "Họ và tên:"+fieldHovaten1.getText()+"\n"+ "Điểm quá trình:"+fieldDiemQT.getText()+"\n"+"Điểm cuối kì:"+fieldDiemCK.getText());
            dialog.getDialogPane().setContent(label);
            ButtonType okButton = new ButtonType("OK",ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButton = new ButtonType("CANCEL",ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(okButton,cancelButton);
            Optional<ButtonType> result = dialog.showAndWait();
            if(result.isPresent()&&result.get()==okButton){
                DataDiem query = new DataDiem();
                Grade grade = new Grade(this.grade.getMSSV(),fieldHovaten1.getText(),
                        Float.parseFloat(fieldDiemQT.getText()),Float.parseFloat(fieldDiemCK.getText()));
                query.deleteGrade(grade);
                showGrades();
                clearFields();
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void clearFields(){
        fieldMSSV1.setText("");
        fieldHovaten1.setText("");
        fieldDiemQT.setText("");
        fieldDiemCK.setText("");

    }
    @FXML
    private void clickNewsGrade(){

        clearFields();

    }
    private void filterData1(String MSSV, String hovaten, String diemQT, String diemCK){
        ObservableList<Grade> filterDate1 = FXCollections.observableArrayList();
        DataDiem query = new DataDiem();
        ObservableList<Grade> list = query.getGradeList();
        for(Grade grade:list){
            if(grade.getHovaten().toLowerCase().contains(hovaten.toLowerCase())){
                filterDate1.add(grade);
            }else if(Integer.toString(grade.getMSSV()).toLowerCase().contains(MSSV.toLowerCase())){
                filterDate1.add(grade);
            }else if(Float.toString(grade.getDiemCK()).toLowerCase().contains(diemCK.toLowerCase())){
                filterDate1.add(grade);
            }else if(Float.toString(grade.getDiemQT()).toLowerCase().contains(diemQT.toLowerCase())){
                filterDate1.add(grade);
            }
        }
        tableView4.setItems(filterDate1);
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
//        //xoá dữ liệu trong bảng lophocphancon
//        DataTimeTable query = new DataTimeTable();
//        ObservableList<LopHocPhan> list = query.getDataLHP();
//        for (LopHocPhan l : list) {
//            query.deleteLHP(l);
//        }

        //chuyển về tab trươc
        MainApp mainApp = new MainApp();
        mainApp.start5(new Stage());
        Stage stage = (Stage) btnBackPage.getScene().getWindow();
        stage.close();
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
    /**
     * @return the stage
     */
    public static Stage getStage(){
        return stage;
    }
    /**
     * @param aStage the stage to set
     */
    public static void setStage(Stage aStage){
        stage = aStage;
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
}
