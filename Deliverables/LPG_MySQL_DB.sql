-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: lpgdb
-- ------------------------------------------------------
-- Server version	8.0.19

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
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patient` (
  `patient_id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `middle_name` varchar(45) DEFAULT NULL,
  `dob` varchar(12) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `gender` char(1) DEFAULT NULL,
  `race` varchar(45) DEFAULT NULL,
  `physician` varchar(45) DEFAULT NULL,
  `insurance_provider` varchar(100) DEFAULT NULL,
  `insurance_phone` varchar(45) DEFAULT NULL,
  `insurance_policy` varchar(9) DEFAULT NULL,
  `ssn` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`patient_id`),
  UNIQUE KEY `patient_id_UNIQUE` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (13,'Sarah','Conners','M','1960-02-20','2499 Terminator Way\nSan Antonio, TX 78414','(555) 633-7226','robothunter60@email.com','F','Caucasian','Dr. Palapa','4 Ever Insurance','(555) 123-8394','439820-03','124-12-1234'),(14,'Joshua','Dogooder','S','1972-11-13','73829 Deeds St\nSan Antonio, TX 78251','(555) 839-9936','goodthings@email.com','M','Caucasian','Dr. Mancy','All Coverage','(555) 999-0099','004983-01','890-89-8901'),(15,'Melissa','Grap','A','1948-07-01','111 Point Rd\nUniversal City, TX 78823','(555) 839-8392','mgrap1@email.com','F','Caucasian','Dr. Palapa','First Insurance','(555) 321-0982','948873-46','487-38-2966'),(16,'Ivy','Sternengrad','C','1981-03-17','93840 Scottish Ln\nSan Antonio, TX 78252','(555) 474-2266','ivyknowsall@email.com','F','Caucasian','Dr. Palapa','All Coverage','(555) 999-0099','111827-11','124-12-1234'),(17,'Jimmy','Space','J','1969-12-07','9340 Circle Cv\nSan Antonio, TX 78443','(555) 899-3392','jimmyouterspace@email.com','M','Asian-American','Dr. Draft','WeCare','(555) 898-2323','345533-33','348-73-6001'),(18,'Alexandria','Permany','A','1955-09-20','2822 Lincoln Ave\nSan Antonio, TX 78150','(555) 373-7700','permanentlyalex','F','African-American','Dr. Mancy','4 Ever Insurance','(555) 123-8394','423423-77','466-01-3591'),(19,'Lauren','O\'connel','S','1978-06-29','12111 Cinder Block St\nSan Antonio, TX 78443','(555) 239-6587','omyemail@email.com','F','Caucasian','Dr. Draft','All Coverage','(555) 999-0099','232272-00','456-65-0987'),(20,'Jasper','Williams','A','1944-01-02','893 North Stark St\nSan Antonio, TX 78443','(555) 228-2288','williamsja@email.com','M','African-American','Dr. Palapa','4 Ever Insurance','(555) 123-8394','987362-93','838-83-9393'),(21,'Sarah','Turn','Q','1955-04-20','3933 Uplift Way\nSan Antonio, TX 78443','(555) 838-8882','turnthisway@email.com','F','Caucasian','Dr. Mancy','WeCare','(555) 898-2323','345834-23','773-21-9211'),(22,'Roman','Pierce','R','1980-02-14','1111 Speed Demon Dr\nSan Antonio, TX 78443','(555) 242-2556','notthatfast@email.com','M','African-American','Dr. Palapa','All Coverage','(555) 999-0099','345627-01','937-64-4564'),(23,'Julia','Gulia','F','1978-10-18','3782 Singing St\nSan Antonio, TX 78253','(555) 556-0789','justawaitress@email.com','F','Caucasian','Dr. Draft','WeCaree','(555) 898-2323','132824-00','468-97-1357'),(24,'Janet','Reno','S','1966-03-18','32144 Hollywood Blvd\nSan Antonio, TX 78434','(555) 748-1212','janetreno@email.com','F','Caucasian','Dr. Palapa','All Coverage','(555) 999-0099','111222-01','221-99-5031'),(25,'Jasmine','Drudge','T','1975-09-20','4344 End Way\nBoerne, TX 78832','(555) 898-0264','jdt1975@email.com','F','African-American','Dr. Palapa','WeCare','(555) 898-2323','494949-00','949-02-8778'),(26,'Josh','Goodwin','T','1980-11-18','1233 Aver Cv\nSan Antonio, TX 78155','(555) 432-4322','joshua.goodwin@college.edu','M','Caucasian','Dr. Palapa','#1 Insurance','(555) 210-0011','949038-00','444-44-4444'),(27,'Rod','Farva','M','1973-04-08','2001 Trooper Ln\nSan Antonio, TX 78545','(555) 858-2929','suptroop@funny.com','M','Caucasion','Dr. Mancy','Famous Insurance','(555) 484-4444','908909-88','373-77-8974');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wellness`
--

DROP TABLE IF EXISTS `wellness`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wellness` (
  `patient_id` int NOT NULL,
  `date` varchar(10) NOT NULL,
  `time` varchar(8) NOT NULL,
  `bp_diastolic` varchar(3) DEFAULT NULL,
  `bp_systolic` varchar(3) DEFAULT NULL,
  `weight` varchar(3) DEFAULT NULL,
  `height` varchar(3) DEFAULT NULL,
  `current_meds` varchar(100) DEFAULT NULL,
  `reason` varchar(100) DEFAULT NULL,
  `notes` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`date`,`time`,`patient_id`),
  KEY `patient_id_idx` (`patient_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `patient_id_FK` FOREIGN KEY (`patient_id`) REFERENCES `patient` (`patient_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wellness`
--

LOCK TABLES `wellness` WRITE;
/*!40000 ALTER TABLE `wellness` DISABLE KEYS */;
INSERT INTO `wellness` VALUES (13,'2019-04-03','10:00:00','70','113','163','66','lortab, ibuprofen','Routine','none'),(14,'2019-07-09','11:00:00','63','98','180','70','none','Routine','none'),(19,'2019-09-01','10:00:00','68','102','115','64','','Routine',''),(13,'2019-10-12','14:00:00','77','118','165','66','lortab, ibuprofen','Routine','Watch blood pressure'),(14,'2020-01-13','08:00:00','64','100','181','70','none','Routine','none'),(19,'2020-02-19','14:45:00','75','103','118','64','','Routine','6 weeks pregnant; smokes'),(18,'2020-03-26','11:00:00','68','105','198','78','null','Routine',NULL),(13,'2020-04-01','09:30:00','72','108','155','66','lortab, ibuprofen','Routine','none'),(14,'2020-04-22','15:45:00','62','101','178','70','none','Unscheduled','COVID-19 symptoms; test negative');
/*!40000 ALTER TABLE `wellness` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-26 21:45:32
