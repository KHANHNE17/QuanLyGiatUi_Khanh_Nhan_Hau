/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;


public class DichVu {

    private String maDV;
    private String tenDV;
    private double donGia;
    private String donViTinh;
    
    public DichVu() {}

    public DichVu(String maDV, String tenDV, double donGia, String donViTinh) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.donGia = donGia;
        this.donViTinh = donViTinh;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public String getDonViTinh() {
        return donViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        this.donViTinh = donViTinh;
    }
    
    
}
