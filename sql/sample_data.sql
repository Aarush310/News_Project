-- Newsagent Delivery System: realistic sample data (Athlone, Co. Westmeath)
-- Run AFTER sql/schema.sql. Safe to run again: rows that already exist are skipped (INSERT IGNORE).
-- Prices are typical Irish cover prices in euro.

USE newsagentdb;

-- Delivery Areas (deliveryPersonId = staff number of the person doing the round)
INSERT IGNORE INTO DeliveryAreaTable (areaNumber, areaName, deliveryPersonId) VALUES
(1, 'Athlone Town Centre', 101),
(2, 'Monksland',           102),
(3, 'Coosan',              103),
(4, 'Retreat Road',        104),
(5, 'Garrycastle',         105),
(6, 'Willow Park',         106);

-- Customers
INSERT IGNORE INTO CustomerTable (customerId, firstName, surname, address, phoneNumber, deliveryArea) VALUES
(1,  'Mary',    'Kelly',     '12 Church Street, Athlone',            '0871234567', 1),
(2,  'John',    'Murphy',    '4 Connaught Street, Athlone',          '0852345678', 1),
(3,  'Aoife',   'Byrne',     '27 Monksland Avenue, Monksland',       '0863456789', 2),
(4,  'Patrick', 'Walsh',     '9 Cloonbrusk Road, Monksland',         '0834567890', 2),
(5,  'Siobhan', 'O''Brien',  '15 Coosan Point Road, Coosan',         '0875678901', 3),
(6,  'Michael', 'Doyle',     '3 Lakeview Drive, Coosan',             '0856789012', 3),
(7,  'Niamh',   'Gallagher', '41 Retreat Heights, Retreat Road',     '0867890123', 4),
(8,  'Sean',    'Brennan',   '6 Retreat Park, Retreat Road',         '0838901234', 4),
(9,  'Ciara',   'Fitzgerald','22 Garrycastle Court, Garrycastle',    '0879012345', 5),
(10, 'Declan',  'Moran',     '18 Dublin Road, Garrycastle',          '0850123456', 5),
(11, 'Orla',    'Keane',     '7 Willow Park Grove, Willow Park',     '0861122334', 6),
(12, 'Eamon',   'Hughes',    '33 Willow Park Road, Willow Park',     '0832233445', 6);

-- Publications
INSERT IGNORE INTO PublicationTable (publicationId, name, type, frequency, price, quantityInStock) VALUES
(1,  'Irish Independent',        'newspaper', 'daily',   3.00, 120),
(2,  'The Irish Times',          'newspaper', 'daily',   3.30, 90),
(3,  'Irish Daily Star',         'newspaper', 'daily',   2.00, 80),
(4,  'Irish Examiner',           'newspaper', 'daily',   3.00, 40),
(5,  'Westmeath Independent',    'newspaper', 'weekly',  2.50, 150),
(6,  'Sunday Independent',       'newspaper', 'weekly',  3.80, 110),
(7,  'Sunday World',             'newspaper', 'weekly',  2.80, 70),
(8,  'Irish Farmers Journal',    'newspaper', 'weekly',  4.20, 35),
(9,  'RTE Guide',                'magazine',  'weekly',  2.99, 60),
(10, 'Ireland''s Own',           'magazine',  'weekly',  2.80, 30),
(11, 'Irish Tatler',             'magazine',  'monthly', 4.95, 20),
(12, 'National Geographic',      'magazine',  'monthly', 7.99, 15);

-- Orders (delivery days match how often each publication comes out:
-- daily papers Mon-Sat, Sunday papers on SUN, the Westmeath Independent on WED, monthlies on one day)
INSERT IGNORE INTO OrderTable (orderId, customerId, publicationId, deliveryDays) VALUES
(1,  1,  1,  'MON,TUE,WED,THU,FRI,SAT'),
(2,  1,  6,  'SUN'),
(3,  1,  5,  'WED'),
(4,  2,  2,  'MON,TUE,WED,THU,FRI,SAT'),
(5,  2,  9,  'TUE'),
(6,  3,  1,  'MON,TUE,WED,THU,FRI'),
(7,  3,  11, 'SAT'),
(8,  4,  3,  'MON,TUE,WED,THU,FRI,SAT'),
(9,  4,  7,  'SUN'),
(10, 5,  2,  'SAT'),
(11, 5,  12, 'FRI'),
(12, 5,  5,  'WED'),
(13, 6,  8,  'THU'),
(14, 6,  6,  'SUN'),
(15, 7,  1,  'MON,WED,FRI'),
(16, 7,  10, 'TUE'),
(17, 8,  4,  'MON,TUE,WED,THU,FRI,SAT'),
(18, 9,  2,  'MON,TUE,WED,THU,FRI,SAT'),
(19, 9,  6,  'SUN'),
(20, 10, 3,  'SAT'),
(21, 10, 5,  'WED'),
(22, 11, 1,  'MON,TUE,WED,THU,FRI,SAT'),
(23, 11, 9,  'TUE'),
(24, 12, 7,  'SUN'),
(25, 12, 8,  'THU');

-- Delivery Dockets (one PENDING and one DELIVERED example)
INSERT IGNORE INTO DeliveryDocketTable (docketId, areaNumber, docketDate, status) VALUES
(1, 1, '2026-11-11', 'DELIVERED'),
(2, 1, '2026-11-18', 'PENDING');
