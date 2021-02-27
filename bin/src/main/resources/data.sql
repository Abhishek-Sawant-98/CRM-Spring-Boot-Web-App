USE `crm`;

DELETE FROM `user` WHERE `user_id` = 'user001';

INSERT INTO `user` (`user_id`,`password`,`first_name`,`last_name`,`date_of_birth`,`gender`,`contact_number`,`email_id`,`job_title`,`company`,`employee_count`,`country`)
VALUES ('user001','Atul@xyz1','Atul','Nigam','1996-02-02','Male','9876543210','atulngm@gmail.com','Software developer','Cognizant','1001-10000','India');
