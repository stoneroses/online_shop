CREATE TABLE `products` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `reference` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `weight` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  `stock` INT(11) NOT NULL DEFAULT '0',
  `price` DECIMAL(10,2) NOT NULL DEFAULT '0.00',
  `discount` DECIMAL(4,2) NOT NULL DEFAULT '0.00',
  `display_price` TINYINT(1) NOT NULL DEFAULT '0',
  `description` VARCHAR(4096) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `sort_order` INT(11) NULL DEFAULT '0',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
AUTO_INCREMENT=97
;
