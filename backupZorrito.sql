/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: abarrote_zorro
-- ------------------------------------------------------
-- Server version	10.11.11-MariaDB

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
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  UNIQUE KEY `correo` (`correo`),
  UNIQUE KEY `telefono` (`telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES
(1,'Jorge Luis','jorge12@gmail.com','5523247886'),
(2,'Atenea Marta','anthonygutierrez16000@gmail.com','5598456734'),
(3,'Ana Cecilia','ana123@gmail.com','5514230987'),
(5,'Bergolio','papadeath@yahoo.com','5567452312'),
(8,'Andrea Moreira','makotoalfaro15122019@gmail.com','5554789636'),
(11,'Luis Vazquez','luisyuyots@gmail.com','5524237085'),
(12,'Norma Rosales','normarosalesleon055@gmail.com','5511366346');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `corte_inventario`
--

DROP TABLE IF EXISTS `corte_inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `corte_inventario` (
  `id_corte` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `tipo` enum('inicio','fin') NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  PRIMARY KEY (`id_corte`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `corte_inventario_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `corte_inventario`
--

LOCK TABLES `corte_inventario` WRITE;
/*!40000 ALTER TABLE `corte_inventario` DISABLE KEYS */;
INSERT INTO `corte_inventario` VALUES
(1,'2025-05-27','inicio',2,1000),
(2,'2025-05-27','inicio',10,400),
(3,'2025-05-27','inicio',11,200),
(4,'2025-05-27','inicio',12,300),
(5,'2025-05-27','inicio',13,400),
(6,'2025-05-27','fin',12,280),
(7,'2025-05-27','fin',2,999),
(8,'2025-05-27','fin',10,200),
(9,'2025-05-27','fin',12,180),
(10,'2025-05-28','fin',12,150),
(11,'2025-05-28','fin',10,190),
(12,'2025-05-28','fin',2,979),
(13,'2025-05-28','fin',11,199),
(14,'2025-05-28','fin',10,189),
(15,'2025-05-28','fin',10,188),
(16,'2025-05-28','fin',12,149),
(17,'2025-05-28','fin',10,168),
(18,'2025-05-28','fin',12,137),
(19,'2025-05-28','inicio',13,400),
(20,'2025-05-28','fin',11,198),
(21,'2025-05-28','fin',10,167),
(22,'2025-05-28','fin',10,147),
(23,'2025-05-28','fin',2,969),
(24,'2025-05-28','fin',13,360),
(25,'2025-05-28','fin',10,135),
(26,'2025-05-28','fin',11,98),
(27,'2025-05-29','fin',13,358),
(28,'2025-05-29','fin',11,78),
(29,'2025-05-29','fin',12,37),
(30,'2025-05-29','fin',10,75),
(31,'2025-05-29','inicio',14,1000);
/*!40000 ALTER TABLE `corte_inventario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_pedido_distribuidor`
--

DROP TABLE IF EXISTS `detalle_pedido_distribuidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_pedido_distribuidor` (
  `id_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `id_pedido` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `id_pedido` (`id_pedido`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_pedido_distribuidor_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedido_distribuidor` (`id_pedido`),
  CONSTRAINT `detalle_pedido_distribuidor_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_pedido_distribuidor`
--

LOCK TABLES `detalle_pedido_distribuidor` WRITE;
/*!40000 ALTER TABLE `detalle_pedido_distribuidor` DISABLE KEYS */;
INSERT INTO `detalle_pedido_distribuidor` VALUES
(1,1,13,1,200.00),
(2,2,10,1,0.00),
(3,3,2,2,0.00),
(4,4,13,1,0.00),
(5,5,13,20,0.00),
(6,6,13,20,0.00),
(7,7,14,100,0.00),
(8,7,13,200,0.00);
/*!40000 ALTER TABLE `detalle_pedido_distribuidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_venta` (
  `id_detalle` int(11) NOT NULL AUTO_INCREMENT,
  `id_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_detalle`),
  KEY `id_venta` (`id_venta`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `detalle_venta_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  CONSTRAINT `detalle_venta_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `producto` (`id_producto`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_venta`
--

LOCK TABLES `detalle_venta` WRITE;
/*!40000 ALTER TABLE `detalle_venta` DISABLE KEYS */;
INSERT INTO `detalle_venta` VALUES
(6,7,11,100,20.00),
(7,8,12,200,40.00),
(8,8,10,50,56.00),
(9,8,2,20,30.00),
(10,9,11,200,20.00),
(11,9,10,200,56.00),
(12,10,12,20,40.00),
(13,11,2,1,30.00),
(14,12,10,200,56.00),
(15,12,12,100,40.00),
(16,13,12,30,40.00),
(17,13,10,10,56.00),
(18,14,2,20,30.00),
(19,15,11,1,20.00),
(20,16,10,1,56.00),
(21,17,10,1,56.00),
(22,18,12,1,40.00),
(23,19,10,20,56.00),
(24,19,12,12,40.00),
(25,20,11,1,20.00),
(26,20,10,1,56.00),
(27,21,10,20,56.00),
(28,21,2,10,30.00),
(29,22,13,40,200.00),
(30,22,10,12,56.00),
(31,22,11,100,20.00),
(32,23,13,1,200.00),
(33,24,11,20,20.00),
(34,24,12,100,40.00),
(35,24,10,60,56.00);
/*!40000 ALTER TABLE `detalle_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `distribuidor`
--

DROP TABLE IF EXISTS `distribuidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `distribuidor` (
  `id_distribuidor` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `correo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_distribuidor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `distribuidor`
--

LOCK TABLES `distribuidor` WRITE;
/*!40000 ALTER TABLE `distribuidor` DISABLE KEYS */;
INSERT INTO `distribuidor` VALUES
(1,'Distribuciones El Águila','contacto@elaguila.com'),
(2,'Súper Alimentos MX','ventas@superalimentos.mx'),
(3,'Refrescos del Norte','norte@gmail.com'),
(6,'FoodRapi','rapi03@yahoo.com'),
(7,'Croquetas Mex','arturovazros@gmail.com');
/*!40000 ALTER TABLE `distribuidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `metodo_pago`
--

DROP TABLE IF EXISTS `metodo_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `metodo_pago` (
  `id_metodo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) NOT NULL,
  PRIMARY KEY (`id_metodo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `metodo_pago`
--

LOCK TABLES `metodo_pago` WRITE;
/*!40000 ALTER TABLE `metodo_pago` DISABLE KEYS */;
INSERT INTO `metodo_pago` VALUES
(1,'Efectivo'),
(2,'Debito'),
(3,'Credito');
/*!40000 ALTER TABLE `metodo_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_venta` int(11) NOT NULL,
  `id_metodo` int(11) NOT NULL,
  `total_pagado` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_pago`),
  KEY `id_venta` (`id_venta`),
  KEY `id_metodo` (`id_metodo`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `venta` (`id_venta`),
  CONSTRAINT `pago_ibfk_2` FOREIGN KEY (`id_metodo`) REFERENCES `metodo_pago` (`id_metodo`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` VALUES
(4,7,1,1700.00),
(5,8,1,9800.00),
(6,9,1,12160.00),
(7,10,1,736.00),
(8,11,1,30.00),
(9,12,1,12360.00),
(10,13,1,1548.80),
(11,14,1,528.00),
(12,15,1,20.00),
(13,16,1,56.00),
(14,17,1,56.00),
(15,18,1,40.00),
(16,19,1,1408.00),
(17,20,1,76.00),
(18,21,1,1249.60),
(19,22,1,9331.36),
(20,23,2,200.00),
(21,24,1,6708.80);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_distribuidor`
--

DROP TABLE IF EXISTS `pedido_distribuidor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_distribuidor` (
  `id_pedido` int(11) NOT NULL AUTO_INCREMENT,
  `id_distribuidor` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  PRIMARY KEY (`id_pedido`),
  KEY `id_distribuidor` (`id_distribuidor`),
  CONSTRAINT `pedido_distribuidor_ibfk_1` FOREIGN KEY (`id_distribuidor`) REFERENCES `distribuidor` (`id_distribuidor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_distribuidor`
--

LOCK TABLES `pedido_distribuidor` WRITE;
/*!40000 ALTER TABLE `pedido_distribuidor` DISABLE KEYS */;
INSERT INTO `pedido_distribuidor` VALUES
(1,7,'2025-05-29 12:36:50'),
(2,2,'2025-05-29 00:00:00'),
(3,6,'2025-05-29 00:00:00'),
(4,7,'2025-05-29 00:00:00'),
(5,7,'2025-05-29 00:00:00'),
(6,7,'2025-05-29 00:00:00'),
(7,7,'2025-05-29 00:00:00');
/*!40000 ALTER TABLE `pedido_distribuidor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `precio_unitario_venta` decimal(10,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `id_distribuidor` int(11) NOT NULL,
  `imagen_url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `id_distribuidor` (`id_distribuidor`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`id_distribuidor`) REFERENCES `distribuidor` (`id_distribuidor`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES
(2,'Coca Cola 1L',30.00,969,3,NULL),
(10,'Helado Vainilla',56.00,75,2,NULL),
(11,'Pepsi Dieta 600ml',20.00,78,3,NULL),
(12,'Pizza Congelada',40.00,37,6,NULL),
(13,'Costal Croquetas Dog',200.00,358,7,NULL),
(14,'Croquetas Gato ',200.00,1000,7,NULL);
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(255) NOT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES
(1,'admin'),
(2,'cajero');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarjeta`
--

DROP TABLE IF EXISTS `tarjeta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarjeta` (
  `id_tarjeta` int(11) NOT NULL AUTO_INCREMENT,
  `id_pago` int(11) NOT NULL,
  `tipo_tarjeta` varchar(255) DEFAULT NULL,
  `digitos_tarjeta` varchar(4) DEFAULT NULL,
  `banco` varchar(255) DEFAULT NULL,
  `ccv` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`id_tarjeta`),
  KEY `id_pago` (`id_pago`),
  CONSTRAINT `tarjeta_ibfk_1` FOREIGN KEY (`id_pago`) REFERENCES `pago` (`id_pago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarjeta`
--

LOCK TABLES `tarjeta` WRITE;
/*!40000 ALTER TABLE `tarjeta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tarjeta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `rol` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`),
  UNIQUE KEY `username` (`username`),
  KEY `rol` (`rol`),
  CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`rol`) REFERENCES `rol` (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES
(1,'Juan Robles','admin1',1,'$2a$10$cccmoCk8Ebu/R6FARn74C.fdZ5LzztIsKklO6XrOrb5ZNL7D6XJj2'),
(2,'Marcos Luis','cajero1',2,'$2a$10$uBHoIpDYw90AZX9qdy6Av.dsO7KXMoMmPiqEp45jPZyVcap7.7lHm'),
(3,'Luis Miguel ','cajero2',2,'$2a$10$dz5uKV.ub3kt/Q3yY.G/zevrD7/2kaZpMErA8WipfsVYc1ZFTSyGe'),
(8,'Manuel Manu','ManuAdmin',1,'$2a$10$LAz0jO.n8c9UkPBVW6lwhuM24sK.zInYwwdhM07SPI7k/OnRr5Sla');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha` datetime NOT NULL,
  `total` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_usuario` (`id_usuario`),
  CONSTRAINT `venta_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  CONSTRAINT `venta_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
INSERT INTO `venta` VALUES
(7,2,2,'2025-05-27 11:00:00',0.00),
(8,3,2,'2025-05-27 11:04:04',0.00),
(9,5,3,'2025-05-27 13:44:01',0.00),
(10,2,2,'2025-05-27 15:33:58',0.00),
(11,5,2,'2025-05-27 18:48:13',0.00),
(12,3,2,'2025-05-27 19:19:32',0.00),
(13,2,2,'2025-05-28 12:39:49',0.00),
(14,2,2,'2025-05-28 12:51:41',0.00),
(15,3,2,'2025-05-28 18:28:59',0.00),
(16,8,2,'2025-05-28 18:52:38',0.00),
(17,3,2,'2025-05-28 18:57:57',0.00),
(18,5,2,'2025-05-28 19:00:06',0.00),
(19,8,2,'2025-05-28 19:06:06',0.00),
(20,11,2,'2025-05-28 22:08:03',0.00),
(21,12,2,'2025-05-28 22:11:45',0.00),
(22,8,3,'2025-05-28 22:15:55',0.00),
(23,11,2,'2025-05-29 12:45:10',0.00),
(24,2,2,'2025-05-29 15:39:05',0.00);
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-29 16:48:13
