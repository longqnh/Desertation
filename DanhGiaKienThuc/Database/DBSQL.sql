CREATE DATABASE IF NOT EXISTS danhgiakienthuc;
USE danhgiakienthuc;

CREATE TABLE `danhgiakienthuc`.`table_user` (
  `username` VARCHAR(25) NOT NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  PRIMARY KEY (`username`));
INSERT INTO `danhgiakienthuc`.`table_user` (`username`, `password`, `name`, `email`) VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'Administrator', 'abc@xyz.com');

CREATE TABLE `danhgiakienthuc`.`table_hamso` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `danhgiakienthuc`.`table_loga` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `danhgiakienthuc`.`table_tichphan` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `danhgiakienthuc`.`table_sophuc` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `danhgiakienthuc`.`table_hhkg` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `danhgiakienthuc`.`table_oxyz` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `danhgiakienthuc`.`table_dethi` (
  `id` VARCHAR(45) NOT NULL,
  `noidung` LONGTEXT NULL,
  `dapanA` LONGTEXT NULL,
  `dapanB` LONGTEXT NULL,
  `dapanC` LONGTEXT NULL,
  `dapanD` LONGTEXT NULL,
  `dapan` VARCHAR(45) NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbt` VARCHAR(45) NULL,
  `dokho` INT NULL,
  `dophancach` INT NULL,
  `malop` INT NULL,
  `hinh` INT NULL,
  PRIMARY KEY (`id`));
  
CREATE TABLE `danhgiakienthuc`.`table_dokho` (
  `dokho` INT NOT NULL,
  `mucdo` VARCHAR(45) NULL,
  PRIMARY KEY (`dokho`));
INSERT INTO `danhgiakienthuc`.`table_dokho` (`dokho`, `mucdo`) VALUES ('0', 'nhận biết');
INSERT INTO `danhgiakienthuc`.`table_dokho` (`dokho`, `mucdo`) VALUES ('1', 'thổng hiểu');
INSERT INTO `danhgiakienthuc`.`table_dokho` (`dokho`, `mucdo`) VALUES ('2', 'vận dụng');
INSERT INTO `danhgiakienthuc`.`table_dokho` (`dokho`, `mucdo`) VALUES ('3', 'vận dụng cao');

CREATE TABLE `danhgiakienthuc`.`table_lop` (
  `malop` INT NOT NULL,
  `tenlop` VARCHAR(45) NULL,
  PRIMARY KEY (`malop`));
INSERT INTO `danhgiakienthuc`.`table_lop` (`malop`, `tenlop`) VALUES ('10', 'Lớp 10');
INSERT INTO `danhgiakienthuc`.`table_lop` (`malop`, `tenlop`) VALUES ('11', 'Lớp 11');
INSERT INTO `danhgiakienthuc`.`table_lop` (`malop`, `tenlop`) VALUES ('12', 'Lớp 12');

CREATE TABLE `danhgiakienthuc`.`table_phanloaidangtoan` (
  `dangtoan` VARCHAR(45) NOT NULL,
  `malop` INT NOT NULL,
  `dangtoanTV` VARCHAR(45) NULL,
  `dopc_de` FLOAT NULL,
  `dopc_tb` FLOAT NULL,
  `dopc_tbk` FLOAT NULL,
  `dopc_kho` FLOAT NULL,
  PRIMARY KEY (`dangtoan`));
INSERT INTO `danhgiakienthuc`.`table_phanloaidangtoan` (`dangtoan`, `malop`, `dangtoanTV`, `dopc_de`, `dopc_tb`, `dopc_tbk`, `dopc_kho`) VALUES ('hamso', '12', 'Hàm số', NULL, NULL, NULL, NULL);
INSERT INTO `danhgiakienthuc`.`table_phanloaidangtoan` (`dangtoan`, `malop`, `dangtoanTV`, `dopc_de`, `dopc_tb`, `dopc_tbk`, `dopc_kho`) VALUES ('loga', '12', 'Lũy thừa - Mũ - Logarith', NULL, NULL, NULL, NULL);
INSERT INTO `danhgiakienthuc`.`table_phanloaidangtoan` (`dangtoan`, `malop`, `dangtoanTV`, `dopc_de`, `dopc_tb`, `dopc_tbk`, `dopc_kho`) VALUES ('tichphan', '12', 'Tích phân', NULL, NULL, NULL, NULL);
INSERT INTO `danhgiakienthuc`.`table_phanloaidangtoan` (`dangtoan`, `malop`, `dangtoanTV`, `dopc_de`, `dopc_tb`, `dopc_tbk`, `dopc_kho`) VALUES ('sophuc', '12', 'Số phức', NULL, NULL, NULL, NULL);
INSERT INTO `danhgiakienthuc`.`table_phanloaidangtoan` (`dangtoan`, `malop`, `dangtoanTV`, `dopc_de`, `dopc_tb`, `dopc_tbk`, `dopc_kho`) VALUES ('hhkg', '12', 'Hình học không gian', NULL, NULL, NULL, NULL);
INSERT INTO `danhgiakienthuc`.`table_phanloaidangtoan` (`dangtoan`, `malop`, `dangtoanTV`, `dopc_de`, `dopc_tb`, `dopc_tbk`, `dopc_kho`) VALUES ('oxyz', '12', 'OXYZ', NULL, NULL, NULL, NULL);

CREATE TABLE `danhgiakienthuc`.`table_phanloaibt` (
  `dangbt` VARCHAR(45) NOT NULL,
  `dangtoan` VARCHAR(45) NULL,
  `dangbtTV` VARCHAR(45) NULL,
  PRIMARY KEY (`dangbt`));
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('nhanbiet', 'hamso', 'Nhận biết đồ thị');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('tiemcan', 'hamso', 'Tiệm cận của đồ thị');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('dondieu', 'hamso', 'Tính đơn điệu của hàm số');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('cuctri', 'hamso', 'Cực trị hàm số');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('tuongiao', 'hamso', 'Tương giao');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('gtlnnn', 'hamso', 'GTLN - GTNN');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('tieptuyen', 'hamso', 'Tiếp tuyến của đồ thị');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('ptloga', 'loga', 'Phương trình logarith');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('hsmuloga', 'loga', 'Hàm số mũ - logarith');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('bptloga', 'loga', 'Bất phương trình logarith');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('ptmu', 'loga', 'Phương trình mũ');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('thucte', 'loga', 'Bài toán thực tế');
INSERT INTO `danhgiakienthuc`.`table_phanloaibt` (`dangbt`, `dangtoan`, `dangbtTV`) VALUES ('bptmu', 'loga', 'Bất phương trình mũ');

/* CREATE FOREIGN KEY */
ALTER TABLE `danhgiakienthuc`.`table_hamso`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);

ALTER TABLE `danhgiakienthuc`.`table_loga`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);

ALTER TABLE `danhgiakienthuc`.`table_tichphan`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);

ALTER TABLE `danhgiakienthuc`.`table_sophuc`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);

ALTER TABLE `danhgiakienthuc`.`table_hhkg`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);

ALTER TABLE `danhgiakienthuc`.`table_oxyz`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);

ALTER TABLE `danhgiakienthuc`.`table_dethi`
	ADD FOREIGN KEY (`dokho`) REFERENCES `danhgiakienthuc`.`table_dokho`(`dokho`),
	ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`),
	ADD FOREIGN KEY (`dangbt`) REFERENCES `danhgiakienthuc`.`table_phanloaibt`(`dangbt`),
	ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);	

ALTER TABLE `danhgiakienthuc`.`table_phanloaidangtoan` ADD FOREIGN KEY (`malop`) REFERENCES `danhgiakienthuc`.`table_lop`(`malop`);	

ALTER TABLE `danhgiakienthuc`.`table_phanloaibt` ADD FOREIGN KEY (`dangtoan`) REFERENCES `danhgiakienthuc`.`table_phanloaidangtoan`(`dangtoan`);		