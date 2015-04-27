DELETE FROM `roles`;

INSERT INTO `roles` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (1, 'admin', 'admin role', NOW(), NULL);
INSERT INTO `roles` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (2, 'user', 'user role', NOW(), NULL);
