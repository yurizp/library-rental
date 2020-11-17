CREATE TABLE `book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(120) DEFAULT NULL,
  `description` varchar(3000) NOT NULL,
  `isbn_13` varchar(20) DEFAULT NULL,
  `isbn_10` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `book_properties` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `key` varchar(45) NOT NULL,
  `value` varchar(2000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_book_properties_book_id_idx` (`book_id`),
  CONSTRAINT `fk_book_properties_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(145) NOT NULL,
  `birthday` date NOT NULL,
  `gender` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `rental_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `book_id` int NOT NULL,
  `status` varchar(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_rental_book_book_id_idx` (`book_id`),
  CONSTRAINT `fk_rental_book_book_id` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `rental_book_client` (
  `id` int NOT NULL AUTO_INCREMENT,
  `rental_book_id` int NOT NULL,
  `client_id` int NOT NULL,
  `lented_date` date NOT NULL,
  `returned_date` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_rental_book_client_client_id_idx` (`client_id`),
  KEY `fk_rental_book_client_rental_book_id_idx` (`rental_book_id`),
  CONSTRAINT `fk_rental_book_client_client_id` FOREIGN KEY (`client_id`) REFERENCES `client` (`id`),
  CONSTRAINT `fk_rental_book_client_rental_book_id` FOREIGN KEY (`rental_book_id`) REFERENCES `rental_book` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

CREATE TABLE `rental_tax` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `days_arrear` int NOT NULL,
  `daily_rate` double NOT NULL,
  `penalty` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
