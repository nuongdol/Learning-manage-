package com.example.btl_ltnc.getData;

import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.LopHocPhan;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;


public class DataTimeTable {
    private DBConnection c = new DBConnection();
    private Connection conn;
    public ObservableList<LopHocPhan> getTableTimeList(){
        ObservableList<LopHocPhan> tableTimeList = FXCollections.observableArrayList();
        String query = "select * from lophocphan";
        try{
            Statement statement = c.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            LopHocPhan t;
            while (rs.next()){
                t = new LopHocPhan(rs.getInt("MaLopGiangDay"),
                        rs.getTimestamp("ThoiGianBatDau").toLocalDateTime(),
                        rs.getTimestamp("ThoiGianBatDau").toLocalDateTime(),
                        rs.getString("PhongHoc"), rs.getString("TenMonHoc"),
                        rs.getInt("MaHocPhan"),rs.getInt("SoLuongSinhVien"),
                        rs.getInt("MSGV"),rs.getString("TenGiangVien"));
                tableTimeList.add(t);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return tableTimeList;
    }
    //insert vào bảng con của lop hoc phan
    public void insertSQL1(LopHocPhan grade){
        try {
            String sql = "INSERT INTO LopHocPhanCon (MaLopGiangDay,TenMonHoc,MaHocPhan) VALUES (?, ?,?)";
            PreparedStatement statement = c.getConnection().prepareStatement(sql);
            statement.setInt(1, grade.getMaLopGiangDay());
            statement.setString(2, grade.getTenMonHoc());
            statement.setInt(3,grade.getMaHocPhan());
            // Gán các giá trị cho các cột trong câu lệnh SQL
            statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //ham lay data
    public ObservableList<LopHocPhan> getDataLHP(){
        ObservableList<LopHocPhan> tableTimeList = FXCollections.observableArrayList();
        String query = "select MaLopGiangDay, TenMonHoc, MaHocPhan from lophocphancon ";
        try {
            conn = c.getConnection();
            Statement statement = c.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            LopHocPhan s;
            while (rs.next()) {
                s = new LopHocPhan(rs.getInt("MaLopGiangDay"), rs.getString("TenMonHoc"),
                        rs.getInt("MaHocPhan"));
                tableTimeList.add(s);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return tableTimeList;
    }


    //xoá du lieu ở bảng lophocphancon luon
    public void deleteLHP(LopHocPhan lopHocPhan){
        try{
            PreparedStatement ps = c.getConnection().prepareStatement("Delete from lophocphancon\n"
                    + " where MaLopGiangDay =?");
            ps.setInt(1, lopHocPhan.getMaLopGiangDay());
            ps.executeUpdate();
            ps.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
