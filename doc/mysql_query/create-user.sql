CREATE USER 'yestory_user'@'localhost' IDENTIFIED BY '1q2w3e4r';
GRANT ALL PRIVILEGES ON yestory.* TO 'yestory_user'@'localhost';
FLUSH PRIVILEGES;