CREATE DATABASE IF NOT EXISTS newsagent;
USE newsagent;

CREATE TABLE IF NOT EXISTS publication (
    publication_id    INT AUTO_INCREMENT PRIMARY KEY,
    name              VARCHAR(50)  NOT NULL,
    type              ENUM('newspaper', 'magazine') NOT NULL,
    frequency         ENUM('daily', 'weekly', 'monthly') NOT NULL,
    price             DECIMAL(6,2) NOT NULL,
    quantity_in_stock INT NOT NULL DEFAULT 0
);
