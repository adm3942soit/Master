-- MySQL dump 10.13  Distrib 5.5.45, for Win64 (x86)
--
-- Host: localhost    Database: master
-- ------------------------------------------------------
-- Server version	5.5.45

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
-- Table structure for table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `addresses` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CITY` varchar(255) DEFAULT NULL,
  `COUNTRY` varchar(255) NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `FULL_ADDRESS` varchar(255) DEFAULT NULL,
  `HOUSE_NUMBER` bigint(20) DEFAULT NULL,
  `HOUSE_NUMER_ADDITION` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `STATE` varchar(255) DEFAULT NULL,
  `STREET` varchar(255) DEFAULT NULL,
  `ZIPCODE` varchar(255) DEFAULT NULL,
  `person_NUMBER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `FK34207BA2FCF8480A` (`person_NUMBER`),
  CONSTRAINT `FK34207BA2FCF8480A` FOREIGN KEY (`person_NUMBER`) REFERENCES `persons` (`NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `addresses`
--

LOCK TABLES `addresses` WRITE;
/*!40000 ALTER TABLE `addresses` DISABLE KEYS */;
/*!40000 ALTER TABLE `addresses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogs`
--

DROP TABLE IF EXISTS `catalogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogs` (
  `CATALOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`CATALOG_ID`),
  UNIQUE KEY `CATALOG_ID` (`CATALOG_ID`),
  UNIQUE KEY `NAME` (`NAME`),
  UNIQUE KEY `NAME_2` (`NAME`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogs`
--

LOCK TABLES `catalogs` WRITE;
/*!40000 ALTER TABLE `catalogs` DISABLE KEYS */;
INSERT INTO `catalogs` VALUES (1,'2016-03-15 12:27:24','admin','2016-03-15 12:28:11','admin','firstCatalog');
/*!40000 ALTER TABLE `catalogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `catalogs_departments`
--

DROP TABLE IF EXISTS `catalogs_departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `catalogs_departments` (
  `catalogs_CATALOG_ID` int(11) NOT NULL,
  `departments_DEPARTMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`catalogs_CATALOG_ID`,`departments_DEPARTMENT_ID`),
  UNIQUE KEY `departments_DEPARTMENT_ID` (`departments_DEPARTMENT_ID`),
  KEY `FK4287C21C925C4C5E` (`departments_DEPARTMENT_ID`),
  KEY `FK4287C21CA34AFFD` (`catalogs_CATALOG_ID`),
  CONSTRAINT `FK4287C21C925C4C5E` FOREIGN KEY (`departments_DEPARTMENT_ID`) REFERENCES `departments` (`DEPARTMENT_ID`),
  CONSTRAINT `FK4287C21CA34AFFD` FOREIGN KEY (`catalogs_CATALOG_ID`) REFERENCES `catalogs` (`CATALOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `catalogs_departments`
--

LOCK TABLES `catalogs_departments` WRITE;
/*!40000 ALTER TABLE `catalogs_departments` DISABLE KEYS */;
/*!40000 ALTER TABLE `catalogs_departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesusd`
--

DROP TABLE IF EXISTS `coursesusd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursesusd` (
  `COURSEUSD_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `BUYING_RATE` double NOT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `SELLING_RATE` double NOT NULL,
  PRIMARY KEY (`COURSEUSD_ID`),
  UNIQUE KEY `COURSEUSD_ID` (`COURSEUSD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesusd`
--

LOCK TABLES `coursesusd` WRITE;
/*!40000 ALTER TABLE `coursesusd` DISABLE KEYS */;
/*!40000 ALTER TABLE `coursesusd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursesusd_products`
--

DROP TABLE IF EXISTS `coursesusd_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursesusd_products` (
  `coursesUSD_COURSEUSD_ID` bigint(20) NOT NULL,
  `products_PRODUCT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`coursesUSD_COURSEUSD_ID`,`products_PRODUCT_ID`),
  UNIQUE KEY `products_PRODUCT_ID` (`products_PRODUCT_ID`),
  KEY `FK34DE3BD5FCA25413` (`products_PRODUCT_ID`),
  KEY `FK34DE3BD5277DBF09` (`coursesUSD_COURSEUSD_ID`),
  CONSTRAINT `FK34DE3BD5277DBF09` FOREIGN KEY (`coursesUSD_COURSEUSD_ID`) REFERENCES `coursesusd` (`COURSEUSD_ID`),
  CONSTRAINT `FK34DE3BD5FCA25413` FOREIGN KEY (`products_PRODUCT_ID`) REFERENCES `products` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursesusd_products`
--

LOCK TABLES `coursesusd_products` WRITE;
/*!40000 ALTER TABLE `coursesusd_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `coursesusd_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments`
--

DROP TABLE IF EXISTS `departments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments` (
  `DEPARTMENT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `CATALOG_ID` int(11) NOT NULL,
  PRIMARY KEY (`DEPARTMENT_ID`),
  UNIQUE KEY `DEPARTMENT_ID` (`DEPARTMENT_ID`),
  UNIQUE KEY `NAME` (`NAME`),
  UNIQUE KEY `NAME_2` (`NAME`),
  KEY `FK1F3A27617CAE0E18` (`CATALOG_ID`),
  CONSTRAINT `FK1F3A27617CAE0E18` FOREIGN KEY (`CATALOG_ID`) REFERENCES `catalogs` (`CATALOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments`
--

LOCK TABLES `departments` WRITE;
/*!40000 ALTER TABLE `departments` DISABLE KEYS */;
INSERT INTO `departments` VALUES (1,'2016-03-15 12:27:53','admin','2016-03-15 12:27:53','admin','firstDepartment',1),(2,'2016-03-15 12:28:11','admin','2016-03-15 12:28:11','admin','secondDepartment',1);
/*!40000 ALTER TABLE `departments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departments_products`
--

DROP TABLE IF EXISTS `departments_products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `departments_products` (
  `departments_DEPARTMENT_ID` int(11) NOT NULL,
  `products_PRODUCT_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`departments_DEPARTMENT_ID`,`products_PRODUCT_ID`),
  UNIQUE KEY `products_PRODUCT_ID` (`products_PRODUCT_ID`),
  KEY `FKEBA69882FCA25413` (`products_PRODUCT_ID`),
  KEY `FKEBA69882925C4C5E` (`departments_DEPARTMENT_ID`),
  CONSTRAINT `FKEBA69882925C4C5E` FOREIGN KEY (`departments_DEPARTMENT_ID`) REFERENCES `departments` (`DEPARTMENT_ID`),
  CONSTRAINT `FKEBA69882FCA25413` FOREIGN KEY (`products_PRODUCT_ID`) REFERENCES `products` (`PRODUCT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departments_products`
--

LOCK TABLES `departments_products` WRITE;
/*!40000 ALTER TABLE `departments_products` DISABLE KEYS */;
/*!40000 ALTER TABLE `departments_products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons`
--

DROP TABLE IF EXISTS `persons`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons` (
  `NUMBER` bigint(20) NOT NULL AUTO_INCREMENT,
  `BANK_ACCOUNT` varchar(255) DEFAULT NULL,
  `BIRTHDATE` datetime DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `DRIVER_LICENSE` varchar(255) DEFAULT NULL,
  `EDUCATION_DESCRIPTION` varchar(255) DEFAULT NULL,
  `EDUCATION_LEVEL` varchar(255) DEFAULT NULL,
  `EMAIL_ADDRESS` varchar(255) DEFAULT NULL,
  `FIRST_NAME` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `INITIALS` varchar(255) DEFAULT NULL,
  `LAST_NAME` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `MIDDLE_NAME` varchar(255) DEFAULT NULL,
  `MOBILE_NUMBER` varchar(255) DEFAULT NULL,
  `PASSPORT_NUMBER` varchar(255) DEFAULT NULL,
  `PASSPORT_TYPE` varchar(255) DEFAULT NULL,
  `PASSPORT_VALID_UNTIL` datetime DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONENUMBER` varchar(255) DEFAULT NULL,
  `SOCIAL_SECURITY_NUMBER` varchar(255) DEFAULT NULL,
  `USER_NAME` varchar(255) DEFAULT NULL,
  `address_ID` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`NUMBER`),
  UNIQUE KEY `NUMBER` (`NUMBER`),
  KEY `FKD78FCFBEE4E3BC38` (`address_ID`),
  CONSTRAINT `FKD78FCFBEE4E3BC38` FOREIGN KEY (`address_ID`) REFERENCES `addresses` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons`
--

LOCK TABLES `persons` WRITE;
/*!40000 ALTER TABLE `persons` DISABLE KEYS */;
INSERT INTO `persons` VALUES (1,'1234567890',NULL,'2016-03-15 10:40:04','admin',NULL,NULL,NULL,'admin@yahoo.com','admin','M',NULL,'admin','2016-03-15 10:40:04','admin',NULL,NULL,NULL,NULL,NULL,'admin','','1','admin',NULL);
/*!40000 ALTER TABLE `persons` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons_personstatuses`
--

DROP TABLE IF EXISTS `persons_personstatuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons_personstatuses` (
  `persons_NUMBER` bigint(20) NOT NULL,
  `personStatuses_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`persons_NUMBER`,`personStatuses_ID`),
  UNIQUE KEY `personStatuses_ID` (`personStatuses_ID`),
  KEY `FK18128916175EF68E` (`personStatuses_ID`),
  KEY `FK181289162944FB81` (`persons_NUMBER`),
  CONSTRAINT `FK18128916175EF68E` FOREIGN KEY (`personStatuses_ID`) REFERENCES `personstatuses` (`ID`),
  CONSTRAINT `FK181289162944FB81` FOREIGN KEY (`persons_NUMBER`) REFERENCES `persons` (`NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons_personstatuses`
--

LOCK TABLES `persons_personstatuses` WRITE;
/*!40000 ALTER TABLE `persons_personstatuses` DISABLE KEYS */;
/*!40000 ALTER TABLE `persons_personstatuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persons_userlogs`
--

DROP TABLE IF EXISTS `persons_userlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persons_userlogs` (
  `persons_NUMBER` bigint(20) NOT NULL,
  `userLogs_USERLOG_ID` bigint(20) NOT NULL,
  PRIMARY KEY (`persons_NUMBER`,`userLogs_USERLOG_ID`),
  UNIQUE KEY `userLogs_USERLOG_ID` (`userLogs_USERLOG_ID`),
  KEY `FKDFD90B5B705A875D` (`userLogs_USERLOG_ID`),
  KEY `FKDFD90B5B2944FB81` (`persons_NUMBER`),
  CONSTRAINT `FKDFD90B5B2944FB81` FOREIGN KEY (`persons_NUMBER`) REFERENCES `persons` (`NUMBER`),
  CONSTRAINT `FKDFD90B5B705A875D` FOREIGN KEY (`userLogs_USERLOG_ID`) REFERENCES `userlogs` (`USERLOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persons_userlogs`
--

LOCK TABLES `persons_userlogs` WRITE;
/*!40000 ALTER TABLE `persons_userlogs` DISABLE KEYS */;
/*!40000 ALTER TABLE `persons_userlogs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personstatuses`
--

DROP TABLE IF EXISTS `personstatuses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personstatuses` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `END_DATE` datetime DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `MOTIVATION` varchar(255) DEFAULT NULL,
  `START_DATE` datetime DEFAULT NULL,
  `STATUS` varchar(255) NOT NULL,
  `NUMBER` bigint(20) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`),
  KEY `FK2A6B1E751DC5FE0` (`NUMBER`),
  CONSTRAINT `FK2A6B1E751DC5FE0` FOREIGN KEY (`NUMBER`) REFERENCES `persons` (`NUMBER`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personstatuses`
--

LOCK TABLES `personstatuses` WRITE;
/*!40000 ALTER TABLE `personstatuses` DISABLE KEYS */;
/*!40000 ALTER TABLE `personstatuses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `products` (
  `PRODUCT_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `ALL_COUNT` double DEFAULT NULL,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `FILE_IMAGE` mediumblob,
  `FOR_COUNT` int(11) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `NAME_IMAGE` varchar(255) DEFAULT NULL,
  `PRICE_UAH` double DEFAULT NULL,
  `PRICE_USD` double DEFAULT NULL,
  `SHORT_NAME` varchar(255) DEFAULT NULL,
  `COURSEUSD_ID` bigint(20) NOT NULL,
  `DEPARTMENT_ID` int(11) NOT NULL,
  PRIMARY KEY (`PRODUCT_ID`),
  UNIQUE KEY `PRODUCT_ID` (`PRODUCT_ID`),
  UNIQUE KEY `NAME` (`NAME`),
  UNIQUE KEY `NAME_2` (`NAME`),
  KEY `FKC42BD164DA75BD38` (`COURSEUSD_ID`),
  KEY `FKC42BD164560150BC` (`DEPARTMENT_ID`),
  CONSTRAINT `FKC42BD164560150BC` FOREIGN KEY (`DEPARTMENT_ID`) REFERENCES `departments` (`DEPARTMENT_ID`),
  CONSTRAINT `FKC42BD164DA75BD38` FOREIGN KEY (`COURSEUSD_ID`) REFERENCES `coursesusd` (`COURSEUSD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sysparameters`
--

DROP TABLE IF EXISTS `sysparameters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sysparameters` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `NAME` varchar(255) NOT NULL,
  `VALUE` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sysparameters`
--

LOCK TABLES `sysparameters` WRITE;
/*!40000 ALTER TABLE `sysparameters` DISABLE KEYS */;
INSERT INTO `sysparameters` VALUES (1,'2016-03-15 10:40:04','admin','Application database scheme version','2016-03-15 10:40:04','admin','dbversion','1.0');
/*!40000 ALTER TABLE `sysparameters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userlogs`
--

DROP TABLE IF EXISTS `userlogs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userlogs` (
  `USERLOG_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CREATION_DATE` datetime NOT NULL,
  `CREATION_PERSON` varchar(255) NOT NULL,
  `LAST_UPDATE_DATE` datetime NOT NULL,
  `LAST_UPDATE_PERSON` varchar(255) NOT NULL,
  `LOGIN_TIME` datetime NOT NULL,
  `LOGOUT_TIME` datetime DEFAULT NULL,
  `NUMBER` bigint(20) NOT NULL,
  PRIMARY KEY (`USERLOG_ID`),
  UNIQUE KEY `USERLOG_ID` (`USERLOG_ID`),
  UNIQUE KEY `LOGIN_TIME` (`LOGIN_TIME`),
  KEY `FKF028D3FA1DC5FE0` (`NUMBER`),
  CONSTRAINT `FKF028D3FA1DC5FE0` FOREIGN KEY (`NUMBER`) REFERENCES `persons` (`NUMBER`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userlogs`
--

LOCK TABLES `userlogs` WRITE;
/*!40000 ALTER TABLE `userlogs` DISABLE KEYS */;
INSERT INTO `userlogs` VALUES (1,'2016-03-15 11:21:20','admin','2016-03-15 11:21:20','admin','2016-03-15 11:21:20',NULL,1);
/*!40000 ALTER TABLE `userlogs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-03-23 10:34:06
