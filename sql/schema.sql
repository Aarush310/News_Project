-- Newsagent Delivery System: tables for the 5 entities
-- Run once in MySQL Workbench. Order and Delivery Docket are last because they link to the other tables.
-- Then run sql/sample_data.sql for realistic test data.

CREATE DATABASE IF NOT EXISTS newsagentdb;
USE newsagentdb;

-- Customer
CREATE TABLE IF NOT EXISTS CustomerTable
(
    customerId INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL,
    address VARCHAR(150) NOT NULL,
    phoneNumber VARCHAR(10) NOT NULL,
    deliveryArea INT NOT NULL,
    CONSTRAINT chk_customer_area CHECK (deliveryArea BETWEEN 1 AND 24)
);

-- Publication
CREATE TABLE IF NOT EXISTS PublicationTable
(
    publicationId INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    type VARCHAR(10) NOT NULL,
    frequency VARCHAR(10) NOT NULL,
    price DECIMAL(6,2) NOT NULL,
    quantityInStock INT NOT NULL,
    CONSTRAINT uq_publication_name UNIQUE (name),
    CONSTRAINT chk_publication_type CHECK (type IN ('newspaper', 'magazine')),
    CONSTRAINT chk_publication_frequency CHECK (frequency IN ('daily', 'weekly', 'monthly')),
    CONSTRAINT chk_publication_price CHECK (price > 0),
    CONSTRAINT chk_publication_stock CHECK (quantityInStock >= 0)
);

-- Delivery Area
CREATE TABLE IF NOT EXISTS DeliveryAreaTable
(
    areaNumber INT PRIMARY KEY,
    areaName VARCHAR(50) NOT NULL,
    deliveryPersonId INT NOT NULL,
    CONSTRAINT chk_area_number CHECK (areaNumber BETWEEN 1 AND 24)
);

-- Order
CREATE TABLE IF NOT EXISTS OrderTable
(
    orderId INT AUTO_INCREMENT PRIMARY KEY,
    customerId INT NOT NULL,
    publicationId INT NOT NULL,
    deliveryDays VARCHAR(27) NOT NULL,
    CONSTRAINT uq_order_customer_publication UNIQUE (customerId, publicationId),
    CONSTRAINT fk_order_customer FOREIGN KEY (customerId) REFERENCES CustomerTable (customerId),
    CONSTRAINT fk_order_publication FOREIGN KEY (publicationId) REFERENCES PublicationTable (publicationId)
);

-- Delivery Docket (one per delivery area per day)
CREATE TABLE IF NOT EXISTS DeliveryDocketTable
(
    docketId INT AUTO_INCREMENT PRIMARY KEY,
    areaNumber INT NOT NULL,
    docketDate DATE NOT NULL,
    status VARCHAR(10) NOT NULL DEFAULT 'PENDING',
    CONSTRAINT uq_docket_area_date UNIQUE (areaNumber, docketDate),
    CONSTRAINT chk_docket_status CHECK (status IN ('PENDING', 'DELIVERED')),
    CONSTRAINT fk_docket_area FOREIGN KEY (areaNumber) REFERENCES DeliveryAreaTable (areaNumber) ON DELETE CASCADE
);
