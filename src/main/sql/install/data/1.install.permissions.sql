DELETE FROM `permissions`;

INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (1, 'news_create', 'create news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (2, 'news_save', 'save news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (3, 'news_edit', 'edit news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (4, 'news_delete', 'delete news', NOW(), NULL);
INSERT INTO `permissions` (`id`, `name`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (5, 'news_view', 'delete news', NOW(), NULL);

