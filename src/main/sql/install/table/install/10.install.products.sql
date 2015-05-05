CREATE TABLE `products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `reference` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `weight` DECIMAL(10, 2) NOT NULL DEFAULT 0,
  `stock` INT(11) NOT NULL DEFAULT 0,
  `price` DECIMAL(10, 2) NOT NULL DEFAULT 0,
  `discount` DECIMAL(4, 2) NOT NULL DEFAULT 0,
  `description` VARCHAR(1024) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;
