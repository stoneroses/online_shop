DELETE FROM `settings`;

INSERT INTO `settings` (`setting_key`, `setting_value`, `createdDateTime`, `updatedDateTime`) VALUES ('about_us', 'Default about us message. Please edit about_us settings instead.', NOW(), NULL);
INSERT INTO `settings` (`setting_key`, `setting_value`, `createdDateTime`, `updatedDateTime`) VALUES ('contact_us', 'Default contact us message. Please edit contact_us settings instead.', NOW(), NULL);
