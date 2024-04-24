package com.example.btl_ltnc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(Test.class.getResource("ThoikhoabieuGVView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 973, 661);
            primaryStage.setTitle("Quản lý điểm");
            primaryStage.setScene(scene);
            primaryStage.show();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}

