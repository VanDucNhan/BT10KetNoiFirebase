package com.google.appketnoivoi_firebase.Model;

public class SinhVienModel {
    String ten,msv,url;

    SinhVienModel(){

    }
    public SinhVienModel(String ten, String msv, String url) {
        this.ten = ten;
        this.msv = msv;
        this.url = url;
    }

    public String getTen() {
        return ten;
    }

    public void setName(String ten) {
        this.ten = ten;
    }

    public String getMsv() {
        return msv;
    }

    public void setMsv(String msv) {
        this.msv = msv;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
