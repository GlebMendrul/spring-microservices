DROP TABLE IF EXISTS `ClientDetails`;
CREATE TABLE `ClientDetails` (
  `appId`                  VARCHAR(256) NOT NULL,
  `resourceIds`            VARCHAR(256)  DEFAULT NULL,
  `appSecret`              VARCHAR(256)  DEFAULT NULL,
  `scope`                  VARCHAR(256)  DEFAULT NULL,
  `grantTypes`             VARCHAR(256)  DEFAULT NULL,
  `redirectUrl`            VARCHAR(256)  DEFAULT NULL,
  `authorities`            VARCHAR(256)  DEFAULT NULL,
  `access_token_validity`  INT(11)       DEFAULT NULL,
  `refresh_token_validity` INT(11)       DEFAULT NULL,
  `additionalInformation`  VARCHAR(4096) DEFAULT NULL,
  PRIMARY KEY (`appId`)
);


DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username`  VARCHAR(50) NOT NULL,
  `authority` VARCHAR(50) NOT NULL,
  UNIQUE KEY `ix_auth_username` (`username`, `authority`)
);


DROP TABLE IF EXISTS `oauth_access_token`;
CREATE TABLE `oauth_access_token` (
  `token_id`          VARCHAR(256) DEFAULT NULL,
  `token`             BLOB,
  `authentication_id` VARCHAR(256) DEFAULT NULL,
  `user_name`         VARCHAR(256) DEFAULT NULL,
  `client_id`         VARCHAR(256) DEFAULT NULL,
  `authentication`    BLOB,
  `refresh_token`     VARCHAR(256) DEFAULT NULL
);


DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details` (
  `client_id`               VARCHAR(256) NOT NULL,
  `resource_ids`            VARCHAR(256)  DEFAULT NULL,
  `client_secret`           VARCHAR(256)  DEFAULT NULL,
  `scope`                   VARCHAR(256)  DEFAULT NULL,
  `authorized_grant_types`  VARCHAR(256)  DEFAULT NULL,
  `web_server_redirect_uri` VARCHAR(256)  DEFAULT NULL,
  `authorities`             VARCHAR(256)  DEFAULT NULL,
  `access_token_validity`   INT(11)       DEFAULT NULL,
  `refresh_token_validity`  INT(11)       DEFAULT NULL,
  `additional_information`  VARCHAR(4096) DEFAULT NULL,
  `autoapprove`             VARCHAR(256)  DEFAULT NULL,
  PRIMARY KEY (`client_id`)
);


DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code`           VARCHAR(256) DEFAULT NULL,
  `authentication` BLOB
);

DROP TABLE IF EXISTS `oauth_refresh_token`;
CREATE TABLE `oauth_refresh_token` (
  `token_id`       VARCHAR(256) DEFAULT NULL,
  `token`          BLOB,
  `authentication` BLOB
);

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` VARCHAR(120) NOT NULL,
  `password` VARCHAR(120) NOT NULL,
  `enabled`  TINYINT (1) NOT NULL,
  PRIMARY KEY (`username`)
);

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  FOREIGN KEY  (username) REFERENCES users (username));

INSERT INTO users(username,password,enabled)
VALUES ('admin','$2a$10$T9JW1AWa2jH.Li3fMXNII.u20OT0miW5hDsasLLOnoxvKNtmktlMC', true);
INSERT INTO users(username,password,enabled)
VALUES ('user','$2a$10$T9JW1AWa2jH.Li3fMXNII.u20OT0miW5hDsasLLOnoxvKNtmktlMC', true);

INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO user_roles (username, role)
VALUES ('user', 'ROLE_USER');