-- MySQL dump 10.13  Distrib 5.5.56, for Win32 (AMD64)
--
-- Host: localhost    Database: album
-- ------------------------------------------------------
-- Server version	5.5.62-log

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
-- Table structure for table `album`
--

DROP TABLE IF EXISTS `album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `album` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(31) NOT NULL,
  `cover_id` int(11) DEFAULT NULL,
  `is_private` tinyint(1) DEFAULT '1',
  `owner` int(11) DEFAULT NULL COMMENT '鐩稿唽鍒涘缓鐢ㄦ埛id',
  `deleted` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `describe` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `album_user_id_fk` (`owner`),
  CONSTRAINT `album_user_id_fk` FOREIGN KEY (`owner`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `album`
--

LOCK TABLES `album` WRITE;
/*!40000 ALTER TABLE `album` DISABLE KEYS */;
INSERT INTO `album` VALUES (1,'2r4f',NULL,1,2,1,'2022-05-20 13:30:43','2022-05-20 13:30:43','faf'),(3,'2r4fsf',NULL,0,2,0,'2022-05-20 17:11:15','2022-05-20 22:43:43','鍦版柟璁叉寮忛鏍煎寲鎺ュ彈鐨勪經鍐堝悗i'),(4,'saf',NULL,0,2,0,'2022-05-20 17:35:23','2022-06-18 23:49:07',''),(5,'safwf',NULL,0,2,0,'2022-05-20 17:35:34','2022-05-20 17:35:34',''),(6,'1',NULL,1,2,0,'2022-05-20 17:35:37','2022-05-20 22:56:24',''),(7,'2',NULL,1,2,0,'2022-05-20 17:35:41','2022-05-20 17:35:41',''),(8,'3',NULL,1,2,0,'2022-05-20 17:35:44','2022-05-20 17:35:44',''),(9,'4',NULL,1,2,0,'2022-05-20 17:35:47','2022-05-20 17:35:47',''),(10,'5',NULL,1,2,0,'2022-05-20 17:35:49','2022-05-20 17:35:49',''),(11,'6',NULL,1,2,0,'2022-05-20 17:35:51','2022-05-20 17:35:51',''),(12,'7',NULL,1,2,0,'2022-05-20 17:35:53','2022-05-20 17:35:53',''),(13,'8',NULL,1,2,0,'2022-05-20 17:35:55','2022-05-20 17:35:55',''),(14,'9',NULL,1,2,0,'2022-05-20 17:35:56','2022-05-20 17:35:56',''),(15,'10',NULL,1,2,0,'2022-05-20 17:36:00','2022-05-20 17:36:00',''),(17,'sbhr',NULL,1,2,0,'2022-06-16 11:49:14','2022-06-16 11:49:14','sbhr'),(18,'asdf',NULL,1,73,1,'2022-06-17 00:53:33','2022-06-17 00:53:33','asdf'),(19,'',NULL,0,73,0,'2022-06-17 01:04:10','2022-06-18 23:37:04',''),(20,'asdfsadfasd',NULL,1,73,1,'2022-06-17 11:13:36','2022-06-17 11:13:36','sdf'),(21,'sdfg',NULL,1,73,1,'2022-06-17 11:15:58','2022-06-17 11:15:58','fg'),(22,'sdfgsdf',NULL,0,73,1,'2022-06-17 11:20:02','2022-06-17 11:20:02','dsfgdfg'),(23,'lalla',NULL,1,73,1,'2022-06-17 11:21:42','2022-06-17 11:21:42','dsfgdfg'),(24,'test',NULL,1,73,1,'2022-06-17 11:26:27','2022-06-17 11:26:27','2333'),(25,'鐩稿唽2',NULL,1,73,1,'2022-06-18 14:24:37','2022-06-18 19:13:26','璁＄畻鏈轰笌澶ф暟鎹闄?),(26,'',NULL,0,73,0,'2022-06-18 15:25:50','2022-06-18 23:38:15',''),(27,'鐩稿唽4',NULL,1,73,1,'2022-06-18 18:59:15','2022-06-18 19:14:28','1鐝?),(28,'鐩稿唽5',NULL,1,73,1,'2022-06-18 18:59:55','2022-06-18 19:16:18','鐝婄憵瀹績娴?),(29,'',NULL,1,73,0,'2022-06-18 19:19:41','2022-06-18 23:32:40',''),(30,'闄堟槑杞╁皬鍨冨溇',NULL,1,73,1,'2022-06-18 19:20:25','2022-06-18 19:20:25','鍛靛懙鍝堝搱鍝堟垨'),(31,'鐩稿唽1',NULL,1,98,0,'2022-06-18 19:48:19','2022-06-18 19:48:19','杩欐槸鐩稿唽涓€'),(32,'鐩稿唽2',NULL,1,98,1,'2022-06-18 19:48:40','2022-06-18 19:48:40','杩欐槸鐩稿唽浜?),(33,'鐩稿唽3',NULL,1,98,0,'2022-06-18 19:48:47','2022-06-18 19:48:47','杩欐槸鐩稿唽涓?),(34,'鐝婄憵瀹績娴?,NULL,1,98,1,'2022-06-18 19:48:54','2022-06-18 19:53:48','鐝婄憵瀹績娴?),(35,'鏂板缓鐩稿唽',NULL,1,98,1,'2022-06-18 22:24:04','2022-06-18 22:24:04','鏂板缓鎴愬姛'),(36,'绾︿細澶т綔鎴?,NULL,1,98,0,'2022-06-18 22:28:01','2022-06-18 22:28:01','鐩稿唽淇℃伅宸叉洿鏀?),(37,'sadfsadfasd',NULL,1,73,0,'2022-06-18 23:26:32','2022-06-18 23:26:32','asdfasd'),(38,'',NULL,1,73,0,'2022-06-18 23:26:53','2022-06-18 23:32:31',''),(39,'asdfsdafasd',NULL,1,73,0,'2022-06-18 23:27:10','2022-06-18 23:28:23','asdfasdf'),(40,'',NULL,1,73,0,'2022-06-18 23:27:34','2022-06-18 23:28:50',''),(41,'213423',NULL,0,73,0,'2022-06-18 23:37:45','2022-06-18 23:37:45','234234234');
/*!40000 ALTER TABLE `album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` int(31) NOT NULL AUTO_INCREMENT,
  `user_id` int(31) DEFAULT NULL COMMENT '璇勮浜虹殑id',
  `imal_id` int(31) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `deleted` tinyint(1) DEFAULT '0',
  `modified_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_user_fk` (`user_id`),
  KEY `comment_im2al_fk` (`imal_id`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`imal_id`) REFERENCES `im2al` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (2,2,14,'sbhr','2022-06-17 17:05:36',1,'2022-06-17 18:11:50'),(3,2,14,'sbsbsbhr','2022-06-17 17:43:08',1,'2022-06-17 18:11:56'),(4,2,14,'haorunshabi','2022-06-17 18:12:49',0,'2022-06-17 18:12:49'),(5,73,40,'np','2022-06-18 17:23:39',0,'2022-06-18 17:23:39'),(6,73,40,'np','2022-06-18 17:26:08',0,'2022-06-18 17:26:08'),(7,73,41,'np','2022-06-18 17:26:45',0,'2022-06-18 17:26:45'),(8,73,41,'np','2022-06-18 17:27:18',0,'2022-06-18 17:27:18'),(9,73,40,'np','2022-06-18 17:28:46',0,'2022-06-18 17:28:46'),(10,73,41,'np','2022-06-18 17:29:23',0,'2022-06-18 17:29:23'),(11,73,41,'np','2022-06-18 17:30:19',0,'2022-06-18 17:30:19'),(12,73,41,'npewfr','2022-06-18 17:30:22',0,'2022-06-18 17:30:22'),(13,73,41,'npewfr','2022-06-18 17:30:41',0,'2022-06-18 17:30:41'),(14,73,37,'np','2022-06-18 17:40:18',0,'2022-06-18 17:40:18'),(15,73,37,'np','2022-06-18 17:40:18',0,'2022-06-18 17:40:18'),(16,73,37,'np','2022-06-18 17:40:19',0,'2022-06-18 17:40:19'),(17,73,37,'np','2022-06-18 17:40:19',0,'2022-06-18 17:40:19'),(18,73,37,'np','2022-06-18 17:40:19',0,'2022-06-18 17:40:19'),(19,73,37,'np','2022-06-18 17:40:28',0,'2022-06-18 17:40:28'),(20,73,37,'np','2022-06-18 17:40:29',0,'2022-06-18 17:40:29'),(21,73,37,'np','2022-06-18 17:40:29',0,'2022-06-18 17:40:29'),(22,73,37,'np','2022-06-18 17:40:30',0,'2022-06-18 17:40:30'),(23,73,38,'np','2022-06-18 17:40:36',0,'2022-06-18 17:40:36'),(24,73,38,'np','2022-06-18 17:40:37',0,'2022-06-18 17:40:37'),(25,73,37,'np','2022-06-18 17:40:45',0,'2022-06-18 17:40:45'),(26,73,37,'np','2022-06-18 17:45:39',0,'2022-06-18 17:45:39'),(27,73,37,'np','2022-06-18 17:53:13',0,'2022-06-18 17:53:13'),(28,73,38,'np','2022-06-18 19:10:58',0,'2022-06-18 19:10:58'),(29,73,38,'np','2022-06-18 19:10:59',0,'2022-06-18 19:10:59'),(30,73,38,'杈惧埌','2022-06-18 19:11:03',0,'2022-06-18 19:11:03'),(31,73,38,'杈惧埌','2022-06-18 19:11:04',0,'2022-06-18 19:11:04'),(32,73,43,'蹇冩捣鑰佸﹩','2022-06-18 19:32:50',0,'2022-06-18 19:32:50'),(33,73,46,'蹇冩捣鑰佸﹩鐪熸紓浜紝鐖辨浜?,'2022-06-18 19:33:11',0,'2022-06-18 19:33:11'),(34,73,37,'2432344444444','2022-06-18 20:10:31',0,'2022-06-18 20:10:31'),(35,73,49,'2432344444444','2022-06-18 20:10:52',0,'2022-06-18 20:10:52'),(36,98,48,'鐓х墖涓嬭浇濂芥參鍟?,'2022-06-18 20:11:58',0,'2022-06-18 20:11:58'),(37,98,48,'鐓х墖涓嬭浇濂芥參鍟?,'2022-06-18 20:12:00',0,'2022-06-18 20:12:00'),(38,98,48,'鐓х墖涓嬭浇濂芥參鍟?,'2022-06-18 20:12:00',0,'2022-06-18 20:12:00'),(39,98,48,'鐓х墖涓嬭浇濂芥參鍟?,'2022-06-18 20:12:00',0,'2022-06-18 20:12:00'),(40,98,48,'鐓х墖涓嬭浇濂芥參鍟?,'2022-06-18 20:12:31',0,'2022-06-18 20:12:31'),(41,98,50,'122','2022-06-18 20:39:22',0,'2022-06-18 20:39:22'),(42,102,16,'鎴戝枩娆㈣繖寮犵収鐗?,'2022-06-18 22:15:09',0,'2022-06-18 22:15:09'),(43,102,16,'鎴戝枩娆㈣繖寮犵収鐗?,'2022-06-18 22:15:11',0,'2022-06-18 22:15:11'),(44,102,16,'鎴戝枩娆㈣繖寮犵収鐗?,'2022-06-18 22:15:11',0,'2022-06-18 22:15:11'),(45,102,16,'鎴戝枩娆㈣繖寮犵収鐗?,'2022-06-18 22:15:11',0,'2022-06-18 22:15:11'),(46,102,16,'鎴戝枩娆㈣繖寮犵収鐗?,'2022-06-18 22:15:12',0,'2022-06-18 22:15:12'),(47,102,16,'鎴戝枩娆㈣繖寮犵収鐗?,'2022-06-18 22:15:14',0,'2022-06-18 22:15:14'),(48,98,51,'鐙備笁姘歌繙鐨勭','2022-06-18 22:17:21',0,'2022-06-18 22:17:21'),(49,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:50',0,'2022-06-18 22:20:50'),(50,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:50',0,'2022-06-18 22:20:50'),(51,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:51',0,'2022-06-18 22:20:51'),(52,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:54',0,'2022-06-18 22:20:54'),(53,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:54',0,'2022-06-18 22:20:54'),(54,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:54',0,'2022-06-18 22:20:54'),(55,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:55',0,'2022-06-18 22:20:55'),(56,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:55',0,'2022-06-18 22:20:55'),(57,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:55',0,'2022-06-18 22:20:55'),(58,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:55',0,'2022-06-18 22:20:55'),(59,102,16,'鎴戝枩娆㈣繖寮犲浘鐗?,'2022-06-18 22:20:55',0,'2022-06-18 22:20:55'),(60,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:28',0,'2022-06-18 22:31:28'),(61,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:30',0,'2022-06-18 22:31:30'),(62,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:31',0,'2022-06-18 22:31:31'),(63,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:31',0,'2022-06-18 22:31:31'),(64,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:31',0,'2022-06-18 22:31:31'),(65,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:31',0,'2022-06-18 22:31:31'),(66,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:32',0,'2022-06-18 22:31:32'),(67,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:32',0,'2022-06-18 22:31:32'),(68,73,14,'钑惧鑰佸﹩','2022-06-18 22:31:32',0,'2022-06-18 22:31:32'),(69,73,37,'np','2022-06-18 22:31:57',0,'2022-06-18 22:31:57'),(70,98,15,'鏌ョ湅鍏叡鐩稿唽璇勮','2022-06-18 22:32:59',0,'2022-06-18 22:32:59'),(71,98,15,'鏌ョ湅鍏叡鐩稿唽璇勮','2022-06-18 22:32:59',0,'2022-06-18 22:32:59'),(72,98,15,'鏌ョ湅鍏叡鐩稿唽璇勮','2022-06-18 22:32:59',0,'2022-06-18 22:32:59'),(73,98,15,'鏌ョ湅鍏叡鐩稿唽璇勮','2022-06-18 22:32:59',0,'2022-06-18 22:32:59'),(74,73,37,'np','2022-06-18 22:43:17',0,'2022-06-18 22:43:17'),(75,73,37,'np','2022-06-18 22:43:17',0,'2022-06-18 22:43:17'),(76,73,37,'np','2022-06-18 22:43:17',0,'2022-06-18 22:43:17'),(77,73,56,'np','2022-06-19 00:04:04',0,'2022-06-19 00:04:04'),(78,73,56,'np','2022-06-19 00:10:54',0,'2022-06-19 00:10:54'),(79,73,56,'np','2022-06-19 00:10:55',0,'2022-06-19 00:10:55'),(80,73,56,'np','2022-06-19 00:10:55',0,'2022-06-19 00:10:55'),(81,73,56,'np','2022-06-19 00:10:55',0,'2022-06-19 00:10:55');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `im2al`
--

DROP TABLE IF EXISTS `im2al`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `im2al` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `album_id` int(11) NOT NULL DEFAULT '0' COMMENT '鐩稿唽id',
  `image_id` int(11) NOT NULL DEFAULT '0',
  `deleted` tinyint(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `modified_time` datetime DEFAULT NULL,
  `image_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `im2al_image_id_fk` (`image_id`),
  KEY `im2al_album_id_index` (`album_id`),
  CONSTRAINT `im2al_album_id_fk` FOREIGN KEY (`album_id`) REFERENCES `album` (`id`),
  CONSTRAINT `im2al_image_id_fk` FOREIGN KEY (`image_id`) REFERENCES `image` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `im2al`
--

LOCK TABLES `im2al` WRITE;
/*!40000 ALTER TABLE `im2al` DISABLE KEYS */;
INSERT INTO `im2al` VALUES (14,4,66,0,'2022-05-22 19:06:08','2022-05-22 19:06:08','2~`STYDNGH013@M[$F]CYKX.png'),(15,4,67,0,'2022-05-22 19:06:08','2022-05-22 19:06:08','85617B670F803C44668504F591CDEC4B.jpg'),(16,4,68,0,'2022-05-22 19:06:08','2022-05-22 19:06:08','QQ鍥剧墖20201009210212.png'),(27,24,69,0,'2022-06-17 21:40:26','2022-06-17 21:40:26','2333.png'),(28,18,69,1,'2022-06-17 22:35:32','2022-06-17 22:35:32','2333.png'),(29,18,69,0,'2022-06-17 22:41:28','2022-06-17 22:41:28','2333.png'),(30,21,69,1,'2022-06-17 23:54:09','2022-06-17 23:54:09','2333.png'),(31,21,69,1,'2022-06-17 23:56:28','2022-06-17 23:56:28','2333.png'),(32,18,69,0,'2022-06-18 00:02:52','2022-06-18 00:02:52','2333.png'),(33,18,69,1,'2022-06-18 00:03:00','2022-06-18 00:03:00','2333.png'),(34,18,69,0,'2022-06-18 00:04:17','2022-06-18 00:04:17','2333.png'),(35,18,69,0,'2022-06-18 00:04:19','2022-06-18 00:04:19','2333.png'),(36,18,69,0,'2022-06-18 00:04:22','2022-06-18 00:04:22','2333.png'),(37,19,69,0,'2022-06-18 11:55:07','2022-06-18 11:55:07','2333.png'),(38,19,69,1,'2022-06-18 11:55:08','2022-06-18 11:55:08','2333.png'),(39,25,69,1,'2022-06-18 15:35:29','2022-06-18 15:35:29','2333.png'),(40,25,69,1,'2022-06-18 15:35:33','2022-06-18 15:35:33','2333.png'),(41,25,69,0,'2022-06-18 15:35:36','2022-06-18 15:35:36','2333.png'),(42,19,70,1,'2022-06-18 19:10:31','2022-06-18 19:10:31','3.png'),(43,29,71,0,'2022-06-18 19:31:51','2022-06-18 19:31:51','鏈€鍧?png'),(44,29,72,0,'2022-06-18 19:31:57','2022-06-18 19:31:57','鎺掑垪.png'),(45,29,73,0,'2022-06-18 19:32:02','2022-06-18 19:32:02','鏈€浣?png'),(46,29,74,0,'2022-06-18 19:32:06','2022-06-18 19:32:06','鏈€鍏?png'),(47,19,75,1,'2022-06-18 19:38:28','2022-06-18 19:38:28','hdy.jpg'),(48,34,76,1,'2022-06-18 20:07:26','2022-06-18 20:07:26','鐝婄憵瀹績娴?.png'),(49,19,77,0,'2022-06-18 20:09:49','2022-06-18 20:09:49','R-C.jpg'),(50,34,78,0,'2022-06-18 20:38:50','2022-06-18 20:38:50','2.png'),(51,31,79,0,'2022-06-18 22:14:43','2022-06-18 22:14:43','01.jpg'),(52,31,80,1,'2022-06-18 22:15:10','2022-06-18 22:15:10','05.jpg'),(53,31,81,0,'2022-06-18 22:15:50','2022-06-18 22:15:50','08.jpg'),(54,31,82,0,'2022-06-18 22:15:58','2022-06-18 22:15:58','07.jpg'),(55,19,75,0,'2022-06-18 23:46:25','2022-06-18 23:46:25','hdy.jpg'),(56,19,69,0,'2022-06-18 23:46:30','2022-06-18 23:46:30','2333.png');
/*!40000 ALTER TABLE `im2al` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`qiao`@`%`*/ /*!50003 trigger after_used_add

    after insert

    on im2al

    for each row

begin

    update user

    set user.used=user.used + (select sum(image.size) from image where image.id = NEW.image_id)

    where user.id = (select album.owner from album where album.id = NEW.album_id);

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`qiao`@`%`*/ /*!50003 trigger after_user_delete

    after update

    on im2al

    for each row

begin

    update user

    set user.used=user.used - (select sum(image.size) from image where image.id = NEW.image_id)

    where user.id = (select album.owner from album where album.id = NEW.album_id)

      and NEW.deleted;

end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `image`
--

DROP TABLE IF EXISTS `image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鍥剧墖id',
  `path` varchar(255) NOT NULL COMMENT '鍥剧墖璺緞',
  `size` int(11) DEFAULT NULL COMMENT '鍥剧墖澶у皬',
  `content_type` varchar(31) DEFAULT NULL COMMENT '鍥剧墖绫诲瀷 image/*',
  `create_time` datetime DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  PRIMARY KEY (`id`),
  UNIQUE KEY `image_path_uindex` (`path`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image`
--

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;
INSERT INTO `image` VALUES (66,'\\images\\fc8c5c991665dc135f54b09530c2fb75.png',731988,'image/png','2022-05-22 19:06:08'),(67,'\\images\\754aee0483b8a5febb43ea9a989b3593.jpg',293987,'image/jpeg','2022-05-22 19:06:08'),(68,'\\images\\6343a1c9d780ee074476139cb8a6ae67.png',1674486,'image/png','2022-05-22 19:06:08'),(69,'\\images\\4fe7e62a9c21ba3340139005717066d6.png',1693856,'image/png','2022-06-17 21:40:26'),(70,'\\images\\4353fba960c3fb06ca4a24330127d114.png',27932,'image/png','2022-06-18 19:10:31'),(71,'\\images\\685f9a9ee0cc21b98c3408cac27c8477.png',120458,'image/png','2022-06-18 19:31:51'),(72,'\\images\\eda73c9da19e6e67573619fd0c4fe666.png',151133,'image/png','2022-06-18 19:31:57'),(73,'\\images\\234f6caac1868f008f3f73a0f07ce7c5.png',119969,'image/png','2022-06-18 19:32:02'),(74,'\\images\\99ba15de23aaf4622b07337ea6a8b661.png',121967,'image/png','2022-06-18 19:32:06'),(75,'\\images\\49942e9c61b70d860544c4013ebba130.jpg',268372,'image/jpeg','2022-06-18 19:38:28'),(76,'\\images\\d230c1215e42b617c4ac315807d815a4.png',315205,'image/png','2022-06-18 20:07:26'),(77,'\\images\\e263c940f8664927db1039ffebbf374d.jpg',97423,'image/jpeg','2022-06-18 20:09:49'),(78,'\\images\\ade558be9cc05d584874b306050e89c7.png',26007,'image/png','2022-06-18 20:38:50'),(79,'\\images\\bd6ad442350c31fc576ebd8dfdf162c8.jpg',47070,'image/jpeg','2022-06-18 22:14:43'),(80,'\\images\\964e8c0120215e1b910df90c2d15a6ea.jpg',46733,'image/jpeg','2022-06-18 22:15:10'),(81,'\\images\\9695ac12983d544fc7e9344f371a51c6.jpg',95944,'image/jpeg','2022-06-18 22:15:50'),(82,'\\images\\e36db16802bf5c2e783c710ea2a687a7.jpg',44723,'image/jpeg','2022-06-18 22:15:58');
/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '鐢ㄦ埛id',
  `name` varchar(31) NOT NULL COMMENT '鐢ㄦ埛鍚?,
  `password` varchar(255) NOT NULL COMMENT '鍔犲瘑瀵嗙爜',
  `used` int(11) DEFAULT '0' COMMENT '宸蹭娇鐢ㄧ┖闂?,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '鍒涘缓鏃堕棿',
  `role` varchar(15) DEFAULT 'user',
  `avatar` int(11) DEFAULT NULL COMMENT '澶村儚鍥剧墖id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (2,'qiao','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'12@fds.com','2022-05-20 11:08:25','user',NULL),(37,'xiaogou','-fvlbj2vale6h2i9767affak5linuts1b',0,'2333@qq.com','2022-06-13 16:00:26','user',NULL),(38,'asdfadsf','cm6aiuoase28tdvk8d22050a50d4kenf',0,'12123@11.com','2022-06-13 16:22:58','user',NULL),(42,'asdfads','cm6aiuoase28tdvk8d22050a50d4kenf',0,'12123@11.com','2022-06-13 16:23:26','user',NULL),(44,'2333j','fumb4tpjn74tlqkvtkoihb74h7q955bp',0,'5555@qq.com','2022-06-13 16:29:09','user',NULL),(45,'asdfadf','cm6aiuoase28tdvk8d22050a50d4kenf',0,'123@11.com','2022-06-13 16:36:51','user',NULL),(50,'asdfdf','cm6aiuoase28tdvk8d22050a50d4kenf',0,'sdsdsd3@11.com','2022-06-13 16:37:33','user',NULL),(56,'12334','-etee5q18uornsotdpeqb9rsqrf9prcvs',0,'55sdf5@qq.com','2022-06-13 16:40:28','user',NULL),(58,'sbcmx','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'asdf@qq.com','2022-06-13 17:31:04','user',NULL),(59,'sbcmxd','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'asdf@qq.com','2022-06-13 17:32:03','user',NULL),(60,'sbcmxddd','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'asdf@qq.com','2022-06-13 17:40:46','user',NULL),(61,'sbmxddd','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'asdf@qq.com','2022-06-13 17:41:19','user',NULL),(63,'sbmd','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'asdf@qq.com','2022-06-13 17:49:57','user',NULL),(64,'sbmdsd','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'asdf@qq.com','2022-06-13 17:52:32','user',NULL),(65,'qwew','7cap6s5e6vrj93q0cgkdu23euuh8700j',0,'wewew@sds.com','2022-06-13 17:53:27','user',NULL),(66,'sdfads','-1rn15v27nt88g04e78hb4riqunn40n4d',0,'sadfsa@qq.com','2022-06-13 17:53:48','user',NULL),(70,'asdf','-1ojro2b0js4fhvab2pcsp4h59lrr6q4b',0,'23@qq.com','2022-06-13 18:01:59','user',NULL),(72,'cmxxlg','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'123@11.com','2022-06-13 18:03:46','user',NULL),(73,'cmxxlj','-er87afe6mrrtprordlu8d4ljpg44asmu',4267034,'123@11.com','2022-06-13 18:11:44','user',NULL),(75,'lalala','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'123@123.com','2022-06-18 18:39:35','user',NULL),(77,'lalal','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'123@123.com','2022-06-18 18:39:48','user',NULL),(81,'sdcmx','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'123@qq.com','2022-06-18 18:41:29','user',NULL),(94,'sdsd','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'132@qq.com','2022-06-18 18:43:32','user',NULL),(95,'asdfas','24d8q9d208ifhnc2n3661i77dr4iflv0',0,'asdfasdf@qq.com','2022-06-18 18:51:20','user',NULL),(96,'adsf','2j5kq9pg26jbhbblrnvdutnhmrt4n56d',0,'adsf@qq.com','2022-06-18 18:52:19','user',NULL),(97,'qing','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'1581630687@qq.com','2022-06-18 19:01:51','user',NULL),(98,'鐝婄憵瀹績娴?,'-er87afe6mrrtprordlu8d4ljpg44asmu',213744,'2746125624@qq.com','2022-06-18 19:45:58','user',NULL),(102,'asada','-9gdvdpbj8nbmout5h2l80hjr42p5183s',0,'463556483@qq.com','2022-06-18 22:12:27','user',NULL),(103,'蹇冩捣','-er87afe6mrrtprordlu8d4ljpg44asmu',0,'2746125624@qq.com','2022-06-18 22:12:54','user',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-19 13:52:53
