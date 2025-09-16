/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import util.DBConnection;
import util.RefreshablePanel;


public class ThongKePanel extends javax.swing.JPanel implements RefreshablePanel{

    private Connection conn;
    private DefaultTableModel tableModel;
    private DefaultTableModel tongCongModel;

    public ThongKePanel() {
        initComponents();

        conn = DBConnection.getConnection();
        initTable();
        loadComboBox();

        chkTheoNam.setSelected(true);
        cboThang.setEnabled(false);

        if (cboNam.getItemCount() > 0) {
            cboNam.setSelectedIndex(cboNam.getItemCount() - 1);
            int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
            loadData(0, nam, true);
        }

        cboNam.addActionListener(e -> autoLoadData());
        cboThang.addActionListener(e -> autoLoadData());
        chkTheoNam.addActionListener(e -> {
            cboThang.setEnabled(!chkTheoNam.isSelected());
            autoLoadData();
        });
    }

    // ======== KHỞI TẠO TABLE ========
    private void initTable() {
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{
            "Mã Phiếu", "Khách Hàng", "Ngày Nhận", "Ngày Trả", "Dịch Vụ", "Số Lượng", "Thành Tiền"
        });
        tblThongKe.setModel(tableModel);

        tongCongModel = new DefaultTableModel();
        tongCongModel.setColumnIdentifiers(new String[]{
            "Khoản mục", "Số tiền", "Tỷ lệ (%)"
        });
        tblTongCong.setModel(tongCongModel);
    }

    private void loadComboBox() {
        cboThang.removeAllItems();
        for (int i = 1; i <= 12; i++) {
            cboThang.addItem(String.valueOf(i));
        }

        cboNam.removeAllItems();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT DISTINCT YEAR(NgayNhan) as Nam FROM Phieu ORDER BY Nam ASC");
            while (rs.next()) {
                cboNam.addItem(String.valueOf(rs.getInt("Nam")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi load năm: " + e.getMessage());
        }
    }

    private void loadData(int thang, int nam, boolean theoNam) {
        tableModel.setRowCount(0);
        double dtGiatUi = 0, dtGiatKho = 0, dtChanMen = 0, dtGiay = 0, tongDT = 0;

        try {
            String sql = "SELECT p.MaPhieu, p.MaKH, p.NgayNhan, p.NgayTra, dv.TenDV, ct.SoLuong, (ct.SoLuong * dv.DonGia) as ThanhTien "
                    + "FROM ChiTietPhieu ct "
                    + "JOIN Phieu p ON ct.MaPhieu = p.MaPhieu "
                    + "JOIN DichVu dv ON ct.MaDV = dv.MaDV "
                    + "WHERE YEAR(p.NgayNhan) = ? "
                    + (theoNam ? "" : "AND MONTH(p.NgayNhan) = ? ");

            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, nam);
            if (!theoNam) {
                pst.setInt(2, thang);
            }

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getString("MaPhieu"));
                row.add(rs.getString("MaKH"));
                row.add(rs.getDate("NgayNhan"));
                row.add(rs.getDate("NgayTra"));
                row.add(rs.getString("TenDV"));
                row.add(rs.getInt("SoLuong"));
                row.add(String.format("%,.0f đ", rs.getDouble("ThanhTien")));
                tableModel.addRow(row);

                double tien = rs.getDouble("ThanhTien");
                tongDT += tien;

                String tenDV = rs.getString("TenDV").toLowerCase();
                if (tenDV.contains("ủi") || tenDV.contains("quần áo")) {
                    dtGiatUi += tien;
                } else if (tenDV.contains("khô") || tenDV.contains("vest")) {
                    dtGiatKho += tien;
                } else if (tenDV.contains("chăn") || tenDV.contains("mền")) {
                    dtChanMen += tien;
                } else if (tenDV.contains("giày")) {
                    dtGiay += tien;
                }
            }

            // Cập nhật label
            lblDTgiatui.setText(String.format("%,.0f đ", dtGiatUi));
            lblDTgiatkho.setText(String.format("%,.0f đ", dtGiatKho));
            lblDTgiatchanmen.setText(String.format("%,.0f đ", dtChanMen));
            lblDTgiatgiay.setText(String.format("%,.0f đ", dtGiay));
            lblTongDT.setText(String.format("%,.0f đ", tongDT));

            if (tongDT > 0) {
                lblPTgiatui.setText(String.format("Chiếm %.2f%%", dtGiatUi / tongDT * 100));
                lblPTgiatkho.setText(String.format("Chiếm %.2f%%", dtGiatKho / tongDT * 100));
                lblPTgiatchanmen.setText(String.format("Chiếm %.2f%%", dtChanMen / tongDT * 100));
                lblPTgiatgiay.setText(String.format("Chiếm %.2f%%", dtGiay / tongDT * 100));
            }

            tongCongModel.setRowCount(0);
            if (tongDT > 0) {
                tongCongModel.addRow(new Object[]{"Giặt Ủi Quần Áo", String.format("%,.0f đ", dtGiatUi), String.format("%.2f %%", dtGiatUi / tongDT * 100)});
                tongCongModel.addRow(new Object[]{"Giặt Khô Vest", String.format("%,.0f đ", dtGiatKho), String.format("%.2f %%", dtGiatKho / tongDT * 100)});
                tongCongModel.addRow(new Object[]{"Giặt Chăn Mền", String.format("%,.0f đ", dtChanMen), String.format("%.2f %%", dtChanMen / tongDT * 100)});
                tongCongModel.addRow(new Object[]{"Giặt Giày", String.format("%,.0f đ", dtGiay), String.format("%.2f %%", dtGiay / tongDT * 100)});
                tongCongModel.addRow(new Object[]{"TỔNG CỘNG", String.format("%,.0f đ", tongDT), "100 %"});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi load dữ liệu: " + e.getMessage());
        }
    }

    private void autoLoadData() {
        if (cboNam.getSelectedItem() == null) {
            return;
        }
        int nam = Integer.parseInt(cboNam.getSelectedItem().toString());
        boolean theoNam = chkTheoNam.isSelected();
        int thang = 0;
        if (!theoNam && cboThang.getSelectedItem() != null) {
            thang = Integer.parseInt(cboThang.getSelectedItem().toString());
        }
        loadData(thang, nam, theoNam);
    }

    @Override
    public void refreshData()
    {
        loadData(WIDTH, WIDTH, true);
        autoLoadData();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblDTgiatui = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lblPTgiatui = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDTgiatkho = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        lblPTgiatkho = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        lblDTgiatchanmen = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        lblPTgiatchanmen = new javax.swing.JLabel();
        btnThongKe = new javax.swing.JButton();
        cboNam = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboThang = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        chkTheoNam = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        lblDTgiatgiay = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lblPTgiatgiay = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblTongDT = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTongCong = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ THỐNG KÊ");

        jPanel4.setBackground(new java.awt.Color(153, 153, 255));

        lblDTgiatui.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTgiatui.setForeground(new java.awt.Color(255, 255, 255));
        lblDTgiatui.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDTgiatui.setText("999999 đ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Giặt Ủi Quần Áo");

        lblPTgiatui.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPTgiatui.setForeground(new java.awt.Color(255, 255, 255));
        lblPTgiatui.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPTgiatui.setText("Chiếm  % tổng doanh thu");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblDTgiatui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblPTgiatui, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTgiatui)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPTgiatui, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));

        lblDTgiatkho.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTgiatkho.setForeground(new java.awt.Color(255, 255, 255));
        lblDTgiatkho.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDTgiatkho.setText("999999 đ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Giặt Khô (Vest)");

        lblPTgiatkho.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPTgiatkho.setForeground(new java.awt.Color(255, 255, 255));
        lblPTgiatkho.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPTgiatkho.setText("Chiếm  % tổng doanh thu");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPTgiatkho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(lblDTgiatkho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTgiatkho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPTgiatkho, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel6.setBackground(new java.awt.Color(255, 153, 0));

        lblDTgiatchanmen.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTgiatchanmen.setForeground(new java.awt.Color(255, 255, 255));
        lblDTgiatchanmen.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDTgiatchanmen.setText("999999 đ");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Giặt Chăn, Mền");

        lblPTgiatchanmen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPTgiatchanmen.setForeground(new java.awt.Color(255, 255, 255));
        lblPTgiatchanmen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPTgiatchanmen.setText("Chiếm  % tổng doanh thu");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPTgiatchanmen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(lblDTgiatchanmen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTgiatchanmen)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPTgiatchanmen, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnThongKe.setBackground(new java.awt.Color(255, 153, 51));
        btnThongKe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThongKe.setForeground(new java.awt.Color(255, 255, 255));
        btnThongKe.setText("Thống kê");
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        cboNam.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("năm");

        cboThang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Tháng");

        chkTheoNam.setText("Theo năm");

        jPanel7.setBackground(new java.awt.Color(0, 153, 153));

        lblDTgiatgiay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblDTgiatgiay.setForeground(new java.awt.Color(255, 255, 255));
        lblDTgiatgiay.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDTgiatgiay.setText("999999 đ");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Giặt Giày");

        lblPTgiatgiay.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPTgiatgiay.setForeground(new java.awt.Color(255, 255, 255));
        lblPTgiatgiay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblPTgiatgiay.setText("Chiếm  % tổng doanh thu");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPTgiatgiay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDTgiatgiay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblDTgiatgiay)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblPTgiatgiay, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboThang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(chkTheoNam, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboThang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkTheoNam, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cboNam))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(153, 204, 255));

        lblTongDT.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblTongDT.setForeground(new java.awt.Color(255, 255, 255));
        lblTongDT.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTongDT.setText("999999 đ");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Tổng Doanh Thu");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("100% tổng doanh thu");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
                    .addComponent(lblTongDT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTongDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblThongKe);

        tblTongCong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblTongCong);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        autoLoadData();
    }//GEN-LAST:event_btnThongKeActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThongKe;
    private javax.swing.JComboBox<String> cboNam;
    private javax.swing.JComboBox<String> cboThang;
    private javax.swing.JCheckBox chkTheoNam;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDTgiatchanmen;
    private javax.swing.JLabel lblDTgiatgiay;
    private javax.swing.JLabel lblDTgiatkho;
    private javax.swing.JLabel lblDTgiatui;
    private javax.swing.JLabel lblPTgiatchanmen;
    private javax.swing.JLabel lblPTgiatgiay;
    private javax.swing.JLabel lblPTgiatkho;
    private javax.swing.JLabel lblPTgiatui;
    private javax.swing.JLabel lblTongDT;
    private javax.swing.JTable tblThongKe;
    private javax.swing.JTable tblTongCong;
    // End of variables declaration//GEN-END:variables

}
