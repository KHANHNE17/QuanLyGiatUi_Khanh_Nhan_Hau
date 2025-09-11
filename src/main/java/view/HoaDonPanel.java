/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import dao.HoaDonDAO;
import dao.PhieuDAO;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HoaDon;


public class HoaDonPanel extends javax.swing.JPanel {

    private HoaDonDAO hdDAO = new HoaDonDAO();
    private DefaultTableModel modelHD;

    public HoaDonPanel() {
        initComponents();

        initData();
        loadCboMaPhieu();
    }

    private void initData() {
        modelHD = new DefaultTableModel(
                new Object[]{"Mã HD", "Mã Phiếu", "Ngày lập", "Tổng tiền", "Đã thanh toán"}, 0
        );
        tblHoaDon.setModel(modelHD);
        loadData();
    }

    private void loadData() {
        modelHD.setRowCount(0);
        for (HoaDon hd : hdDAO.getAll()) {
            modelHD.addRow(new Object[]{
                hd.getMaHD(),
                hd.getMaPhieu(),
                hd.getNgayLap(),
                hd.getTongTien(),
                hd.isDaThanhToan() ? "Đã TT" : "Chưa TT"
            });
        }
    }

    private void loadCboMaPhieu() {
        cboMaPhieu.removeAllItems();
        PhieuDAO phieuDAO = new PhieuDAO();
        for (String maPhieu : phieuDAO.getAllMaPhieu()) {
            cboMaPhieu.addItem(maPhieu);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtMaHDon = new javax.swing.JTextField();
        btnTaoHoaDon = new javax.swing.JButton();
        btnInHoaDon = new javax.swing.JButton();
        btnLamMoi = new javax.swing.JButton();
        cboMaPhieu = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtTongTien = new javax.swing.JTextField();
        dtpNgayLap = new com.toedter.calendar.JDateChooser();
        chkThanhToan = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();

        jButton1.setText("jButton1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 204, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ HÓA ĐƠN");

        jLabel9.setText("Mã phiếu");

        jLabel12.setText("Ngày lập");

        btnTaoHoaDon.setText("Tạo hóa đơn");
        btnTaoHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoHoaDonActionPerformed(evt);
            }
        });

        btnInHoaDon.setText("In hóa đơn");
        btnInHoaDon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInHoaDonActionPerformed(evt);
            }
        });

        btnLamMoi.setText("Làm mới");
        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        cboMaPhieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Mã hóa đơn");

        jLabel17.setText("Đã thanh toán");

        jLabel20.setText("Tổng tiền");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dtpNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaHDon)
                            .addComponent(cboMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTongTien)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(chkThanhToan)
                                .addGap(105, 105, 105)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dtpNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaHDon, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTaoHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
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
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        if (row >= 0) {
            txtMaHDon.setText(modelHD.getValueAt(row, 0).toString());
            cboMaPhieu.setSelectedItem(modelHD.getValueAt(row, 1).toString());

            Object dateObj = modelHD.getValueAt(row, 2);
            if (dateObj instanceof java.util.Date) {
                dtpNgayLap.setDate((java.util.Date) dateObj);  // đã sửa lại đúng tên biến
            }

            txtTongTien.setText(modelHD.getValueAt(row, 3).toString());
            chkThanhToan.setSelected("Đã TT".equals(modelHD.getValueAt(row, 4).toString()));
        }
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        txtMaHDon.setText("");
        txtTongTien.setText("");
        dtpNgayLap.setDate(null);
        chkThanhToan.setSelected(false);
        if (cboMaPhieu.getItemCount() > 0) {
            cboMaPhieu.setSelectedIndex(0);
        }
        tblHoaDon.clearSelection();
        loadData();
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void btnInHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInHoaDonActionPerformed
        JOptionPane.showMessageDialog(this, "Chức năng in hóa đơn sẽ được triển khai trong tương lai!");
    }//GEN-LAST:event_btnInHoaDonActionPerformed

    private void btnTaoHoaDonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoHoaDonActionPerformed
        try {
            String maHD = txtMaHDon.getText().trim();
            String maPhieu = cboMaPhieu.getSelectedItem().toString();
            java.util.Date ngay = dtpNgayLap.getDate();
            double tongTien = Double.parseDouble(txtTongTien.getText().trim());
            boolean daTT = chkThanhToan.isSelected();

            if (maHD.isEmpty() || maPhieu.isEmpty() || ngay == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!");
                return;
            }

            HoaDon hd = new HoaDon(maHD, maPhieu, new java.sql.Date(ngay.getTime()), tongTien, daTT);
            if (hdDAO.insert(hd)) {
                JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể tạo hóa đơn!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi dữ liệu: " + e.getMessage());
        }
    }//GEN-LAST:event_btnTaoHoaDonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnInHoaDon;
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnTaoHoaDon;
    private javax.swing.JComboBox<String> cboMaPhieu;
    private javax.swing.JCheckBox chkThanhToan;
    private com.toedter.calendar.JDateChooser dtpNgayLap;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTextField txtMaHDon;
    private javax.swing.JTextField txtTongTien;
    // End of variables declaration//GEN-END:variables

}
