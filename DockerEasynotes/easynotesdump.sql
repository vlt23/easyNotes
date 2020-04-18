-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: localhost    Database: easynotes
-- ------------------------------------------------------
-- Server version	5.7.29-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apunte`
--

DROP TABLE IF EXISTS `apunte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apunte` (
  `id` bigint(20) NOT NULL,
  `es_examen` bit(1) NOT NULL,
  `fecha_subida` datetime(6) DEFAULT NULL,
  `file_path` tinyblob,
  `nombre` varchar(255) DEFAULT NULL,
  `numero_descargas` int(11) NOT NULL,
  `tamanyo` bigint(20) NOT NULL,
  `asignatura_id` bigint(20) DEFAULT NULL,
  `autor_id` bigint(20) DEFAULT NULL,
  `carrera_id` bigint(20) DEFAULT NULL,
  `universidad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2u7h14det682ikvesda5uutlw` (`asignatura_id`),
  KEY `FK3gg5d8ocxcs69mhh0mjc4br2x` (`autor_id`),
  KEY `FKit19qqxw8v2wl30mk83f7bfkf` (`carrera_id`),
  KEY `FKq9x0lr6pacinbapruvhyrfmn4` (`universidad_id`),
  CONSTRAINT `FK2u7h14det682ikvesda5uutlw` FOREIGN KEY (`asignatura_id`) REFERENCES `asignatura` (`id`),
  CONSTRAINT `FK3gg5d8ocxcs69mhh0mjc4br2x` FOREIGN KEY (`autor_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKit19qqxw8v2wl30mk83f7bfkf` FOREIGN KEY (`carrera_id`) REFERENCES `carrera` (`id`),
  CONSTRAINT `FKq9x0lr6pacinbapruvhyrfmn4` FOREIGN KEY (`universidad_id`) REFERENCES `universidad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apunte`
--

LOCK TABLES `apunte` WRITE;
/*!40000 ALTER TABLE `apunte` DISABLE KEYS */;
INSERT INTO `apunte` VALUES (11,_binary '\0','2020-04-18 13:14:41.138000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/pruebaPablo.pdfw\0/x','manualPortatil',0,5725786,5,10,3,1),(16,_binary '\0','2020-04-18 13:14:41.840000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/zswap.pdfw\0/x','script',0,351140,9,10,4,1),(21,_binary '\0','2020-04-18 13:14:42.526000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/zram.pdfw\0/x','script',0,692892,7,10,3,1),(26,_binary '\0','2020-04-18 13:14:43.182000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/bateria.pdfw\0/x','arduino',0,3483746,8,10,4,2),(31,_binary '\0','2020-04-18 13:14:43.664000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/prueba_linkedlist.pdfw\0/x','script',0,4216076,7,10,4,1),(36,_binary '\0','2020-04-18 13:14:44.275000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/arduino.pdfw\0/x','arduino',0,808895,6,10,4,1),(41,_binary '','2020-04-18 13:14:44.967000',_binary '¬\í\0sr\0java.io.File-¤E\r\äÿ\0L\0patht\0Ljava/lang/String;xpt\0Files/esqui.pdfw\0/x','esqui',0,2914455,5,10,3,1);
/*!40000 ALTER TABLE `apunte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `apunte_tags`
--

DROP TABLE IF EXISTS `apunte_tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apunte_tags` (
  `apuntes_id` bigint(20) NOT NULL,
  `tags_id` bigint(20) NOT NULL,
  KEY `FKo6vtcvm2t07calnypny1djkg0` (`tags_id`),
  KEY `FKgtmpqqta4lultrm1y6opsghuj` (`apuntes_id`),
  CONSTRAINT `FKgtmpqqta4lultrm1y6opsghuj` FOREIGN KEY (`apuntes_id`) REFERENCES `apunte` (`id`),
  CONSTRAINT `FKo6vtcvm2t07calnypny1djkg0` FOREIGN KEY (`tags_id`) REFERENCES `tag` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apunte_tags`
--

LOCK TABLES `apunte_tags` WRITE;
/*!40000 ALTER TABLE `apunte_tags` DISABLE KEYS */;
INSERT INTO `apunte_tags` VALUES (11,12),(11,13),(11,14),(11,15),(16,17),(16,18),(16,19),(16,20),(21,22),(21,23),(21,24),(21,25),(26,27),(26,28),(26,29),(26,30),(31,32),(31,33),(31,34),(31,35),(36,37),(36,38),(36,39),(36,40),(41,42),(41,43),(41,44),(41,45);
/*!40000 ALTER TABLE `apunte_tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asignatura`
--

DROP TABLE IF EXISTS `asignatura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asignatura` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `profesores` varchar(255) DEFAULT NULL,
  `carrera_id` bigint(20) DEFAULT NULL,
  `universidad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpck5ph8oyrxv8v8fwmjtxt8kq` (`carrera_id`),
  KEY `FKhaxw3r26f91wyswxnh1gaeja4` (`universidad_id`),
  CONSTRAINT `FKhaxw3r26f91wyswxnh1gaeja4` FOREIGN KEY (`universidad_id`) REFERENCES `universidad` (`id`),
  CONSTRAINT `FKpck5ph8oyrxv8v8fwmjtxt8kq` FOREIGN KEY (`carrera_id`) REFERENCES `carrera` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asignatura`
--

LOCK TABLES `asignatura` WRITE;
/*!40000 ALTER TABLE `asignatura` DISABLE KEYS */;
INSERT INTO `asignatura` VALUES (5,'DAD','Patxi',3,1),(6,'POO','el de POO',4,1),(7,'ED','Buenaposada',3,2),(8,'Logica','Alexandra',4,1),(9,'SO','Alberto',3,2);
/*!40000 ALTER TABLE `asignatura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carrera` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `universidad_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7si6txk1u8rdi8ypccd3y7qjf` (`universidad_id`),
  CONSTRAINT `FK7si6txk1u8rdi8ypccd3y7qjf` FOREIGN KEY (`universidad_id`) REFERENCES `universidad` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carrera`
--

LOCK TABLES `carrera` WRITE;
/*!40000 ALTER TABLE `carrera` DISABLE KEYS */;
INSERT INTO `carrera` VALUES (3,'GIC',2),(4,'GII',1);
/*!40000 ALTER TABLE `carrera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (46),(46),(46),(46),(46),(46);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tag` (
  `id` bigint(20) NOT NULL,
  `tag` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (12,'manualPortatil'),(13,'DAD'),(14,'GIC'),(15,'URJC'),(17,'script'),(18,'SO'),(19,'GII'),(20,'URJC'),(22,'script'),(23,'ED'),(24,'GIC'),(25,'URJC'),(27,'arduino'),(28,'Logica'),(29,'GII'),(30,'UPM'),(32,'script'),(33,'ED'),(34,'GII'),(35,'URJC'),(37,'arduino'),(38,'POO'),(39,'GII'),(40,'URJC'),(42,'esqui'),(43,'DAD'),(44,'GIC'),(45,'URJC');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universidad`
--

DROP TABLE IF EXISTS `universidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `universidad` (
  `id` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidad`
--

LOCK TABLES `universidad` WRITE;
/*!40000 ALTER TABLE `universidad` DISABLE KEYS */;
INSERT INTO `universidad` VALUES (1,'URJC'),(2,'UPM');
/*!40000 ALTER TABLE `universidad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `id` bigint(20) NOT NULL,
  `apellidos` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `creditos` int(11) NOT NULL,
  `is_admin` bit(1) NOT NULL,
  `nick` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `numero_descargas` int(11) NOT NULL,
  `password_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_c73frfmnb66qdcnki0xc5mj59` (`nick`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (10,'admin','easynotes.dad.2020@gmail.com',1073741823,_binary '','admin','user',0,'$2a$10$MGszDkiZfu/3dDMJSRPMv.V9xgIyBoMf6e0IQzgk6atsMUeWOKwHW');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_roles`
--

DROP TABLE IF EXISTS `usuario_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario_roles` (
  `usuario_id` bigint(20) NOT NULL,
  `roles` varchar(255) DEFAULT NULL,
  KEY `FKqblnumndby0ftm4c7sg6uso6p` (`usuario_id`),
  CONSTRAINT `FKqblnumndby0ftm4c7sg6uso6p` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_roles`
--

LOCK TABLES `usuario_roles` WRITE;
/*!40000 ALTER TABLE `usuario_roles` DISABLE KEYS */;
INSERT INTO `usuario_roles` VALUES (10,'ROLE_ADMIN'),(10,'ROLE_USER');
/*!40000 ALTER TABLE `usuario_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-04-18 13:48:02
