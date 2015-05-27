CREATE TABLE `customers` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `hashed_email` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `title` VARCHAR(256) NULL DEFAULT '' COLLATE 'utf8_bin',
  `name` VARCHAR(256) NULL DEFAULT '' COLLATE 'utf8_bin',
  `subscribe` TINYINT(1) NULL DEFAULT '1' COLLATE 'utf8_bin',
  `description` VARCHAR(1024) NULL DEFAULT '' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;
