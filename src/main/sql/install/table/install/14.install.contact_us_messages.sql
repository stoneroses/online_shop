CREATE TABLE `contact_us_messages` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(128) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `first_name` VARCHAR(128) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `last_name` VARCHAR(128) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `phone` VARCHAR(128) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `email` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `subject` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `content` VARCHAR(2048) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
AUTO_INCREMENT=11
;
