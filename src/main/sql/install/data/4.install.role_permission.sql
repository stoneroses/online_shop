DELETE FROM `role_permission`;

INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_create';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_save';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_edit';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_delete';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_view';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 1, p.id FROM `permissions` p WHERE `name` LIKE '%_list';

INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_create' AND `name` NOT LIKE 'permission_%' AND `name` NOT LIKE 'role_%' AND `name` NOT LIKE 'user_%';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_save' AND `name` NOT LIKE 'permission_%' AND `name` NOT LIKE 'role_%' AND `name` NOT LIKE 'user_%';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_edit' AND `name` NOT LIKE 'permission_%' AND `name` NOT LIKE 'role_%' AND `name` NOT LIKE 'user_%';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_view' AND `name` NOT LIKE 'permission_%' AND `name` NOT LIKE 'role_%' AND `name` NOT LIKE 'user_%';
INSERT INTO `role_permission` (`role_id`, `permission_id`) SELECT 2, p.id FROM `permissions` p WHERE `name` LIKE '%_list' AND `name` NOT LIKE 'permission_%' AND `name` NOT LIKE 'role_%' AND `name` NOT LIKE 'user_%';

