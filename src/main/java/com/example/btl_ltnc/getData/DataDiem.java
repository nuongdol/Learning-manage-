package com.example.btl_ltnc.getData;

import com.example.btl_ltnc.database.DBConnection;
import com.example.btl_ltnc.models.Grade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DataDiem {
        private DBConnection c = new DBConnection();
        private Connection conn;

        public void insertSQL(Grade grade){
            try {
                String sql = "INSERT INTO diemmonhoc (MSTKD, MSSV, Tensinhvien) VALUES (?,?,?)";
                PreparedStatement statement = c.getConnection().prepareStatement(sql);
                statement.setInt(1, grade.getMSTKD());
                statement.setInt(2, grade.getMSSV());
                statement.setString(3, grade.getHovaten());
                // Gán các giá trị cho các cột trong câu lệnh SQL
                statement.executeUpdate();
                statement.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        public void addGrade(Grade grade) {
            try {
                try {
                    PreparedStatement ps = c.getConnection().prepareStatement("update diemmonhoc\n"
                            + "set DiemQuaTrinh=?,DiemCuoiKy=?,TenSinhVien=? where MSSV = ? ");
                    ps.setFloat(1, grade.getDiemQT());
                    ps.setFloat(2, grade.getDiemCK());
                    ps.setString(3, grade.getHovaten());
                    ps.setInt(4, grade.getMSSV());
                   // ps.setInt(5, grade.getMSTKD());
                    ps.executeUpdate();
                    ps.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //hiển thị bảng sinh viên lên trên màn hình tab
        public ObservableList<Grade> getGradeList() {
            ObservableList<Grade> gradeList = FXCollections.observableArrayList();
            String query = "select MSSV,TenSinhVien,DiemQuaTrinh,DiemCuoiKy from btl.diemmonhoc";
            try {
                Statement statement = c.getConnection().createStatement();
                ResultSet rs = statement.executeQuery(query);
                Grade g;
                while (rs.next()) {
                    float t = (float)(rs.getFloat("DiemQuaTrinh")*0.3+ rs.getFloat("DiemCuoiKy")*0.7);
                    g = new Grade(rs.getInt("MSSV"), rs.getString("TenSinhVien"),
                            rs.getFloat("DiemQuaTrinh"), rs.getFloat("DiemCuoiKy"),t);
                    gradeList.add(g);
                }
                rs.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gradeList;
        }


        public void updateGrade(Grade grade) {
            try {
                PreparedStatement ps = c.getConnection().prepareStatement("update diemmonhoc\n"
                        + "set DiemQuaTrinh=?,DiemCuoiKy=?,TenSinhVien=? where MSSV = ? ");
                ps.setFloat(1, grade.getDiemQT());
                ps.setFloat(2, grade.getDiemCK());
                ps.setString(3, grade.getHovaten());
                ps.setInt(4, grade.getMSSV());
//                ps.setInt(5, grade.getMSTKD());
                ps.executeUpdate();
                ps.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        public void deleteGrade(Grade grade){
            try{
                PreparedStatement ps = c.getConnection().prepareStatement("Delete from diemmonhoc\n"
                        + " where MSSV =?");
                ps.setInt(1, grade.getMSSV());
                ps.executeUpdate();
                ps.close();
            }catch(Exception ex){
                ex.printStackTrace();
            }
        }

//Tính tổng điểm
        public float[] calculateTongdiem(){
            float s[]={};
            ObservableList<Grade> gradeList = FXCollections.observableArrayList();
            String query = "select MSSV,TenSinhVien,DiemQuaTrinh,DiemCuoiKy from btl.diemmonhoc";
            try {
                Statement statement = c.getConnection().createStatement();
                ResultSet rs = statement.executeQuery(query);
                Grade g;
                int i = 0;
                while (rs.next()) {
                    g = new Grade(rs.getInt("MSSV"), rs.getString("TenSinhVien"),
                            rs.getFloat("DiemQuaTrinh"), rs.getFloat("DiemCuoiKy"));
                    gradeList.add(g);

                }
                rs.close();
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return s;
        }


//        public void insertSQL1(Grade grade){
//            try {
//                String sql = "INSERT INTO diemmonhoc (MSSV, TenSinhVien) VALUES (?, ?)";
//                PreparedStatement statement = c.getConnection().prepareStatement(sql);
//                statement.setInt(1, grade.getMSSV());
//                statement.setString(2, grade.getHovaten());
//                // Gán các giá trị cho các cột trong câu lệnh SQL
//                statement.executeUpdate();
//                statement.close();
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        public void load(){
//            try{
//            String query = "SELECT * FROM diemmonhoc";
//            PreparedStatement statement = c.getConnection().prepareStatement(query);
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                int mstkd = resultSet.getInt("MSTKD");
//                int mssv = resultSet.getInt("MSSV");
//                String mslhp = resultSet.getString("MSLHP");
//                String tenmonhoc = resultSet.getString("TenMonHoc");
//                int mahocphan= resultSet.getInt("MaHocPhan");
//                Float diemQT= resultSet.getFloat("DiemQuaTrinh");
//                Float diemCK= resultSet.getFloat("DiemCuoiKy");
//
//            }
//
//            statement.close();
//        } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }

}

