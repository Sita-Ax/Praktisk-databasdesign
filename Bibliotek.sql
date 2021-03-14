-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: localhost    Database: Bibliotek
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `anställda`
--

DROP TABLE IF EXISTS `anställda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `anställda` (
  `AnställdId` int NOT NULL AUTO_INCREMENT,
  `Namn` varchar(255) DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `Telefonnummer` int DEFAULT NULL,
  `Mobilnummer` int DEFAULT NULL,
  `Månadslön` int DEFAULT NULL,
  `Semesterdagar` int DEFAULT NULL,
  PRIMARY KEY (`AnställdId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `anställda`
--

LOCK TABLES `anställda` WRITE;
/*!40000 ALTER TABLE `anställda` DISABLE KEYS */;
INSERT INTO `anställda` VALUES (1,'Ebba_Grön','Vägen 2, 325 98 Nollberga',82655556,702555556,11000,25),(2,'Farbror_Blå','Vägen 8, 325 98 Nollberga',82659744,702598435,11000,20),(3,'Asta_Kask','Vägen 15, 325 98 Nollberga',3265313,5698127,10200,10);
/*!40000 ALTER TABLE `anställda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `böcker`
--

DROP TABLE IF EXISTS `böcker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `böcker` (
  `BokId` int NOT NULL AUTO_INCREMENT,
  `Title` varchar(255) DEFAULT NULL,
  `Författare` varchar(255) DEFAULT NULL,
  `Sidor` int DEFAULT NULL,
  `Klassifikation` varchar(255) DEFAULT NULL,
  `Utlånad` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`BokId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `böcker`
--

LOCK TABLES `böcker` WRITE;
/*!40000 ALTER TABLE `böcker` DISABLE KEYS */;
INSERT INTO `böcker` VALUES (1,'Den ensamma katten','Rudolf Ruskprick',123,'Hce',0),(2,'Vägen till Västerås','Kenny Surströmming',244,'Hce',1),(3,'Boken om java','Ola Lindqvist',555,'Hcf',1);
/*!40000 ALTER TABLE `böcker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lånadeböcker`
--

DROP TABLE IF EXISTS `lånadeböcker`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lånadeböcker` (
  `LånId` int NOT NULL AUTO_INCREMENT,
  `LåntagareId` varchar(255) DEFAULT NULL,
  `BokId` int DEFAULT NULL,
  `TidskriftsId` int DEFAULT NULL,
  `StartDatum` date DEFAULT NULL,
  `SlutDatum` date DEFAULT NULL,
  PRIMARY KEY (`LånId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lånadeböcker`
--

LOCK TABLES `lånadeböcker` WRITE;
/*!40000 ALTER TABLE `lånadeböcker` DISABLE KEYS */;
INSERT INTO `lånadeböcker` VALUES (1,'1',1,NULL,'2021-03-14','2021-03-14'),(2,'1',1,NULL,'2021-03-14','2021-03-14'),(3,'1',1,NULL,'2021-03-14','2021-03-14');
/*!40000 ALTER TABLE `lånadeböcker` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `låntagare`
--

DROP TABLE IF EXISTS `låntagare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `låntagare` (
  `LåntagareId` int NOT NULL AUTO_INCREMENT,
  `Name` varchar(255) DEFAULT NULL,
  `Adress` varchar(255) DEFAULT NULL,
  `Telefonnummer` varchar(255) DEFAULT NULL,
  `Mobilnummer` varchar(255) DEFAULT NULL,
  `Lånekortsnummer` int DEFAULT NULL,
  `Lån` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`LåntagareId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `låntagare`
--

LOCK TABLES `låntagare` WRITE;
/*!40000 ALTER TABLE `låntagare` DISABLE KEYS */;
INSERT INTO `låntagare` VALUES (1,'Viggo_Filtner','Vägen 1, 325 98 Nollberga','08-77775897','070-4564508',1234,NULL),(2,'Bosse_Baron','Vägen 5, 325 98 Nollberga','08-73259997','070-45321317',4536,NULL),(3,'Pelle_Pälsäng','Vägen 25, 325 98 Nollberga','08-7355557','070-45355557',1597,NULL);
/*!40000 ALTER TABLE `låntagare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tidskrifter`
--

DROP TABLE IF EXISTS `tidskrifter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tidskrifter` (
  `TidsskriftsId` int NOT NULL AUTO_INCREMENT,
  `Titel` varchar(255) DEFAULT NULL,
  `Utgivningsdatum` date DEFAULT NULL,
  `Hyllplats` varchar(255) DEFAULT NULL,
  `Utlånad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`TidsskriftsId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tidskrifter`
--

LOCK TABLES `tidskrifter` WRITE;
/*!40000 ALTER TABLE `tidskrifter` DISABLE KEYS */;
INSERT INTO `tidskrifter` VALUES (1,'Illusterad Ångerst','2020-12-12','Hylla A','1'),(2,'Illusterad Ångerst','1958-10-20','Hylla A','1');
/*!40000 ALTER TABLE `tidskrifter` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-14 20:55:57
