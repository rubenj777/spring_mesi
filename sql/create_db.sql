-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: mesi
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `app_order`
--

DROP TABLE IF EXISTS `app_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_order` (
                             `idOrder` int NOT NULL AUTO_INCREMENT,
                             `orderDate` date DEFAULT NULL,
                             `total` decimal(15,2) DEFAULT NULL,
                             `idUser` int NOT NULL,
                             PRIMARY KEY (`idOrder`),
                             KEY `idUser` (`idUser`),
                             CONSTRAINT `app_order_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `app_user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `app_user`
--

DROP TABLE IF EXISTS `app_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `app_user` (
                            `idUser` int NOT NULL AUTO_INCREMENT,
                            `lastName` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `firstName` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `username` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `password` varchar(500) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `email` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            `subscriptionDate` date DEFAULT NULL,
                            PRIMARY KEY (`idUser`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category` (
                            `idCategory` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                            PRIMARY KEY (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
                           `idComment` int NOT NULL AUTO_INCREMENT,
                           `comment` varchar(2000) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                           `rating` int DEFAULT NULL,
                           `idUser` int NOT NULL,
                           `idProduct` int NOT NULL,
                           PRIMARY KEY (`idComment`),
                           KEY `idUser` (`idUser`),
                           KEY `idProduct` (`idProduct`),
                           CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `app_user` (`idUser`),
                           CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
                           `idMessage` int NOT NULL AUTO_INCREMENT,
                           `content` varchar(8000) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                           `idUser` int NOT NULL,
                           `idUser1` int NOT NULL,
                           PRIMARY KEY (`idMessage`),
                           KEY `idUser` (`idUser`),
                           KEY `idUser1` (`idUser1`),
                           CONSTRAINT `message_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `app_user` (`idUser`),
                           CONSTRAINT `message_ibfk_2` FOREIGN KEY (`idUser1`) REFERENCES `app_user` (`idUser`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order_products`
--

DROP TABLE IF EXISTS `order_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_products` (
                                  `idProduct` int NOT NULL,
                                  `idOrder` int NOT NULL,
                                  PRIMARY KEY (`idProduct`,`idOrder`),
                                  KEY `idOrder` (`idOrder`),
                                  CONSTRAINT `order_products_ibfk_1` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`),
                                  CONSTRAINT `order_products_ibfk_2` FOREIGN KEY (`idOrder`) REFERENCES `app_order` (`idOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
                           `idProduct` int NOT NULL AUTO_INCREMENT,
                           `name` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                           `price` decimal(15,2) DEFAULT NULL,
                           `description` varchar(2000) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                           `idUser` int NOT NULL,
                           `idCategory` int NOT NULL,
                           PRIMARY KEY (`idProduct`),
                           KEY `idUser` (`idUser`),
                           KEY `idCategory` (`idCategory`),
                           CONSTRAINT `product_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `app_user` (`idUser`),
                           CONSTRAINT `product_ibfk_2` FOREIGN KEY (`idCategory`) REFERENCES `category` (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
                        `idRole` int NOT NULL AUTO_INCREMENT,
                        `name` varchar(50) COLLATE utf8mb3_unicode_ci DEFAULT NULL,
                        PRIMARY KEY (`idRole`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_products`
--

DROP TABLE IF EXISTS `user_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_products` (
                                 `idUser` int NOT NULL,
                                 `idProduct` int NOT NULL,
                                 PRIMARY KEY (`idUser`,`idProduct`),
                                 KEY `idProduct` (`idProduct`),
                                 CONSTRAINT `user_products_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `app_user` (`idUser`),
                                 CONSTRAINT `user_products_ibfk_2` FOREIGN KEY (`idProduct`) REFERENCES `product` (`idProduct`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
                              `idUser` int NOT NULL,
                              `idRole` int NOT NULL,
                              PRIMARY KEY (`idUser`,`idRole`),
                              KEY `idRole` (`idRole`),
                              CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`idUser`) REFERENCES `app_user` (`idUser`),
                              CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`idRole`) REFERENCES `role` (`idRole`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-12  9:49:23