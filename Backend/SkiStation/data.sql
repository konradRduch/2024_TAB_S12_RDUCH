INSERT INTO public.client (email, phone) VALUES
    ('client1@example.com', 123456789),
    ('client2@example.com', 987654321),
    ('client3@example.com', 555555555),
    ('client4@example.com', 666666666);

INSERT INTO public.order (total, client_id) VALUES
    (100.50, 1),
    (75.25, 2),
    (120.75, 3),
    (90.50, 4),
    (50.00, 3);

INSERT INTO public.price_list (time_start, time_end, ticket_price, pass_price) VALUES
    ('2024-05-01', '2024-05-31', 50.00, 200.00),
    ('2024-06-01', '2024-06-30', 60.00, 250.00),
    ('2024-07-01', '2024-07-31', 70.00, 300.00),
    ('2024-08-01', '2024-08-31', 80.00, 350.00);

INSERT INTO public.ticket (amount_of_rides, price_per_ride, ticket_type_name, order_id, price_list_id) VALUES
    (10, 5.50, 'Normal', 1, 1),
    (5, 8.20, 'Reduced', 2, 2),
    (15, 5.00, 'Normal', 3, 3),
    (8, 8.50, 'Reduced', 4, 4),
    (12, 6.00, 'Normal', 5, 1);

INSERT INTO public.pass (active, pass_type_name, price, order_id, price_list_id) VALUES
    (true, 'Daily', 30.00, 1, 1),
    (true, 'Weekly', 100.00, 2, 2),
    (true, 'Monthly', 200.00, 3, 3),
    (true, 'Daily', 35.00, 4, 1),
    (true, 'Weekly', 110.00, 5, 2),
    (true, 'Monthly', 220.00, 5, 3),
    (true, 'Daily', 40.00, 5, 1);

INSERT INTO public.worker (phone, email, role) VALUES
    (111111111, 'worker1@example.com', 'Staff'),
    (222222222, 'worker2@example.com', 'Manager'),
    (333333333, 'worker3@example.com', 'Instructor'),
    (444444444, 'worker4@example.com', 'Technician');

INSERT INTO public.lift (name, active, distance) VALUES
    ('Beginner Lift', true, 500),
    ('Advanced Lift', true, 1000),
    ('Intermediate Lift', true, 750),
    ('Expert Lift', true, 1500);

INSERT INTO public.ski_schedule (open, close, lift_id) VALUES
    ('2024-05-08 09:00:00', '2024-05-08 17:00:00', 1),
    ('2024-05-08 09:30:00', '2024-05-08 16:30:00', 2),
    ('2024-05-08 08:30:00', '2024-05-08 16:30:00', 1),
    ('2024-05-08 09:00:00', '2024-05-08 17:00:00', 2);

INSERT INTO public.ski_card (time_start, time_end, discount) VALUES
    ('2024-05-01 00:00:00', '2024-05-31 23:59:59', false),
    ('2024-06-01 00:00:00', '2024-06-30 23:59:59', true),
    ('2024-07-01 00:00:00', '2024-07-31 23:59:59', true),
    ('2024-08-01 00:00:00', '2024-08-31 23:59:59', false);

INSERT INTO public.history_ski_card (lift_id, ski_card_id) VALUES
    (1, 1),
    (2, 2),
    (1, 2),
    (2, 1);
