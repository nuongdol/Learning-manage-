package com.example.btl_ltnc.getData;

import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.DoAn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataDA {
    private DBConnection c = new DBConnection();
    private Connection conn;

    public void addDA(DoAn student) {
        try {
            conn = c.getConnection();
            String sql = "INSERT INTO doan (MaHocPhan,SoTinChi,TenHocPhan,DangKiDeTai,NguyenVongHuongDan,TrangThaiDangKy) VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getMaHocPhan());
            ps.setInt(2, student.getSoTinChi());
            ps.setString(3, student.getTenHocPhan());
            ps.setString(4, student.getDangKiDeTai());
            ps.setString(5, student.getNguyenVongHuongDan());
            ps.setString(6, student.getTrangThaiDangKy());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //hiển thị bảng sinh viên lên trên màn hình tab
    public ObservableList<DoAn> getDAList() {
        ObservableList<DoAn> studentList = FXCollections.observableArrayList();
        String query = "SELECT MaHocPhan,SoTinChi,TenHocPhan,DangKiDeTai,NguyenVongHuongDan,TrangThaiDangKy from btl.doan";
        try {
            Statement statement = c.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            DoAn s;
            while (rs.next()) {
                s = new DoAn(rs.getInt("MaHocPhan"),rs.getInt("SoTinChi"),
                        rs.getString("TenHocPhan"),
                        rs.getString("DangKiDeTai"), rs.getString("NguyenVongHuongDan"),
                        rs.getString("TrangThaiDangKy"));
                studentList.add(s);
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void updateDA(DoAn student) {
        try {
            PreparedStatement ps = c.getConnection().prepareStatement("update doan\n"
                    + "set SoTinChi=?,TenHocPhan=?,DangKiDeTai=?,NguyenVongHuongDan=?,TrangThaiDangKy=?where MaHocPhan = ?");
            ps.setInt(1, student.getSoTinChi());
            ps.setString(2, student.getTenHocPhan());
            ps.setString(3, student.getDangKiDeTai());
            ps.setString(4, student.getNguyenVongHuongDan());
            ps.setString(5, student.getTrangThaiDangKy());
            ps.setInt(6, student.getMaHocPhan());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void deleteDA(DoAn student){
        try{
            PreparedStatement ps = c.getConnection().prepareStatement("Delete from doan\n"
                    + " where MaHocPhan = ?");
            ps.setInt(1, student.getMaHocPhan());
            ps.executeUpdate();
            ps.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

//    public void insertSQL(Grade grade){
//        try {
//            String sql = "INSERT INTO diemmonhoc(MSSV, Tensinhvien) VALUES (?, ?)";
//            PreparedStatement statement = c.getConnection().prepareStatement(sql);
//            statement.setInt(1, grade.getMSSV());
//            statement.setString(2, grade.getHovaten());
//            // Gán các giá trị cho các cột trong câu lệnh SQL
//            statement.executeUpdate();
//            statement.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }


}


