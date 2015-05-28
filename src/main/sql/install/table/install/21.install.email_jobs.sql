CREATE TABLE `email_jobs` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `content` VARCHAR(4096) NOT NULL DEFAULT '' COLLATE 'utf8_bin',
  `activated` TINYINT(1) NULL DEFAULT '1' COLLATE 'utf8_bin',
  `createdDateTime` DATETIME NOT NULL,
  `updatedDateTime` DATETIME NULL DEFAULT NULL,
  `executedDateTime` DATETIME NULL DEFAULT NULL,
  `success` INT(11) NOT NULL DEFAULT '0',
  `failure` INT(11) NOT NULL DEFAULT '0',
  INDEX `id` (`id`)
)
COLLATE='utf8_bin'
ENGINE=InnoDB
;
