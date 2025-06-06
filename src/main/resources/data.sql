-- Data SQL for visit-microservice with business rules compliance
-- Insert sample appointment slots with proper seller-house relationships and valid dates

INSERT IGNORE INTO appointment_slot (id, start_time, end_time, seller_id, house_id) VALUES 
-- Future appointment slots (within next 3 weeks from current date: June 6, 2025)
-- Casa 1: seller_id=2 (from property-microservice)
(1, '2025-06-09 10:00:00', '2025-06-09 11:00:00', 2, 1),
(2, '2025-06-09 14:00:00', '2025-06-09 15:00:00', 2, 1),
-- Casa 2: seller_id=3
(3, '2025-06-09 16:00:00', '2025-06-09 17:00:00', 3, 2),
(4, '2025-06-10 09:00:00', '2025-06-10 10:00:00', 3, 2),
-- Casa 3: seller_id=4
(5, '2025-06-10 11:00:00', '2025-06-10 12:00:00', 4, 3),
(6, '2025-06-10 15:00:00', '2025-06-10 16:00:00', 4, 3),
-- Casa 4: seller_id=5
(7, '2025-06-11 10:00:00', '2025-06-11 11:00:00', 5, 4),
(8, '2025-06-11 14:00:00', '2025-06-11 15:00:00', 5, 4),
-- Casa 5: seller_id=2
(9, '2025-06-12 09:00:00', '2025-06-12 10:00:00', 2, 5),
(10, '2025-06-12 11:00:00', '2025-06-12 12:00:00', 2, 5),
-- Casa 6: seller_id=3
(11, '2025-06-13 10:00:00', '2025-06-13 11:00:00', 3, 6),
(12, '2025-06-13 16:00:00', '2025-06-13 17:00:00', 3, 6),
-- Casa 7: seller_id=4
(13, '2025-06-14 09:00:00', '2025-06-14 10:00:00', 4, 7),
(14, '2025-06-14 14:00:00', '2025-06-14 15:00:00', 4, 7),
-- Casa 8: seller_id=5
(15, '2025-06-15 11:00:00', '2025-06-15 12:00:00', 5, 8),
-- Past appointment slots (for testing purposes)
(16, '2024-12-15 10:00:00', '2024-12-15 11:00:00', 2, 1),
(17, '2024-12-16 14:00:00', '2024-12-16 15:00:00', 3, 2),
(18, '2024-12-17 16:00:00', '2024-12-17 17:00:00', 4, 3);

-- Insert sample visits
INSERT IGNORE INTO visits (id, appointment_slot_id, customer_email) VALUES 
-- Some visits for past appointment slots
(1, 16, 'cliente1@test.com'),
(2, 16, 'cliente2@test.com'), -- This makes slot 16 full (2 visits)
(3, 17, 'cliente3@test.com'),
-- Some visits for future appointment slots
(4, 1, 'cliente4@test.com'),
(5, 3, 'cliente5@test.com'),
(6, 5, 'cliente6@test.com'),
(7, 7, 'cliente7@test.com'),
(8, 9, 'cliente8@test.com');
