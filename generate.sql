CREATE SCHEMA `transportation` ;

use transportation;

CREATE TABLE `transportation`.`user_role` (
  `user_id` INT NOT NULL,
  `roles` VARCHAR(45) NULL DEFAULT NULL);

CREATE TABLE `transportation`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  active boolean NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `transportation`.`point` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45),
  `way_id` INT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `transportation`.`transport` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `max_capacity` DOUBLE NOT NULL,
  `unit_cost` DOUBLE NOT NULL,
  `speed` DOUBLE NOT NULL,
  PRIMARY KEY (`id`));

CREATE TABLE `transportation`.`way` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `start_point_id` INT NOT NULL,
  `end_point_id` INT NOT NULL,
  `length` DOUBLE NULL,
  `transport_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `start_point_id_idx` (`start_point_id` ASC) VISIBLE,
  INDEX `end_point_id_idx` (`end_point_id` ASC) VISIBLE,
  INDEX `transport_id_idx` (`transport_id` ASC) VISIBLE,
  CONSTRAINT `start_point_id`
    FOREIGN KEY (`start_point_id`)
    REFERENCES `transportation`.`point` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `end_point_id`
    FOREIGN KEY (`end_point_id`)
    REFERENCES `transportation`.`point` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `transport_id`
	FOREIGN KEY (`transport_id`)
	REFERENCES `transportation`.`transport` (`id`)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION);

ALTER TABLE `transportation`.`point`
ADD INDEX `way_id_idx` (`way_id` ASC) VISIBLE;
;
ALTER TABLE `transportation`.`point`
ADD CONSTRAINT `way_id`
  FOREIGN KEY (`way_id`)
  REFERENCES `transportation`.`way` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE transport ADD fragile double NOT NULL;
ALTER TABLE transport ADD perishable double NOT NULL;
ALTER TABLE transport ADD dangerous double NOT NULL;

CREATE TABLE request
(
    id int PRIMARY KEY NOT NULL AUTO_INCREMENT,
    way_id int NOT NULL,
    user_id int NOT NULL,
    weight double NOT NULL,
    cost double NOT NULL,
    complete boolean NOT NULL,
    CONSTRAINT request_way FOREIGN KEY (way_id) REFERENCES way (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT request_user FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE ON UPDATE CASCADE
);