package com.example.btl_ltnc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName="btl";
        String databaseUser="root";
        String databasePassword="123456n";
        String url = "jdbc:mysql://localhost/"+databaseName;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            databaseLink = DriverManager.getConnection(url,databaseUser,databasePassword);
            System.out.println("Database connected");
        }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }

//    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=BTL;user=sa;password=27072001;encrypt=true;trustServerCertificate=true;";
//
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(DB_URL);
//    }
}
