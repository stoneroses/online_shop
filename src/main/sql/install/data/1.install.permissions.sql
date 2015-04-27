DELETE FROM `permissions`;

INSERT INTO `permissions` (`id`, `name`, `permission`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (1, 'create news', 'news:create', 'create news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `permission`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (2, 'save news', 'news:save', 'save news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `permission`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (3, 'edit news', 'news:edit', 'edit news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `permission`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (4, 'delete news', 'news:delete', 'delete news', NOW(), NULL);

