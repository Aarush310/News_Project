CREATE TABLE OrderTable
(
orderId INT AUTO_INCREMENT PRIMARY KEY,
customerId INT NOT NULL,
publicationId INT NOT NULL,
deliveryDays VARCHAR(27) NOT NULL,
CONSTRAINT uq_order_customer_publication UNIQUE (customerId, publicationId),
CONSTRAINT fk_order_customer FOREIGN KEY (customerId) REFERENCES CustomerTable (customerId),
CONSTRAINT fk_order_publication FOREIGN KEY (publicationId) REFERENCES PublicationTable (publicationId)
);
