package com.example.btl_ltnc.models;



public class Student {
        private int MSSV;
        private String Hovaten;
        private String Vang;
        private String Ngay;


        public Student(int MSSV, String hovaten) {
            this.MSSV = MSSV;
            Hovaten = hovaten;
        }

//        public Student(int MSSV, String hovaten, String vang, String ngay) {
//            this.MSSV = MSSV;
//            Hovaten = hovaten;
//            Vang = vang;
//            Ngay = ngay;
//        }
        public Student(String MSSV,String hovaten, String vang, String ngay){
            this.MSSV = Integer.parseInt(MSSV);
            this.Hovaten=hovaten;
            Vang = vang;
            Ngay = ngay;
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

        public String getVang() {
            return Vang;
        }

        public void setVang(String vang) {
            Vang = vang;
        }

        public String getNgay() {
            return Ngay;
        }

        public void setNgay(String ngay) {
            Ngay = ngay;
        }
}
