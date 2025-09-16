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

-- Thêm khách hàng
INSERT INTO KhachHang (MaKH, HoTen, SDT, DiaChi) VALUES
('KH001', N'Nguyễn Văn A', '0901234567', N'123 Lê Lợi, Q1'),
('KH002', N'Trần Thị B', '0912345678', N'45 Nguyễn Huệ, Q1'),
('KH003', N'Lê Văn C', '0987654321', N'78 Hai Bà Trưng, Q3');
GO

-- Thêm dịch vụ phổ biến cho tiệm giặt ủi
INSERT INTO DichVu (MaDV, TenDV, DonGia, DonViTinh) VALUES
('DV01', N'Giặt + Ủi (trọn gói)', 25000, N'kg'),
('DV02', N'Giặt khô (vest)',      50000, N'cái'),
('DV03', N'Giặt chăn, mền',       60000, N'cái'),
('DV04', N'Giặt giày thể thao',   70000, N'đôi');
GO
