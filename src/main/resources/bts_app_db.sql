-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: bts_app_db
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.18.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cell_bts`
--

DROP TABLE IF EXISTS `cell_bts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cell_bts` (
  `LAT_CELL_BTS` varchar(255) NOT NULL,
  `LONG_CELL_BTS` varchar(255) NOT NULL,
  `ID_CELL_BTS` varchar(25) DEFAULT NULL,
  `RADIUS_CELL_BTS` int(11) DEFAULT NULL,
  `ID_KECAMATAN` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`LAT_CELL_BTS`,`LONG_CELL_BTS`),
  UNIQUE KEY `ID_CELL_BTS` (`ID_CELL_BTS`),
  KEY `ID_KECAMATAN` (`ID_KECAMATAN`),
  CONSTRAINT `cell_bts_ibfk_1` FOREIGN KEY (`ID_KECAMATAN`) REFERENCES `kecamatan` (`ID_KECAMATAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cell_bts`
--

LOCK TABLES `cell_bts` WRITE;
/*!40000 ALTER TABLE `cell_bts` DISABLE KEYS */;
/*!40000 ALTER TABLE `cell_bts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kecamatan`
--

DROP TABLE IF EXISTS `kecamatan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kecamatan` (
  `ID_KECAMATAN` varchar(25) NOT NULL,
  `NAMA_KECAMATAN` varchar(1000) NOT NULL,
  PRIMARY KEY (`ID_KECAMATAN`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kecamatan`
--

LOCK TABLES `kecamatan` WRITE;
/*!40000 ALTER TABLE `kecamatan` DISABLE KEYS */;
/*!40000 ALTER TABLE `kecamatan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pengajuan`
--

DROP TABLE IF EXISTS `pengajuan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pengajuan` (
  `id_pengajuan` bigint(19) NOT NULL,
  `user_id` varchar(25) NOT NULL,
  `scan_npwpd_file` text,
  `dokumen_pengajuan` text,
  `id_kecamatan` varchar(25) DEFAULT NULL,
  `kecamatan` varchar(1000) DEFAULT NULL,
  `latitude_ajuan` varchar(255) DEFAULT NULL,
  `longitude_ajuan` varchar(255) DEFAULT NULL,
  `status` int(5) DEFAULT NULL,
  `status_aktif` int(5) DEFAULT NULL,
  PRIMARY KEY (`id_pengajuan`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `pengajuan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pengajuan`
--

LOCK TABLES `pengajuan` WRITE;
/*!40000 ALTER TABLE `pengajuan` DISABLE KEYS */;
INSERT INTO `pengajuan` VALUES (1545267796879,'1545192899425','1545267796879.pdf','1545267796879.docx','001','Babelan','1010','0101',1,0);
/*!40000 ALTER TABLE `pengajuan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `perusahaan`
--

DROP TABLE IF EXISTS `perusahaan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `perusahaan` (
  `user_id` varchar(25) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nama_pendaftar` varchar(500) DEFAULT NULL,
  `nama_perusahaan` varchar(500) DEFAULT NULL,
  `alamat_perusahaan` text,
  `no_telp` varchar(25) DEFAULT NULL,
  `npwpd` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `perusahaan_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `perusahaan`
--

LOCK TABLES `perusahaan` WRITE;
/*!40000 ALTER TABLE `perusahaan` DISABLE KEYS */;
INSERT INTO `perusahaan` VALUES ('1545192899425','jatikusuma0801@gmail.com','Bagus Jatikusuma','L LAWLIET','Jl. Jalan','0227212051','P-09988 9898989');
/*!40000 ALTER TABLE `perusahaan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` varchar(25) NOT NULL,
  `password` varchar(500) NOT NULL,
  `role` int(5) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1545192899425','$2a$10$JCeqJ4jC02/oFsTlxXJpy.JnHg3jrBSmc1L1PhLjTlR0SNtDKt136',1),('Admin','$2a$10$r75i5n1jp.ieGbhuwHTl2.OjG1NrXkxrVUmRPPatbjhmiZ8SRRcQS',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-20  9:54:24
