/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.ChiTietPhieuDAO;
import dao.PhieuDAO;
import dao.KhachHangDAO;
import dao.DichVuDAO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChiTietPhieu;
import model.Phieu;
import model.KhachHang;
import model.DichVu;
import util.DBConnection;
import util.RefreshablePanel;


public class PhieuGiatUiPanel extends javax.swing.JPanel implements RefreshablePanel {

    PhieuDAO phieuDAO = new PhieuDAO();
    ChiTietPhieuDAO ctDAO = new ChiTietPhieuDAO();
    KhachHangDAO khDAO = new KhachHangDAO();
    DichVuDAO dvDAO = new DichVuDAO();

    DefaultTableModel modelPhieu;
    DefaultTableModel modelCT;

    /**
     * Creates new form QuanLyDocGiaPanel
     */
    public PhieuGiatUiPanel() throws SQLException {
        initComponents();

        initData();
        loadComboBox();
        refreshData();
    }

    private void initData() {
        // Setup bảng phiếu
        modelPhieu = new DefaultTableModel(new Object[]{"Mã Phiếu", "Mã KH", "Ngày nhận", "Ngày trả", "Trạng thái"}, 0);
        tblPhieu.setModel(modelPhieu);

        // Setup bảng chi tiết
        modelCT = new DefaultTableModel(new Object[]{"Mã Phiếu", "Mã DV", "Số lượng"}, 0);
        tblChiTietPhieu.setModel(modelCT);

        refreshData();
        loadComboBox();
    }

    public void refreshData() {
        modelPhieu.setRowCount(0);
        List<Phieu> list = phieuDAO.getAll();
        for (Phieu p : list) {
            modelPhieu.addRow(new Object[]{
                p.getMaPhieu(), p.getMaKH(), p.getNgayNhan(), p.getNgayTra(), p.getTrangThai()
            });
        }
        loadComboBox();
        
    }

    private void loadComboBox() {
        cboMaKH.removeAllItems();
        for (KhachHang kh : khDAO.getAll()) {
            cboMaKH.addItem(kh.getMaKH());
        }
        cboTrangThai.removeAllItems();
        cboTrangThai.addItem("Chờ xử lý");
        cboTrangThai.addItem("Đang giặt");
        cboTrangThai.addItem("Hoàn tất");
    }

    private void loadChiTiet(String maPhieu) {
        modelCT.setRowCount(0);
        for (ChiTietPhieu ct : ctDAO.getByPhieu(maPhieu)) {
            modelCT.addRow(new Object[]{ct.getMaPhieu(), ct.getMaDV(), ct.getSoLuong()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieu = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        btnTimKiem = new javax.swing.JButton();
        cboMaKH = new javax.swing.JComboBox<>();
        cboTrangThai = new javax.swing.JComboBox<>();
        dtpNgayNhan = new com.toedter.calendar.JDateChooser();
        dtpNgayTra = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietPhieu = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnXoaDV = new javax.swing.JButton();
        btnThemDV = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setAutoscrolls(true);

        tblPhieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPhieuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPhieu);

        jLabel2.setText("Mã khách hàng");

        jLabel3.setText("Trạng thái");

        jLabel4.setText("Ngày trả");

        jLabel5.setText("Ngày nhận");

        jLabel7.setText("Mã Phiếu");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        cboMaKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(cboMaKH, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cboTrangThai, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dtpNgayTra, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
                            .addComponent(dtpNgayNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 197, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpNgayNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dtpNgayTra, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTrangThai)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ HỢP ĐỒNG");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 102, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Chi tiết phiếu");

        tblChiTietPhieu.setModel(new javax.swing.table.DefaultTableModel(
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
        tblChiTietPhieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietPhieuMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietPhieu);

        btnXoaDV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnXoaDV.setText("Xóa DV");
        btnXoaDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaDVActionPerformed(evt);
            }
        });

        btnThemDV.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnThemDV.setText("Thêm DV");
        btnThemDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemDVActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXoaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnXoaDV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemDV, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhieuMouseClicked
        int row = tblPhieu.getSelectedRow();
        if (row >= 0) {
            String maPhieu = tblPhieu.getValueAt(row, 0).toString();
            txtMaPhieu.setText(maPhieu);
            cboMaKH.setSelectedItem(tblPhieu.getValueAt(row, 1).toString());
            dtpNgayNhan.setDate((java.util.Date) tblPhieu.getValueAt(row, 2));
            dtpNgayTra.setDate((java.util.Date) tblPhieu.getValueAt(row, 3));
            cboTrangThai.setSelectedItem(tblPhieu.getValueAt(row, 4).toString());

            loadChiTiet(maPhieu);
        }
    }//GEN-LAST:event_tblPhieuMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        Phieu p = new Phieu(
                txtMaPhieu.getText(),
                cboMaKH.getSelectedItem().toString(),
                new java.sql.Date(dtpNgayNhan.getDate().getTime()),
                new java.sql.Date(dtpNgayTra.getDate().getTime()),
                cboTrangThai.getSelectedItem().toString()
        );
        if (phieuDAO.insert(p)) {
            JOptionPane.showMessageDialog(this, "Thêm thành công");
            refreshData();
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        Phieu p = new Phieu(
                txtMaPhieu.getText(),
                cboMaKH.getSelectedItem().toString(),
                new java.sql.Date(dtpNgayNhan.getDate().getTime()),
                new java.sql.Date(dtpNgayTra.getDate().getTime()),
                cboTrangThai.getSelectedItem().toString()
        );
        if (phieuDAO.update(p)) {
            JOptionPane.showMessageDialog(this, "Cập nhật thành công");
            refreshData();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        String maPhieu = txtMaPhieu.getText();
        if (phieuDAO.delete(maPhieu)) {
            JOptionPane.showMessageDialog(this, "Xóa thành công");
            refreshData();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtMaPhieu.setText("");
        cboMaKH.setSelectedIndex(-1);
        cboTrangThai.setSelectedIndex(-1);
        dtpNgayNhan.setDate(null);
        dtpNgayTra.setDate(null);
        modelCT.setRowCount(0);
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        String ma = txtMaPhieu.getText().trim();
        if (ma.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nhập mã phiếu cần tìm!");
            return;
        }
        Phieu p = phieuDAO.getById(ma);
        if (p != null) {
            modelPhieu.setRowCount(0);
            modelPhieu.addRow(new Object[]{
                p.getMaPhieu(), p.getMaKH(), p.getNgayNhan(), p.getNgayTra(), p.getTrangThai()
            });
            loadChiTiet(ma);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy phiếu!");
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblChiTietPhieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietPhieuMouseClicked

    }//GEN-LAST:event_tblChiTietPhieuMouseClicked

    private void btnThemDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemDVActionPerformed
        String maPhieu = txtMaPhieu.getText();
        if (maPhieu.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chọn phiếu trước!");
            return;
        }
        // chọn dịch vụ từ list
        List<DichVu> list = dvDAO.getAll();
        String[] arr = list.stream().map(dv -> dv.getMaDV() + " - " + dv.getTenDV()).toArray(String[]::new);
        String choice = (String) JOptionPane.showInputDialog(this, "Chọn dịch vụ", "Dịch vụ",
                JOptionPane.PLAIN_MESSAGE, null, arr, arr[0]);
        if (choice == null) {
            return;
        }
        String maDV = choice.split(" - ")[0];

        String soLuongStr = JOptionPane.showInputDialog(this, "Nhập số lượng:");
        if (soLuongStr == null) {
            return;
        }
        int soLuong = Integer.parseInt(soLuongStr);

        ChiTietPhieu ct = new ChiTietPhieu(maPhieu, maDV, soLuong);
        if (ctDAO.insert(ct)) {
            JOptionPane.showMessageDialog(this, "Thêm dịch vụ thành công");
            loadChiTiet(maPhieu);
        }
    }//GEN-LAST:event_btnThemDVActionPerformed

    private void btnXoaDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaDVActionPerformed
        int row = tblChiTietPhieu.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Chọn dòng dịch vụ cần xóa!");
            return;
        }
        String maPhieu = tblChiTietPhieu.getValueAt(row, 0).toString();
        String maDV = tblChiTietPhieu.getValueAt(row, 1).toString();

        if (ctDAO.delete(maPhieu, maDV)) {
            JOptionPane.showMessageDialog(this, "Xóa dịch vụ thành công");
            loadChiTiet(maPhieu);
        }
    }//GEN-LAST:event_btnXoaDVActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemDV;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXoaDV;
    private javax.swing.JComboBox<String> cboMaKH;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser dtpNgayNhan;
    private com.toedter.calendar.JDateChooser dtpNgayTra;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblChiTietPhieu;
    private javax.swing.JTable tblPhieu;
    private javax.swing.JTextField txtMaPhieu;
    // End of variables declaration//GEN-END:variables

}
