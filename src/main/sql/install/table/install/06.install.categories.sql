CREATE TABLE `categories` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `description` VARCHAR(1024) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `sort_order` INT(11) NULL DEFAULT NULL,
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  `parent_id` INT(11) NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
AUTO_INCREMENT=12
;
