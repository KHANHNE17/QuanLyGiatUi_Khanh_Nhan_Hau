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
import model.DichVu;
import util.DBConnection;


public class DichVuDAO {

    public List<DichVu> getAll() {
        List<DichVu> list = new ArrayList<>();
        String sql = "SELECT * FROM DichVu";

        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                DichVu dv = new DichVu(
                        rs.getString("MaDV"),
                        rs.getString("TenDV"),
                        rs.getDouble("DonGia"),
                        rs.getString("DonViTinh")
                );
                list.add(dv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(DichVu dv) {
        String sql = "INSERT INTO DichVu(MaDV, TenDV, DonGia, DonViTinh) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dv.getMaDV());
            ps.setString(2, dv.getTenDV());
            ps.setDouble(3, dv.getDonGia());
            ps.setString(4, dv.getDonViTinh());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(DichVu dv) {
        String sql = "UPDATE DichVu SET TenDV=?, DonGia=?, DonViTinh=? WHERE MaDV=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dv.getTenDV());
            ps.setDouble(2, dv.getDonGia());
            ps.setString(3, dv.getDonViTinh());
            ps.setString(4, dv.getMaDV());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maDV) {
        String sql = "DELETE FROM DichVu WHERE MaDV=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maDV);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
