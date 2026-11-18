
-- Order Integration Tests
-- Checks Customer, Publication and Order relationships
-- These queries do not change or delete data.

USE newsagentdb;

-- TEST 1: Check all publications
SELECT * FROM PublicationTable;

-- TEST 2: Check all orders
SELECT * FROM OrderTable;

-- TEST 3: Check orders with customer and publication details
SELECT
    o.orderId,
    c.firstName,
    c.surname,
    p.name AS publication,
    o.deliveryDays
FROM OrderTable o
JOIN CustomerTable c
    ON o.customerId = c.customerId
JOIN PublicationTable p
    ON o.publicationId = p.publicationId
ORDER BY o.orderId;

-- TEST 4: Find Order IDs using customer name
SELECT
    o.orderId,
    c.firstName,
    c.surname,
    p.name AS publication,
    o.deliveryDays
FROM OrderTable o
JOIN CustomerTable c
    ON o.customerId = c.customerId
JOIN PublicationTable p
    ON o.publicationId = p.publicationId
WHERE c.firstName = 'Mary'
AND c.surname = 'Kelly';

-- TEST 5: Find orders using surname
SELECT
    o.orderId,
    c.firstName,
    c.surname,
    p.name AS publication
FROM OrderTable o
JOIN CustomerTable c
    ON o.customerId = c.customerId
JOIN PublicationTable p
    ON o.publicationId = p.publicationId
WHERE c.surname = 'Murphy';

-- TEST 6: Check for invalid Customer IDs
SELECT o.*
FROM OrderTable o
LEFT JOIN CustomerTable c
    ON o.customerId = c.customerId
WHERE c.customerId IS NULL;

-- TEST 7: Check for invalid Publication IDs
SELECT o.*
FROM OrderTable o
LEFT JOIN PublicationTable p
    ON o.publicationId = p.publicationId
WHERE p.publicationId IS NULL;

-- TEST 8: Check duplicate customer-publication orders
SELECT customerId, publicationId, COUNT(*) AS total
FROM OrderTable
GROUP BY customerId, publicationId
HAVING COUNT(*) > 1;

-- TEST 9: Check the number of records
SELECT 'Customers' AS tableName, COUNT(*) AS total
FROM CustomerTable
UNION ALL
SELECT 'Publications', COUNT(*) FROM PublicationTable
UNION ALL
SELECT 'Orders', COUNT(*) FROM OrderTable;
