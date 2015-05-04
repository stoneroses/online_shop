DELETE FROM `role_permission`;

INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_create';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_save';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_edit';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_delete';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_view';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_list';

INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_create';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_save';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_edit';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_view';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_list';
