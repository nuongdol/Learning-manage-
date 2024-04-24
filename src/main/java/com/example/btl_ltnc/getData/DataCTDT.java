package com.example.btl_ltnc.getData;

import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.ChuongTrinhDaoTao;
import com.example.btl_ltnc.models.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataCTDT {
    private DBConnection c = new DBConnection();
    private Connection conn;
    public ObservableList<ChuongTrinhDaoTao> getCTDTList() {
        ObservableList<ChuongTrinhDaoTao> gradeList = FXCollections.observableArrayList();
        String query = "select* from btl.chuongtrinhdaotao";
        try {
            Statement statement = c.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            ChuongTrinhDaoTao g;
            while (rs.next()) {
                g = new ChuongTrinhDaoTao(rs.getInt("MSCTDT"),
                        rs.getString("TenChuongTrinh"),rs.getInt("MSHP"),
                        rs.getString("TenHocPhan"));
                gradeList.add(g);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gradeList;
    }
}
