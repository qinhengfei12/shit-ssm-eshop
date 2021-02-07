-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 6.4.0.1
-- Generation Time: Feb 07, 2021 at 09:30 AM
-- Server version: 10.5.8-MariaDB
-- PHP Version: 7.4.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `projacc_shopdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `sys_items`
--

CREATE TABLE `sys_items` (
  `cid` int(8) UNSIGNED NOT NULL,
  `id` int(10) UNSIGNED NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` decimal(14,2) NOT NULL,
  `status` tinyint(1) UNSIGNED NOT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descr` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `shopown` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sys_item_cates`
--

CREATE TABLE `sys_item_cates` (
  `name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(8) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sys_orders`
--

CREATE TABLE `sys_orders` (
  `oid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid(),
  `id` int(8) UNSIGNED NOT NULL,
  `status` tinyint(1) UNSIGNED NOT NULL,
  `items` varchar(5000) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `finalPrice` decimal(14,2) NOT NULL,
  `genTime` timestamp NOT NULL DEFAULT unix_timestamp(),
  `paidTime` timestamp NULL DEFAULT NULL,
  `doneTime` timestamp NULL DEFAULT NULL,
  `refundTime` timestamp NULL DEFAULT NULL,
  `paymentId` tinyint(1) UNSIGNED NOT NULL,
  `deliveryId` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `sys_users`
--

CREATE TABLE `sys_users` (
  `username` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL,
  `uid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT uuid(),
  `id` int(8) UNSIGNED NOT NULL,
  `phone` bigint(11) NOT NULL,
  `password` varchar(100) COLLATE utf8mb4_unicode_ci NOT NULL,
  `role` varchar(24) COLLATE utf8mb4_unicode_ci NOT NULL,
  `avatar` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `preferPayment` tinyint(1) UNSIGNED NOT NULL,
  `preferDelivery` tinyint(1) UNSIGNED NOT NULL,
  `addr` varchar(120) COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` tinyint(2) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sys_users`
--

INSERT INTO `sys_users` (`username`, `uid`, `id`, `phone`, `password`, `role`, `avatar`, `preferPayment`, `preferDelivery`, `addr`, `status`) VALUES
('admin', '0c33d2fd-015b-4c60-bc58-df139dc9d7b9', 1, 12345678902, '$2a$10$veUS/ypYi0uDSkHefZLg8u1P50DI7JxVH6N3SSQ3EEX5TSsOvIOuG', 'ROLE_ADMIN', '/static/imgs/avatar.png', 1, 1, 'addr123', 0);

-- --------------------------------------------------------

--
-- Table structure for table `sys_user_cart`
--

CREATE TABLE `sys_user_cart` (
  `id` int(8) UNSIGNED NOT NULL,
  `uid` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `items` varchar(5000) COLLATE utf8mb4_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `sys_items`
--
ALTER TABLE `sys_items`
  ADD PRIMARY KEY (`id`),
  ADD KEY `shopown` (`shopown`),
  ADD KEY `itemidx` (`name`),
  ADD KEY `sys_items_ibfk_1` (`cid`);

--
-- Indexes for table `sys_item_cates`
--
ALTER TABLE `sys_item_cates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `sys_orders`
--
ALTER TABLE `sys_orders`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `oid` (`oid`),
  ADD UNIQUE KEY `orders_uid` (`oid`,`uid`),
  ADD KEY `uid` (`uid`);

--
-- Indexes for table `sys_users`
--
ALTER TABLE `sys_users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `uid` (`uid`),
  ADD UNIQUE KEY `phone` (`phone`);

--
-- Indexes for table `sys_user_cart`
--
ALTER TABLE `sys_user_cart`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uid` (`uid`),
  ADD KEY `uididx` (`uid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `sys_items`
--
ALTER TABLE `sys_items`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sys_item_cates`
--
ALTER TABLE `sys_item_cates`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sys_orders`
--
ALTER TABLE `sys_orders`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `sys_users`
--
ALTER TABLE `sys_users`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sys_user_cart`
--
ALTER TABLE `sys_user_cart`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `sys_items`
--
ALTER TABLE `sys_items`
  ADD CONSTRAINT `sys_items_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `sys_item_cates` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `sys_items_ibfk_2` FOREIGN KEY (`shopown`) REFERENCES `sys_users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sys_orders`
--
ALTER TABLE `sys_orders`
  ADD CONSTRAINT `sys_orders_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `sys_user_cart`
--
ALTER TABLE `sys_user_cart`
  ADD CONSTRAINT `sys_user_cart_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_users` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
