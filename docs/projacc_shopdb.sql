-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 6.4.0.1
-- Generation Time: Feb 09, 2021 at 06:47 PM
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

--
-- Dumping data for table `sys_items`
--

INSERT INTO `sys_items` (`cid`, `id`, `name`, `price`, `status`, `image`, `descr`, `shopown`) VALUES
(1, 1, '测试商品1', '22.12', 2, '/static/imgs/avatar.png', '测试商品描述1', '8ad3980a-18fe-4951-849b-a6a687aaabd8'),
(1, 2, '测试商品2', '33.55', 2, '/static/imgs/avatar.png', '测试商品描述2', '8ad3980a-18fe-4951-849b-a6a687aaabd8');

-- --------------------------------------------------------

--
-- Table structure for table `sys_item_cates`
--

CREATE TABLE `sys_item_cates` (
  `name` varchar(40) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id` int(8) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sys_item_cates`
--

INSERT INTO `sys_item_cates` (`name`, `id`) VALUES
('测试商品类别1', 1);

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
  `genTime` bigint(12) NOT NULL DEFAULT unix_timestamp(),
  `paidTime` bigint(12) DEFAULT NULL,
  `doneTime` bigint(12) DEFAULT NULL,
  `refundTime` bigint(12) DEFAULT NULL,
  `paymentId` tinyint(1) UNSIGNED NOT NULL,
  `deliveryId` tinyint(1) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `sys_orders`
--

INSERT INTO `sys_orders` (`oid`, `id`, `status`, `items`, `uid`, `finalPrice`, `genTime`, `paidTime`, `doneTime`, `refundTime`, `paymentId`, `deliveryId`) VALUES
('917decb2-6b04-11eb-8b32-b89a2afc3a6e', 1, 2, '{\"items\": [{\"itemId\": 1, \"itemNo\": 2}]}', 'a09ccf60-daba-4507-bfa0-d1688ee5f2ea', '44.24', 1612895292, NULL, NULL, NULL, 1, 1);

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
('admin', '0c33d2fd-015b-4c60-bc58-df139dc9d7b9', 1, 12345678902, '$2a$10$veUS/ypYi0uDSkHefZLg8u1P50DI7JxVH6N3SSQ3EEX5TSsOvIOuG', 'ROLE_ADMIN', '/static/imgs/avatar.png', 1, 1, 'addr123', 0),
('testuser2', '8ad3980a-18fe-4951-849b-a6a687aaabd8', 4, 12345678002, '$2a$10$.079yr9VLx.Jo0TVpNaVcuWNMdZZw5ZKu09kafPTUXyqw1riPt2VK', 'ROLE_USER,ROLE_SHOPOWNER', '/static/imgs/avatar.png', 1, 1, 'addrtest2', 0),
('testuser5', 'a09ccf60-daba-4507-bfa0-d1688ee5f2ea', 5, 12345678005, '$2a$10$OYnQjXYZaCs0XHpJ7i0GeeqCmqUsL20.izGh0sp1Yj9znrbkr22FG', 'ROLE_USER', '/static/imgs/avatar.png', 1, 1, 'addrtest5', 0);

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
-- Dumping data for table `sys_user_cart`
--

INSERT INTO `sys_user_cart` (`id`, `uid`, `items`) VALUES
(3, '8ad3980a-18fe-4951-849b-a6a687aaabd8', '{\"cart\":[]}'),
(4, 'a09ccf60-daba-4507-bfa0-d1688ee5f2ea', '{\"cart\":[{\"itemID\":1,\"itemNum\":2}]}');

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
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sys_item_cates`
--
ALTER TABLE `sys_item_cates`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sys_orders`
--
ALTER TABLE `sys_orders`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `sys_users`
--
ALTER TABLE `sys_users`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `sys_user_cart`
--
ALTER TABLE `sys_user_cart`
  MODIFY `id` int(8) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

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
