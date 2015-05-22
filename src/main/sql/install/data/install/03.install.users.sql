DELETE FROM `users`;

INSERT INTO `users` (`id`, `email`, `name`, `password`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (1, 'michael.wang.brisbane@gmail.com', 'Michael Wang', '6ba174f917482b9e881938ff194ffb159c46b2807dd5b7e1342587210532be1b', 'Michael Wang', NOW(), NULL);
INSERT INTO `users` (`id`, `email`, `name`, `password`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (2, 'zhenyi.zhao@gmail.com', 'Zhenyi Pang', 'd335d06aa1db52a0db83821d6b4ce999bd86298278a5741032e915801c406299', 'Zhenyi Pang', NOW(), NULL);
