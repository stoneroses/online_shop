DELETE FROM `users`;

INSERT INTO `users` (`id`, `email`, `name`, `password`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (1, 'michael.wang.brisbane@gmail.com', 'Michael Wang', '6ba174f917482b9e881938ff194ffb159c46b2807dd5b7e1342587210532be1b', 'Michael Wang', NOW(), NULL);
INSERT INTO `users` (`id`, `email`, `name`, `password`, `description`, `createdDateTime`, `updatedDateTime`) VALUES (2, 'cnlancer@gmail.com', 'cnlancer', 'password', '7a8c8739df2906dfd866a776cca6f2a0355e2bdbe9743b614f7e3d07c263aa74', NOW(), NULL);
