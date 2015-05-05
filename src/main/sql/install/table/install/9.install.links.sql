CREATE TABLE `links` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `url` VARCHAR(1024) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `new_window` TINYINT(1) NULL DEFAULT '1' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;
