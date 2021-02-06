-- MariaDB dump 10.18  Distrib 10.5.8-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: projacc_shopdb
-- ------------------------------------------------------
-- Server version	10.5.8-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `sys_item_cates`
--

DROP TABLE IF EXISTS `sys_item_cates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_item_cates` (
  `name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_item_cates`
--

LOCK TABLES `sys_item_cates` WRITE;
/*!40000 ALTER TABLE `sys_item_cates` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_item_cates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_items`
--

DROP TABLE IF EXISTS `sys_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_items` (
  `cid` int(8) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(14,2) NOT NULL,
  `status` tinyint(1) unsigned NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descr` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shopown` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `shopown` (`shopown`),
  KEY `itemidx` (`name`),
  KEY `sys_items_ibfk_1` (`cid`),
  CONSTRAINT `sys_items_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `sys_item_cates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sys_items_ibfk_2` FOREIGN KEY (`shopown`) REFERENCES `sys_users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_items`
--

LOCK TABLES `sys_items` WRITE;
/*!40000 ALTER TABLE `sys_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_orders`
--

DROP TABLE IF EXISTS `sys_orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_orders` (
  `oid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid(),
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `status` tinyint(1) unsigned NOT NULL,
  `items` varchar(5000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `finalPrice` decimal(14,2) NOT NULL,
  `genTime` timestamp NOT NULL DEFAULT unix_timestamp(),
  `paidTime` timestamp NULL DEFAULT NULL,
  `doneTime` timestamp NULL DEFAULT NULL,
  `refundTime` timestamp NULL DEFAULT NULL,
  `paymentId` tinyint(1) unsigned NOT NULL,
  `deliveryId` tinyint(1) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `oid` (`oid`),
  UNIQUE KEY `orders_uid` (`oid`,`uid`),
  KEY `uid` (`uid`),
  CONSTRAINT `sys_orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_orders`
--

LOCK TABLES `sys_orders` WRITE;
/*!40000 ALTER TABLE `sys_orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_user_cart`
--

DROP TABLE IF EXISTS `sys_user_cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_user_cart` (
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `uid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `items` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uid` (`uid`),
  KEY `uididx` (`uid`),
  CONSTRAINT `sys_user_cart_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_user_cart`
--

LOCK TABLES `sys_user_cart` WRITE;
/*!40000 ALTER TABLE `sys_user_cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_user_cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_users`
--

DROP TABLE IF EXISTS `sys_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_users` (
  `username` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid(),
  `id` int(8) unsigned NOT NULL AUTO_INCREMENT,
  `phone` bigint(11) NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(24) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `preferPayment` tinyint(1) unsigned NOT NULL,
  `preferDelivery` tinyint(1) unsigned NOT NULL,
  `addr` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(2) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `uid` (`uid`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_users`
--

LOCK TABLES `sys_users` WRITE;
/*!40000 ALTER TABLE `sys_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-06 18:59:30
