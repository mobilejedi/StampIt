-- script for creating the STAMPIT database users --
CREATE USER 'app_user'@'localhost' IDENTIFIED BY 'test';
GRANT SELECT, INSERT, UPDATE, DELETE ON STAMPIT.* TO 'jeffrey'@'localhost';
CREATE USER 'admin_user'@'localhost' IDENTIFIED BY 'test';
GRANT ALL ON STAMPIT.*, INFORMATION.* TO 'admin_user'@'localhost';