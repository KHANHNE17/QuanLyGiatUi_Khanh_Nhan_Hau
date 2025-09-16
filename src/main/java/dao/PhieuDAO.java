package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Phieu;
import util.DBConnection;

public class PhieuDAO {

    // Lấy danh sách tất cả mã phiếu (dùng cho combobox)
    public List<String> getAllMaPhieu() {
        List<String> list = new ArrayList<>();
        String sql = "SELECT MaPhieu FROM Phieu";

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("MaPhieu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Phieu> getAll() {
        List<Phieu> list = new ArrayList<>();
        String sql = "SELECT * FROM Phieu";

        try (Connection con = DBConnection.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                Phieu p = new Phieu(
                        rs.getString("MaPhieu"),
                        rs.getString("MaKH"),
                        rs.getDate("NgayNhan"),
                        rs.getDate("NgayTra"),
                        rs.getString("TrangThai")
                );
                list.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Phieu getById(String maPhieu) {
        String sql = "SELECT * FROM Phieu WHERE MaPhieu=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Phieu(
                        rs.getString("MaPhieu"),
                        rs.getString("MaKH"),
                        rs.getDate("NgayNhan"),
                        rs.getDate("NgayTra"),
                        rs.getString("TrangThai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean insert(Phieu p) {
        String sql = "INSERT INTO Phieu(MaPhieu, MaKH, NgayNhan, NgayTra, TrangThai) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getMaPhieu());
            ps.setString(2, p.getMaKH());
            ps.setDate(3, p.getNgayNhan());
            ps.setDate(4, p.getNgayTra());
            ps.setString(5, p.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Phieu p) {
        String sql = "UPDATE Phieu SET MaKH=?, NgayNhan=?, NgayTra=?, TrangThai=? WHERE MaPhieu=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getMaKH());
            ps.setDate(2, p.getNgayNhan());
            ps.setDate(3, p.getNgayTra());
            ps.setString(4, p.getTrangThai());
            ps.setString(5, p.getMaPhieu());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(String maPhieu) {
        String sql = "DELETE FROM Phieu WHERE MaPhieu=?";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maPhieu);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }  

}
