CREATE TABLE CustomerTable
(
customerId INT AUTO_INCREMENT PRIMARY KEY,
firstName VARCHAR(50) NOT NULL,
surname VARCHAR(50) NOT NULL,
address VARCHAR(150) NOT NULL,
phoneNumber VARCHAR(10) NOT NULL,
deliveryArea INT NOT NULL,
CONSTRAINT chk_customer_area CHECK (deliveryArea BETWEEN 1 AND 24)
);
