/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.math.BigDecimal;
import java.sql.Date;


public class HoaDon {
    private String maHD;
    private String maPhieu;
    private Date ngayLap;
    private double tongTien;
    private boolean daThanhToan;

    public HoaDon() {}

    public HoaDon(String maHD, String maPhieu, Date ngayLap, double tongTien, boolean daThanhToan) {
        this.maHD = maHD;
        this.maPhieu = maPhieu;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.daThanhToan = daThanhToan;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isDaThanhToan() {
        return daThanhToan;
    }

    public void setDaThanhToan(boolean daThanhToan) {
        this.daThanhToan = daThanhToan;
    }
    
    
}
