/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.HoaDon;
import util.DBConnection;


public class HoaDonDAO {

    public List<HoaDon> getAll() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM HoaDon";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                HoaDon hd = new HoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaPhieu"),
                        rs.getDate("NgayLap"),
                        rs.getDouble("TongTien"),
                        rs.getBoolean("DaThanhToan")
                );
                list.add(hd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(HoaDon hd) {
        String sql = "INSERT INTO HoaDon(MaHD, MaPhieu, NgayLap, TongTien, DaThanhToan) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getMaHD());
            ps.setString(2, hd.getMaPhieu());
            ps.setDate(3, hd.getNgayLap());
            ps.setDouble(4, hd.getTongTien());
            ps.setBoolean(5, hd.isDaThanhToan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(HoaDon hd) {
        String sql = "UPDATE HoaDon SET MaPhieu=?, NgayLap=?, TongTien=?, DaThanhToan=? WHERE MaHD=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, hd.getMaPhieu());
            ps.setDate(2, hd.getNgayLap());
            ps.setDouble(3, hd.getTongTien());
            ps.setBoolean(4, hd.isDaThanhToan());
            ps.setString(5, hd.getMaHD());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maHD) {
        String sql = "DELETE FROM HoaDon WHERE MaHD=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maHD);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
