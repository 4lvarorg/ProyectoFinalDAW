-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: helpmybrain
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `cita`
--

DROP TABLE IF EXISTS `cita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cita` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha_reservada` date DEFAULT NULL,
  `hora_reservada` time DEFAULT NULL,
  `precio_final` double DEFAULT NULL,
  `psicologo_id` int DEFAULT NULL,
  `usuario_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKb2b9jna1rju8mphfebalfkfs8` (`psicologo_id`),
  KEY `FKb3l8n6flyfg5uxsvimq1idnt8` (`usuario_id`),
  CONSTRAINT `FKb2b9jna1rju8mphfebalfkfs8` FOREIGN KEY (`psicologo_id`) REFERENCES `psicologo` (`id`),
  CONSTRAINT `FKb3l8n6flyfg5uxsvimq1idnt8` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cita`
--

LOCK TABLES `cita` WRITE;
/*!40000 ALTER TABLE `cita` DISABLE KEYS */;
INSERT INTO `cita` (`id`, `fecha_reservada`, `hora_reservada`, `precio_final`, `psicologo_id`, `usuario_id`) VALUES (34,'2022-05-31','21:00:00',44,1,27),(35,'2025-02-02','22:00:00',2,12,28),(36,'2023-01-01','22:00:00',2,12,28),(37,'2023-04-04','22:00:00',2,12,28),(38,'2025-06-12','22:00:00',2,12,28),(39,'2023-09-01','22:00:00',2,12,28),(40,'2026-02-08','22:00:00',1.6,12,28),(41,'2023-06-23','21:00:00',44,1,28),(42,'2023-08-15','21:00:00',10,8,28),(43,'2023-04-23','21:00:00',44,1,28),(44,'2023-09-01','21:00:00',44,1,28),(45,'2023-08-30','22:00:00',1.6,12,28),(46,'2030-06-11','21:00:00',44,1,28),(47,'2034-05-31','21:00:00',44,1,28),(48,'2023-09-12','21:00:00',10,8,28);
/*!40000 ALTER TABLE `cita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `psicologo`
--

DROP TABLE IF EXISTS `psicologo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `psicologo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `codi_colegiado` int DEFAULT NULL,
  `codigo_postal` int DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fecha_disponible` date DEFAULT NULL,
  `hora_disponible` time DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `precio_por_hora` double DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_epn1ttpu1t0wpjuy5y5u3mfj4` (`codi_colegiado`),
  UNIQUE KEY `UK_rrk9oombmwd4pi3hps8g4w0lo` (`email`),
  UNIQUE KEY `UK_1omhkc4bqbv20s8hgp31jicik` (`telefono`),
  KEY `FK9nw19qwjbihra1ijsn9tubt54` (`role_id`),
  CONSTRAINT `FK9nw19qwjbihra1ijsn9tubt54` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `psicologo`
--

LOCK TABLES `psicologo` WRITE;
/*!40000 ALTER TABLE `psicologo` DISABLE KEYS */;
INSERT INTO `psicologo` (`id`, `apellido`, `codi_colegiado`, `codigo_postal`, `direccion`, `email`, `fecha_disponible`, `hora_disponible`, `nombre`, `password`, `precio_por_hora`, `telefono`, `role_id`) VALUES (1,'domingo',12345,123456,'madrid','domingo@gmail.com','2023-06-17','21:00:00','pepe','$2a$12$7BR.B9Y6L2aP8cd0oljk/.BTHVttp8VZsl5JBqBzzsAHLUbie9VDm',44,622525252,3),(8,'lamama',1234,11111,'albacete','lamama@lamama.com','2023-08-28','21:00:00','juanma','$2a$12$hirauTEcjJ9dbxdwjeVnE.BP8wiimwG3fNdoBmTwlHoJv.LxjXm/G',10,652202020,3),(12,'fermin',2323,2131231,'adasda','unico@unico.com','2023-06-26','22:00:00','femin','$2a$12$hirauTEcjJ9dbxdwjeVnE.BP8wiimwG3fNdoBmTwlHoJv.LxjXm/G',2,654646456,3);
/*!40000 ALTER TABLE `psicologo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_q7ga63j9sppysgm0en52iemyb` (`nombre`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` (`id`, `nombre`) VALUES (1,'ADMIN'),(3,'PSICOLOGO'),(2,'USUARIO');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `apellido` varchar(255) DEFAULT NULL,
  `codigo_postal` int DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `telefono` int DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5171l57faosmj8myawaucatdw` (`email`),
  UNIQUE KEY `UK_lyn4jrsa2ou2meyuarytj8tcc` (`telefono`),
  KEY `FK3qieopaxyoowdge29v1m51wr9` (`role_id`),
  CONSTRAINT `FK3qieopaxyoowdge29v1m51wr9` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `apellido`, `codigo_postal`, `direccion`, `email`, `nombre`, `password`, `telefono`, `role_id`) VALUES (1,'admin',111111,'admin','admin@admin.com','admin','$2a$12$CrDniOHP5crT/vlTXwVaNeMxZyRQPwmZ70Z3IrgET3yT7buAZkHwi',611557700,1),(27,'AsomaLaCabeza',56000,'cabezon','antonio@anton.com','Anton',NULL,686989699,2),(28,'hola',1234,'cucu','hola@hola.com','hola',NULL,923252525,2);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-27 23:35:35
