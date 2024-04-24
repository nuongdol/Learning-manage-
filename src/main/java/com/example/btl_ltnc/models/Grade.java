package com.example.btl_ltnc.models;

public class Grade {
    private int MSSV;
    private int MSTKD;
    private String Hovaten;
    private Float diemQT;
    private Float diemCK;
    private Float tongDiem;

    public Float getTongDiem() {
        return tongDiem;
    }

    public void setTongDiem(Float tongDiem) {
        this.tongDiem = tongDiem;
    }

    public Grade(int MSSV, String hovaten, Float diemQT, Float diemCK, Float tongDiem) {
        this.MSSV = MSSV;
        Hovaten = hovaten;
        this.diemQT = diemQT;
        this.diemCK = diemCK;
        this.tongDiem = tongDiem;
    }

    public int getMSTKD() {
        return MSTKD;
    }

    public void setMSTKD(int MSTKD) {
        this.MSTKD = MSTKD;
    }

    public Grade(int MSSV, int MSTKD, String hovaten, Float diemQT, Float diemCK) {
        this.MSSV = MSSV;
        this.MSTKD = MSTKD;
        Hovaten = hovaten;
        this.diemQT = diemQT;
        this.diemCK = diemCK;
    }

    public Grade(int MSSV, Float diemQT, Float diemCK) {
        this.MSSV = MSSV;
        this.diemQT = diemQT;
        this.diemCK = diemCK;
    }

    public Grade(int MSSV, String hovaten) {
        this.MSSV = MSSV;
        Hovaten = hovaten;
    }

    public Grade(){

    }

    public Grade(int MSSV, String hovaten, Float diemQT, Float diemCK) {
        this.MSSV = MSSV;
        Hovaten = hovaten;
        this.diemQT = diemQT;
        this.diemCK = diemCK;
    }
    public Grade(Float diemQT, Float diemCK) {
        this.diemQT = diemQT;
        this.diemCK = diemCK;
    }

    public int getMSSV() {
        return MSSV;
    }

    public void setMSSV(int MSSV) {
        this.MSSV = MSSV;
    }

    public String getHovaten() {
        return Hovaten;
    }

    public void setHovaten(String hovaten) {
        Hovaten = hovaten;
    }


    public Float getDiemQT() {
        return diemQT;
    }

    public void setDiemQT(Float diemQT) {
        this.diemQT = diemQT;
    }

    public Float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(Float diemCK) {
        this.diemCK = diemCK;
    }
}
