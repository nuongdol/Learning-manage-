package com.example.btl_ltnc.getData;

import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.Lop;
import com.example.btl_ltnc.models.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataDiemDanh {
    private DBConnection c = new DBConnection();
    private Connection conn;
    public int siSo = 0;

    public void addStudent(Student student) {
        try {
            conn = c.getConnection();
            String sql = "INSERT INTO sinhvien (MSSV, HoTen, Vang, Ngay) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, student.getMSSV());
            ps.setString(2, student.getHovaten());
            ps.setString(3, student.getVang());
            ps.setString(4, student.getNgay());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //hiển thị bảng sinh viên lên trên màn hình tab
    public ObservableList<Student> getStudentList() {
        ObservableList<Student> studentList = FXCollections.observableArrayList();
        String query = "SELECT MSSV, HoTen, Vang, Ngay from btl.sinhvien";
        try {
            Statement statement = c.getConnection().createStatement();
            ResultSet rs = statement.executeQuery(query);
            Student s;
            while (rs.next()) {
                s = new Student(rs.getString("MSSV"), rs.getString("HoTen"),
                        rs.getString("Vang"), rs.getString("Ngay"));
                studentList.add(s);
                siSo ++;
            }
            rs.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void updateStudents(Student student) {
        try {
            PreparedStatement ps = c.getConnection().prepareStatement("update sinhvien\n"
                    + "set HoTen=?,Vang=?,Ngay=? where MSSV = ?");
            ps.setString(1, student.getHovaten());
            ps.setString(2, student.getVang());
            ps.setString(3, student.getNgay());
            ps.setInt(4, student.getMSSV());
            ps.executeUpdate();
            ps.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public void deleteStudents(Student student){
        try{
            PreparedStatement ps = c.getConnection().prepareStatement("Delete from sinhvien\n"
                    + " where MSSV = ?");
            ps.setInt(1, student.getMSSV());
            ps.executeUpdate();
            ps.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


//    //hiển thị sĩ số lớp
//    public void insertSiSo(){
//        try {
//            String sql = "INSERT INTO lop(SiSo) VALUES (?)";
//            PreparedStatement statement = c.getConnection().prepareStatement(sql);
//            statement.setInt(1, siSo);
//            // Gán các giá trị cho các cột trong câu lệnh SQL
//            statement.executeUpdate();
//            statement.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    public void insertSQLsiSo(Lop lop){
//        try {
//            String sql = "INSERT INTO lop(MSL,TenLop,SiSo) VALUES (?, ?,?)";
//            PreparedStatement statement = c.getConnection().prepareStatement(sql);
//            statement.setInt(1, lop.getMSL());
//            statement.setString(2, lop.getTenLop());
//            statement.setInt(3, lop.getSiSo());
//            // Gán các giá trị cho các cột trong câu lệnh SQL
//            statement.executeUpdate();
//            statement.close();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//


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
