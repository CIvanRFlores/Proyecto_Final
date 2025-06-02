-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_Restaurante_El_Manglar
-- ------------------------------------------------------
-- Server version	8.0.42-0ubuntu0.22.04.1

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
-- Table structure for table `Client`
--

DROP TABLE IF EXISTS `Client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Client` (
  `id_client` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `phone_number` int NOT NULL,
  `address_1` varchar(50) NOT NULL,
  `address_2` varchar(50) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `postal_code` int DEFAULT NULL,
  PRIMARY KEY (`id_client`),
  UNIQUE KEY `phone_number` (`phone_number`),
  CONSTRAINT `Client_chk_1` CHECK ((`postal_code` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Client`
--

LOCK TABLES `Client` WRITE;
/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dish`
--

DROP TABLE IF EXISTS `Dish`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dish` (
  `id_dish` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(50) DEFAULT NULL,
  `stock` int NOT NULL,
  `image` varchar(100) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `dish_type` tinyint(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id_dish`),
  UNIQUE KEY `name` (`name`),
  CONSTRAINT `Dish_chk_1` CHECK ((`price` >= 0)),
  CONSTRAINT `Dish_chk_2` CHECK ((`stock` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dish`
--

LOCK TABLES `Dish` WRITE;
/*!40000 ALTER TABLE `Dish` DISABLE KEYS */;
/*!40000 ALTER TABLE `Dish` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dish_Ingredients`
--

DROP TABLE IF EXISTS `Dish_Ingredients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dish_Ingredients` (
  `id_ingredient` int NOT NULL,
  `id_dish` int NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`id_ingredient`,`id_dish`),
  KEY `id_dish` (`id_dish`),
  CONSTRAINT `Dish_Ingredients_ibfk_1` FOREIGN KEY (`id_ingredient`) REFERENCES `Inventory` (`id_ingredient`),
  CONSTRAINT `Dish_Ingredients_ibfk_2` FOREIGN KEY (`id_dish`) REFERENCES `Dish` (`id_dish`),
  CONSTRAINT `Dish_Ingredients_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dish_Ingredients`
--

LOCK TABLES `Dish_Ingredients` WRITE;
/*!40000 ALTER TABLE `Dish_Ingredients` DISABLE KEYS */;
/*!40000 ALTER TABLE `Dish_Ingredients` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Dish_Orders`
--

DROP TABLE IF EXISTS `Dish_Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Dish_Orders` (
  `id_order` int NOT NULL,
  `id_dish` int NOT NULL,
  `quantity` double NOT NULL,
  PRIMARY KEY (`id_order`,`id_dish`),
  KEY `id_dish` (`id_dish`),
  CONSTRAINT `Dish_Orders_ibfk_1` FOREIGN KEY (`id_order`) REFERENCES `Order` (`id_order`),
  CONSTRAINT `Dish_Orders_ibfk_2` FOREIGN KEY (`id_dish`) REFERENCES `Dish` (`id_dish`),
  CONSTRAINT `Dish_Orders_chk_1` CHECK ((`quantity` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Dish_Orders`
--

LOCK TABLES `Dish_Orders` WRITE;
/*!40000 ALTER TABLE `Dish_Orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `Dish_Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Employee`
--

DROP TABLE IF EXISTS `Employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Employee` (
  `id_employee` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`id_employee`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Employee`
--

LOCK TABLES `Employee` WRITE;
/*!40000 ALTER TABLE `Employee` DISABLE KEYS */;
INSERT INTO `Employee` VALUES (1,'jonasoto','1234'),(2,'sandoval','5678'),(3,'angelmv','9101112'),(4,'luismig','13141516'),(5,'buereke','17181920'),(6,'civan','21222324');
/*!40000 ALTER TABLE `Employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Inventory`
--

DROP TABLE IF EXISTS `Inventory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Inventory` (
  `id_ingredient` int NOT NULL AUTO_INCREMENT,
  `code_ingredient` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `ammount` int NOT NULL,
  PRIMARY KEY (`id_ingredient`),
  UNIQUE KEY `code_ingredient` (`code_ingredient`),
  CONSTRAINT `Inventory_chk_1` CHECK ((`ammount` >= 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Inventory`
--

LOCK TABLES `Inventory` WRITE;
/*!40000 ALTER TABLE `Inventory` DISABLE KEYS */;
/*!40000 ALTER TABLE `Inventory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Order`
--

DROP TABLE IF EXISTS `Order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Order` (
  `id_order` int NOT NULL AUTO_INCREMENT,
  `id_dish` int NOT NULL,
  `id_client` int NOT NULL,
  `table_number` varchar(5) NOT NULL,
  `price` double NOT NULL,
  `ammount` int NOT NULL,
  `order_type` tinyint(1) NOT NULL DEFAULT '1',
  `estimated_time` int DEFAULT NULL,
  PRIMARY KEY (`id_order`),
  KEY `id_dish` (`id_dish`),
  KEY `id_client` (`id_client`),
  CONSTRAINT `Order_ibfk_1` FOREIGN KEY (`id_dish`) REFERENCES `Dish` (`id_dish`),
  CONSTRAINT `Order_ibfk_2` FOREIGN KEY (`id_client`) REFERENCES `Client` (`id_client`),
  CONSTRAINT `Order_chk_1` CHECK ((`price` >= 0)),
  CONSTRAINT `Order_chk_2` CHECK ((`ammount` > 0))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Order`
--

LOCK TABLES `Order` WRITE;
/*!40000 ALTER TABLE `Order` DISABLE KEYS */;
/*!40000 ALTER TABLE `Order` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-01 19:58:23
