package com.example.btl_ltnc.controllers;

import com.example.btl_ltnc.MainApp;
import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.getData.DataDA;
import com.example.btl_ltnc.models.ChuongTrinhDaoTao;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.*;

public class ChuongTrinhDaoTaoController {
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
        private TextField msctdtField;
        @FXML
        private TextField tenChuongTrinhField;
        @FXML
        private TextField mshpField;
        @FXML
        private TextField tenHocPhanField;
        @FXML
        private Button addButton;
        @FXML
        private Button updateButton;
        @FXML
        private Button deleteButton;
        @FXML
        private TextField searchField;
        @FXML
        private Button searchButton;
        @FXML
        private Button loadAllButton;
        @FXML
        private Button btnBackToMenu;

        private Connection connection;
        private DBConnection conn = new DBConnection();


        public void initialize() {
            try {
                connection = conn.getConnection();
                //connection = DBConnection.getConnection();
                initTableView();
                loadData();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        private void initTableView() {
            msctdtColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao,Integer>("msctdt"));
            tenChuongTrinhColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao,String>("tenChuongTrinh"));
            mshpColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao,Integer>("mshp"));
            tenHocPhanColumn.setCellValueFactory(new PropertyValueFactory<ChuongTrinhDaoTao,String>("tenHocPhan"));

            tableView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    msctdtField.setText(Integer.toString(newValue.getMsctdt()));
                    tenChuongTrinhField.setText(newValue.getTenChuongTrinh());
                    mshpField.setText(Integer.toString(newValue.getMshp()));
                    tenHocPhanField.setText(newValue.getTenHocPhan());
                }
            });
        }

        private void loadData() {
            try {
                tableView.getItems().clear();
//                Statement statement = conn.getConnection().createStatement();
//                String query = "select* from btl.chuongtrinhdaotao";
//                ResultSet rs = statement.executeQuery(query);
//                ChuongTrinhDaoTao ct;
//                while (rs.next()) {
//                    ct = new ChuongTrinhDaoTao(rs.getInt("MSCTDT"),
//                            rs.getString("TenChuongTrinh"),rs.getInt(" MSHP"),
//                            rs.getString("TenHocPhan"));
//                    tableView.getItems().add(ct);

                String query = "SELECT * FROM ChuongTrinhDaoTao";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int msctdt = resultSet.getInt("MSCTDT");
                    String tenChuongTrinh = resultSet.getString("TenChuongTrinh");
                    int mshp = resultSet.getInt("MSHP");
                    String tenHocPhan = resultSet.getString("TenHocPhan");
                    ChuongTrinhDaoTao ct = new ChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
                    tableView.getItems().add(ct);
                }

                resultSet.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @FXML
        private void handleAddButton() {
            int msctdt = Integer.parseInt(msctdtField.getText());
            String tenChuongTrinh = tenChuongTrinhField.getText();
            int mshp = Integer.parseInt(mshpField.getText());
            String tenHocPhan = tenHocPhanField.getText();
            ChuongTrinhDaoTao ct = new ChuongTrinhDaoTao(msctdt,tenChuongTrinh,mshp,tenHocPhan);
            insertChuongTrinhDaoTao(msctdt,tenChuongTrinh,mshp,tenHocPhan);
            loadData();
            clearFields();
        }

        @FXML
        private void handleUpdateButton() {
            int msctdt = Integer.parseInt(msctdtField.getText());
            String tenChuongTrinh = tenChuongTrinhField.getText();
            int mshp = Integer.parseInt(mshpField.getText());
            String tenHocPhan = tenHocPhanField.getText();
            updateChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
            loadData();
            clearFields();
        }

        @FXML
        private void handleDeleteButton() {
            int msctdt = Integer.parseInt(msctdtField.getText());
            deleteChuongTrinhDaoTao(msctdt);
            loadData();
            clearFields();
        }

        @FXML
        private void handleSearchButton() {
            String keyword = searchField.getText();
            searchChuongTrinhDaoTao(keyword);
        }

        @FXML
        private void handleLoadAllButton() {
            loadData();
            clearFields();
        }

        private void insertChuongTrinhDaoTao(int msctdt, String tenChuongTrinh, int mshp, String tenHocPhan) {
            try {
                String query = "insert into chuongtrinhdaotao(MSCTDT,TenChuongTrinh,MSHP,TenHocPhan) values (?,?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, msctdt);
                preparedStatement.setString(2, tenChuongTrinh);
                preparedStatement.setInt(3, mshp);
                preparedStatement.setString(4, tenHocPhan);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                showAlert(Alert.AlertType.INFORMATION, "Thêm thành công", "Thêm dữ liệu thành công!");
            } catch (Exception e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể thêm dữ liệu: " + e.getMessage());
            }
        }

        private void updateChuongTrinhDaoTao(int msctdt, String tenChuongTrinh, int mshp, String tenHocPhan) {
            try {
                String query = "update chuongtrinhdaotao set TenChuongTrinh = ?, MSHP = ?, TenHocPhan = ? WHERE MSCTDT = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, tenChuongTrinh);
                preparedStatement.setInt(2, mshp);
                preparedStatement.setString(3, tenHocPhan);
                preparedStatement.setInt(4, msctdt);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                showAlert(Alert.AlertType.INFORMATION, "Cập nhật thành công", "Cập nhật dữ liệu thành công!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể cập nhật dữ liệu: " + e.getMessage());
            }
        }

        private void deleteChuongTrinhDaoTao(int msctdt) {
            try {
                String query = "delete from chuongtrinhdaotao where MSCTDT = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, msctdt);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                showAlert(Alert.AlertType.INFORMATION, "Xóa thành công", "Xóa dữ liệu thành công!");
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể xóa dữ liệu: " + e.getMessage());
            }
        }

        private void searchChuongTrinhDaoTao(String keyword) {
            try {
                tableView.getItems().clear();
                String query = "select * from chuongtrinhdaotao where TenChuongTrinh like ? or TenHocPhan like ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, "%" + keyword + "%");
                preparedStatement.setString(2, "%" + keyword + "%");
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int msctdt = resultSet.getInt("MSCTDT");
                    String tenChuongTrinh = resultSet.getString("TenChuongTrinh");
                    int mshp = resultSet.getInt("MSHP");
                    String tenHocPhan = resultSet.getString("TenHocPhan");
                    ChuongTrinhDaoTao ct = new ChuongTrinhDaoTao(msctdt, tenChuongTrinh, mshp, tenHocPhan);
                    tableView.getItems().add(ct);
                }

                preparedStatement.close();
            } catch (SQLException e) {
                showAlert(Alert.AlertType.ERROR, "Lỗi", "Không thể tìm kiếm dữ liệu: " + e.getMessage());
            }
        }
        @FXML
        private void backToMenu() {
            MainApp mainApp = new MainApp();
            mainApp.start2(new Stage());
            Stage stage = (Stage) btnBackToMenu.getScene().getWindow();
            stage.close();
        }
        private void clearFields() {
            msctdtField.clear();
           tenChuongTrinhField.clear();
           mshpField.clear();
           tenChuongTrinhField.clear();
        }

        private void showAlert(Alert.AlertType type, String title, String content) {
            Alert alert = new Alert(type);
            alert.setTitle(title);
            alert.setContentText(content);
            alert.showAndWait();
        }
    }

