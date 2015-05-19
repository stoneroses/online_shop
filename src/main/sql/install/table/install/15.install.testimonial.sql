CREATE TABLE `testimonials` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `title` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `content` VARCHAR(2048) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
AUTO_INCREMENT=3
;
