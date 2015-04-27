DELETE FROM `permissions`;

INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (1, 'news_create', 'create news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (2, 'news_save', 'save news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (3, 'news_edit', 'edit news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (4, 'news_delete', 'delete news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (5, 'news_view', 'delete news', NOW(), NULL);

INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (6, 'role_create', 'create role', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (7, 'role_save', 'save role', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (8, 'role_edit', 'edit role', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (9, 'role_delete', 'delete role', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (10, 'role_view', 'delete role', NOW(), NULL);

