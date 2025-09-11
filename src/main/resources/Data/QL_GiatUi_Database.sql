------------------------------------------------------------
-- 1. TẠO DATABASE
------------------------------------------------------------
IF DB_ID('QL_GiatUi') IS NULL
    CREATE DATABASE QL_GiatUi;
GO
USE QL_GiatUi;
GO

-- Bảng Khách hàng
CREATE TABLE KhachHang (
    MaKH VARCHAR(20) PRIMARY KEY,   -- do người dùng nhập (vd: KH001, KH002)
    HoTen NVARCHAR(100) NOT NULL,
    SDT VARCHAR(20),
    DiaChi NVARCHAR(200)
);
GO

-- Bảng Dịch vụ
CREATE TABLE DichVu (
    MaDV VARCHAR(20) PRIMARY KEY,   -- do người dùng nhập (vd: DV01, DV02)
    TenDV NVARCHAR(100) NOT NULL,
    DonGia DECIMAL(18,2) NOT NULL,
    DonViTinh NVARCHAR(50) -- kg, cái, bộ...
);
GO

-- Bảng Phiếu giặt ủi (Đơn hàng)
CREATE TABLE Phieu (
    MaPhieu VARCHAR(20) PRIMARY KEY, -- vd: P001
    MaKH VARCHAR(20) FOREIGN KEY REFERENCES KhachHang(MaKH),
    NgayNhan DATE NOT NULL,
    NgayTra DATE,
    TrangThai NVARCHAR(50) -- Đang giặt, Đã xong, Đã trả
);
GO

-- Bảng Chi tiết phiếu
CREATE TABLE ChiTietPhieu (
    MaPhieu VARCHAR(20) FOREIGN KEY REFERENCES Phieu(MaPhieu),
    MaDV VARCHAR(20) FOREIGN KEY REFERENCES DichVu(MaDV),
    SoLuong INT NOT NULL,
    PRIMARY KEY (MaPhieu, MaDV)
);
GO

-- Bảng Hóa đơn
CREATE TABLE HoaDon (
    MaHD VARCHAR(20) PRIMARY KEY, -- vd: HD001
    MaPhieu VARCHAR(20) FOREIGN KEY REFERENCES Phieu(MaPhieu),
    NgayLap DATE NOT NULL,
    TongTien DECIMAL(18,2) NOT NULL,
    DaThanhToan BIT
);
GO


