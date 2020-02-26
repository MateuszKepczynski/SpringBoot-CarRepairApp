DROP DATABASE  IF EXISTS `car_shop`;

CREATE DATABASE  IF NOT EXISTS `car_shop`;
USE `car_shop`;

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
	`id` int NOT NULL AUTO_INCREMENT,
	`first_name` varchar(255),
	`last_name` varchar(255),
    `city` varchar(255),
	`address` varchar(255),
	`phone_number` varchar(255),
	`username` varchar(255) NOT NULL UNIQUE,
	`password` char(255) NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `owner` (
	`id` int NOT NULL AUTO_INCREMENT,
	`first_name` varchar(255),
	`last_name` varchar(255),
	`address` varchar(255),
	`phone_number` varchar(255),
	`city` varchar(255),
	`id_employee` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `vehicle` (
	`id` int NOT NULL AUTO_INCREMENT,
	`brand` varchar(255),
	`numbers` varchar(255),
    `status` varchar(255),
	`id_client` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `visit` (
	`id` int NOT NULL AUTO_INCREMENT,
	`date` varchar(255),
	`description` varchar(255),
	`id_car` int NOT NULL,
	PRIMARY KEY (`id`)
);

CREATE TABLE `user_message` (
	`id` int NOT NULL AUTO_INCREMENT,
	`message` varchar(255),
	`client_phone_number` varchar(255),
	`id_user` int NOT NULL,
	PRIMARY KEY (`id`)
);

ALTER TABLE `owner` ADD CONSTRAINT `owner_fk0` FOREIGN KEY (`id_employee`) REFERENCES `user`(`id`);

ALTER TABLE `vehicle` ADD CONSTRAINT `vehicle_fk0` FOREIGN KEY (`id_client`) REFERENCES `owner`(`id`);

ALTER TABLE `visit` ADD CONSTRAINT `visit_fk0` FOREIGN KEY (`id_car`) REFERENCES `vehicle`(`id`);

ALTER TABLE `user_message` ADD CONSTRAINT `user_message_fk0` FOREIGN KEY (`id_user`) REFERENCES `user`(`id`);

INSERT INTO `user` (username,password,first_name,last_name)
VALUES 
('john','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe'),
('mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public'),
('susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Susan','Adams');


DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


INSERT INTO `role` (name)
VALUES 
('ROLE_EMPLOYEE'),('ROLE_MANAGER'),('ROLE_ADMIN');


DROP TABLE IF EXISTS `users_roles`;

CREATE TABLE `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_ROLE_idx` (`role_id`),
  
  CONSTRAINT `FK_USER_05` FOREIGN KEY (`user_id`) 
  REFERENCES `user` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `role` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 1),
(2, 1),
(2, 2),
(3, 1),
(3, 2),
(3, 3)