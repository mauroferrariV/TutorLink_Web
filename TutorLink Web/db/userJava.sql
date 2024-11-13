--
-- User java
--

CREATE USER 'java'@'localhost' IDENTIFIED BY '123';
GRANT ALL PRIVILEGES ON tutorlink.* TO 'java'@'localhost';
FLUSH PRIVILEGES;
