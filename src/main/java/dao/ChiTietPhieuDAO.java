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
import model.ChiTietPhieu;
import util.DBConnection;


public class ChiTietPhieuDAO {

    public List<ChiTietPhieu> getAll() {
        List<ChiTietPhieu> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieu";

        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                ChiTietPhieu ct = new ChiTietPhieu(
                        rs.getString("MaPhieu"),
                        rs.getString("MaDV"),
                        rs.getInt("SoLuong")
                );
                list.add(ct);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ChiTietPhieu> getByPhieu(String maPhieu) {
        List<ChiTietPhieu> list = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieu WHERE MaPhieu=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChiTietPhieu ct = new ChiTietPhieu(
                        rs.getString("MaPhieu"),
                        rs.getString("MaDV"),
                        rs.getInt("SoLuong")
                );
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(ChiTietPhieu ct) {
        String sql = "INSERT INTO ChiTietPhieu(MaPhieu, MaDV, SoLuong) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ct.getMaPhieu());
            ps.setString(2, ct.getMaDV());
            ps.setInt(3, ct.getSoLuong());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(ChiTietPhieu ct) {
        String sql = "UPDATE ChiTietPhieu SET SoLuong=? WHERE MaPhieu=? AND MaDV=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, ct.getSoLuong());
            ps.setString(2, ct.getMaPhieu());
            ps.setString(3, ct.getMaDV());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maPhieu, String maDV) {
        String sql = "DELETE FROM ChiTietPhieu WHERE MaPhieu=? AND MaDV=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            ps.setString(2, maDV);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
