-- Show order IDs by customer name (two tables connected: CustomerTable JOIN OrderTable)
-- Run in MySQL Workbench after schema.sql. Change 'Aarush' to any first name, surname or full name.
USE newsagentdb;

SELECT o.orderId,
       c.customerId,
       CONCAT(c.firstName, ' ', c.surname) AS customerName,
       o.publicationId,
       o.deliveryDays
FROM OrderTable o
JOIN CustomerTable c ON o.customerId = c.customerId
WHERE c.firstName LIKE '%Aarush%'
   OR c.surname LIKE '%Aarush%'
   OR CONCAT(c.firstName, ' ', c.surname) LIKE '%Aarush%'
ORDER BY o.orderId;
