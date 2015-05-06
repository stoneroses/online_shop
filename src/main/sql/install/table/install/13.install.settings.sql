CREATE TABLE `settings` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `setting_key` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `setting_value` VARCHAR(1024) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;
