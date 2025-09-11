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


-- Thêm phiếu giặt ủi
INSERT INTO Phieu (MaPhieu, MaKH, NgayNhan, NgayTra, TrangThai) VALUES
('P001', 'KH001', '2025-09-08', '2025-09-10', N'Đang giặt'),
('P002', 'KH002', '2025-09-07', '2025-09-09', N'Đã trả'),
('P003', 'KH003', '2025-09-06', '2025-09-08', N'Đã xong');
GO

-- Thêm chi tiết phiếu
INSERT INTO ChiTietPhieu (MaPhieu, MaDV, SoLuong) VALUES
('P001', 'DV01', 3),
('P001', 'DV03', 2),
('P002', 'DV02', 5),
('P003', 'DV04', 4);
GO

-- Thêm hóa đơn
INSERT INTO HoaDon (MaHD, MaPhieu, NgayLap, TongTien, DaThanhToan) VALUES
('HD001', 'P001', '2025-09-10', 80000, 0),
('HD002', 'P002', '2025-09-09', 75000, 1),
('HD003', 'P003', '2025-09-08', 100000, 1);
GO


