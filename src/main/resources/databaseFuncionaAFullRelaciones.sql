CREATE DATABASE  IF NOT EXISTS `pom_hotel` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pom_hotel`;
-- MySQL dump 10.13  Distrib 8.0.21, for macos10.15 (x86_64)
--
-- Host: localhost    Database: pom_hotel
-- ------------------------------------------------------
-- Server version	8.0.21

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bookings`
--

DROP TABLE IF EXISTS `bookings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookings` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_client_id` bigint DEFAULT NULL,
  `fk_room_id` bigint DEFAULT NULL,
  `checkIn` date DEFAULT NULL,
  `checkOut` date DEFAULT NULL,
  `totalPrice` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjh7gwgups4i54ig355hkw77hv` (`fk_room_id`),
  KEY `FKa9u356h5iacb5dvedqqitc2es` (`fk_client_id`),
  CONSTRAINT `bookings_ibfk_2` FOREIGN KEY (`fk_client_id`) REFERENCES `CLIENTS` (`id`),
  CONSTRAINT `FKa9u356h5iacb5dvedqqitc2es` FOREIGN KEY (`fk_client_id`) REFERENCES `clients` (`id`),
  CONSTRAINT `FKjh7gwgups4i54ig355hkw77hv` FOREIGN KEY (`fk_room_id`) REFERENCES `ROOMS` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookings`
--

LOCK TABLES `bookings` WRITE;
/*!40000 ALTER TABLE `bookings` DISABLE KEYS */;
INSERT INTO `bookings` VALUES (1,1,1,'2020-09-10','2020-09-14',400),(2,1,2,'2020-10-10','2020-10-14',200);
/*!40000 ALTER TABLE `bookings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CLIENTS`
--

DROP TABLE IF EXISTS `CLIENTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `CLIENTS` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `lastname` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTS`
--

LOCK TABLES `CLIENTS` WRITE;
/*!40000 ALTER TABLE `CLIENTS` DISABLE KEYS */;
INSERT INTO `CLIENTS` VALUES (1,'Pablo','Garcia','Garcia@seat.es'),(2,'Oscar','Garcia','OGarcia@seat.com');
/*!40000 ALTER TABLE `CLIENTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logins`
--

DROP TABLE IF EXISTS `logins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logins` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `password` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `clientsByFkClientId_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `UK_kpsgg434guljkbty611itckww` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logins`
--

LOCK TABLES `logins` WRITE;
/*!40000 ALTER TABLE `logins` DISABLE KEYS */;
INSERT INTO `logins` VALUES (1,'Garcia1989','1234',1),(2,'Oscar2000','1234',2);
/*!40000 ALTER TABLE `logins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preferences`
--

DROP TABLE IF EXISTS `preferences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `preferences` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `fk_roomtype_id` bigint DEFAULT NULL,
  `priceLastSearch` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKtitdfkve3648jjlcs7s9aix7j` (`fk_roomtype_id`),
  CONSTRAINT `FKtitdfkve3648jjlcs7s9aix7j` FOREIGN KEY (`fk_roomtype_id`) REFERENCES `roomtypes` (`id`),
  CONSTRAINT `preferences_ibfk_1` FOREIGN KEY (`fk_roomtype_id`) REFERENCES `ROOMTYPES` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preferences`
--

LOCK TABLES `preferences` WRITE;
/*!40000 ALTER TABLE `preferences` DISABLE KEYS */;
INSERT INTO `preferences` VALUES (1,1,300),(2,2,200);
/*!40000 ALTER TABLE `preferences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rooms`
--

DROP TABLE IF EXISTS `rooms`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rooms` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `description` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  `pricePerNight` double DEFAULT NULL,
  `image` blob,
  `fk_roomtype_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqmfnmxw6769a5kee84i0a83jn` (`fk_roomtype_id`),
  CONSTRAINT `FKqmfnmxw6769a5kee84i0a83jn` FOREIGN KEY (`fk_roomtype_id`) REFERENCES `roomtypes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rooms`
--

LOCK TABLES `rooms` WRITE;
/*!40000 ALTER TABLE `rooms` DISABLE KEYS */;
INSERT INTO `rooms` VALUES (1,'S1','Habitación grande con jacuzzi',100,NULL,1),(2,'IN1','Habitación pequeña con lavabo',50,NULL,2);
/*!40000 ALTER TABLE `rooms` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ROOMTYPES`
--

DROP TABLE IF EXISTS `ROOMTYPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ROOMTYPES` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `description` varchar(200) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ROOMTYPES`
--

LOCK TABLES `ROOMTYPES` WRITE;
/*!40000 ALTER TABLE `ROOMTYPES` DISABLE KEYS */;
INSERT INTO `ROOMTYPES` VALUES (1,'Suite','Habitación grande con jacuzzi'),(2,'Individual','Habitación pequeña con lavabo');
/*!40000 ALTER TABLE `ROOMTYPES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-17 13:58:32
