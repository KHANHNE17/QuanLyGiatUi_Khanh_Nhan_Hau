/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;


public class Phieu {
    private String maPhieu;
    private String maKH;
    private Date ngayNhan;
    private Date ngayTra;
    private String trangThai;

    public Phieu() {}

    public Phieu(String maPhieu, String maKH, Date ngayNhan, Date ngayTra, String trangThai) {
        this.maPhieu = maPhieu;
        this.maKH = maKH;
        this.ngayNhan = ngayNhan;
        this.ngayTra = ngayTra;
        this.trangThai = trangThai;
    }

    public String getMaPhieu() {
        return maPhieu;
    }

    public void setMaPhieu(String maPhieu) {
        this.maPhieu = maPhieu;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    public Date getNgayTra() {
        return ngayTra;
    }

    public void setNgayTra(Date ngayTra) {
        this.ngayTra = ngayTra;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
    
}
