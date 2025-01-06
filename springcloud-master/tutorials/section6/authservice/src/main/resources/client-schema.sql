
--
-- Table structure for table `oauth_client_details`
--

-- DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int DEFAULT NULL,
  `refresh_token_validity` int DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('accountservice',NULL,'$2a$10$LvG9.2aFGMyWcKFBDHmDb.j84EX059Q5elQaxpXgeSWNHr5tsL/Zm','notification,log','client_credentials','',NULL,300,300,NULL,'true'),('jmaster',NULL,'$2a$10$LvG9.2aFGMyWcKFBDHmDb.j84EX059Q5elQaxpXgeSWNHr5tsL/Zm','read,write','password,refresh_token,implicit,client_credentials,authorization_code','https://oauthdebugger.com/debug,http://127.0.0.1:5500,http://127.0.0.1:3000/login',NULL,3600,36000,NULL,'true');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;
