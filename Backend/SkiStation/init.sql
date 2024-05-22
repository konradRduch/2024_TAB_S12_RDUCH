BEGIN;

ALTER TABLE IF EXISTS public.order DROP CONSTRAINT IF EXISTS client_id;

ALTER TABLE IF EXISTS public.ticket DROP CONSTRAINT IF EXISTS order_fk;

ALTER TABLE IF EXISTS public.ticket DROP CONSTRAINT IF EXISTS price_list_id;

ALTER TABLE IF EXISTS public.pass DROP CONSTRAINT IF EXISTS order_fk;

ALTER TABLE IF EXISTS public.pass DROP CONSTRAINT IF EXISTS price_list_fk;

ALTER TABLE IF EXISTS public.ski_schedule DROP CONSTRAINT IF EXISTS lift_fk;

ALTER TABLE IF EXISTS public.lift_ticket DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.lift_ticket DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.lift_pass DROP CONSTRAINT IF EXISTS None;

ALTER TABLE IF EXISTS public.lift_pass DROP CONSTRAINT IF EXISTS None;

DROP TABLE IF EXISTS public.client;

CREATE TABLE IF NOT EXISTS public.client
(
    id SERIAL PRIMARY KEY,
    email TEXT,
    phone INTEGER
);

DROP TABLE IF EXISTS public.order;

CREATE TABLE IF NOT EXISTS public.order
(
    id SERIAL PRIMARY KEY,
    total REAL,
    client_id INTEGER
);

DROP TABLE IF EXISTS public.ticket;

CREATE TABLE IF NOT EXISTS public.ticket
(
    id SERIAL PRIMARY KEY,
    amount_of_rides INTEGER,
    price_per_ride REAL,
    ticket_type_name TEXT,
    time_start TIMESTAMP WITHOUT TIME ZONE,
    time_end TIMESTAMP WITHOUT TIME ZONE,
    discount BOOLEAN,
    order_id INTEGER,
    price_list_id INTEGER,
    total REAL
);

DROP TABLE IF EXISTS public.pass;

CREATE TABLE IF NOT EXISTS public.pass
(
    id SERIAL PRIMARY KEY,
    active BOOLEAN,
    pass_type_name TEXT,
    price REAL,
    time_start TIMESTAMP WITHOUT TIME ZONE,
    time_end TIMESTAMP WITHOUT TIME ZONE,
    suspension_date TIMESTAMP WITHOUT TIME ZONE,
    discount BOOLEAN,
    price_list_id INTEGER,
    order_id INTEGER
);

DROP TABLE IF EXISTS public.worker;

CREATE TABLE IF NOT EXISTS public.worker
(
    id SERIAL PRIMARY KEY,
    phone INTEGER NOT NULL,
    email TEXT NOT NULL,
    role TEXT NOT NULL
);

DROP TABLE IF EXISTS public.ski_schedule;

CREATE TABLE IF NOT EXISTS public.ski_schedule
(
    id SERIAL PRIMARY KEY,
    open DATE,
    close DATE,
    lift_id INTEGER
);

DROP TABLE IF EXISTS public.lift;

CREATE TABLE IF NOT EXISTS public.lift
(
    id SERIAL PRIMARY KEY,
    name TEXT,
    active BOOLEAN,
    distance REAL
);

DROP TABLE IF EXISTS public.price_list;

CREATE TABLE IF NOT EXISTS public.price_list
(
    id SERIAL PRIMARY KEY,
    time_start DATE NOT NULL,
    time_end DATE NOT NULL,
    ticket_price REAL NOT NULL,
    pass_price REAL NOT NULL
);

DROP TABLE IF EXISTS public.lift_ticket;

CREATE TABLE IF NOT EXISTS public.lift_ticket
(
    id SERIAL PRIMARY KEY,
    lift_id INTEGER,
    ticket_id INTEGER
);

DROP TABLE IF EXISTS public.lift_pass;

CREATE TABLE IF NOT EXISTS public.lift_pass
(
    id SERIAL PRIMARY KEY,
    lift_id INTEGER,
    pass_id INTEGER
);

ALTER TABLE IF EXISTS public.order
    ADD CONSTRAINT client_id FOREIGN KEY (client_id)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public.order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT price_list_id FOREIGN KEY (price_list_id)
        REFERENCES public.price_list (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.pass
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public.order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.pass
    ADD CONSTRAINT price_list_fk FOREIGN KEY (price_list_id)
        REFERENCES public.price_list (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.ski_schedule
    ADD CONSTRAINT lift_fk FOREIGN KEY (lift_id)
        REFERENCES public.lift (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.lift_ticket
    ADD CONSTRAINT lift_id_fk FOREIGN KEY (lift_id)
        REFERENCES public.lift (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.lift_ticket
    ADD CONSTRAINT ticket_id_fk FOREIGN KEY (ticket_id)
        REFERENCES public.ticket (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.lift_pass
    ADD CONSTRAINT lift_id_fk FOREIGN KEY (lift_id)
        REFERENCES public.lift (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

ALTER TABLE IF EXISTS public.lift_pass
    ADD CONSTRAINT pass_id_fk FOREIGN KEY (pass_id)
        REFERENCES public.pass (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION;

END;

INSERT INTO public.client (email, phone)
VALUES ('john@example.com', 123456789),
       ('jane@example.com', 987654321),
       ('alice@example.com', 555555555),
       ('bob@example.com', 666666666);

-- INSERT INTO public.order (total, client_id)
-- VALUES (100.50, 1),
--        (75.25, 2),
--        (120.75, 3),
--        (90.50, 4),
--        (50.00, 3);

INSERT INTO public.price_list (time_start, time_end, ticket_price, pass_price)
VALUES ('2024-05-01', '2024-05-31', 50.00, 200.00),
       ('2024-06-01', '2024-06-30', 60.00, 250.00),
       ('2024-07-01', '2024-07-31', 70.00, 300.00),
       ('2024-08-01', '2024-08-31', 80.00, 350.00);

-- INSERT INTO public.ticket (amount_of_rides, price_per_ride, ticket_type_name, time_start, time_end, discount, order_id, price_list_id)
-- VALUES (10, 5.50, 'Normal', '2024-05-01 09:00:00', '2024-05-01 17:00:00', false, 1, 1),
--        (5, 8.20, 'Reduced', '2024-05-02 09:00:00', '2024-05-02 17:00:00', true, 2, 2),
--        (15, 5.00, 'Normal', '2024-05-03 09:00:00', '2024-05-03 17:00:00', false, 3, 3),
--        (8, 8.50, 'Reduced', '2024-05-04 09:00:00', '2024-05-04 17:00:00', true, 4, 4),
--        (12, 6.00, 'Normal', '2024-05-05 09:00:00', '2024-05-05 17:00:00', false, 5, 1);
--
-- INSERT INTO public.pass (active, pass_type_name, price, time_start, time_end, discount, price_list_id, order_id)
-- VALUES (true, 'Daily', 30.00, '2024-05-01 00:00:00', '2024-05-01 23:59:59', false, 1, 1),
--        (true, 'Weekly', 100.00, '2024-05-02 00:00:00', '2024-05-08 23:59:59', true, 2, 2),
--        (true, 'Monthly', 200.00, '2024-05-09 00:00:00', '2024-06-08 23:59:59', false, 3, 3),
--        (true, 'Daily', 35.00, '2024-05-10 00:00:00', '2024-05-10 23:59:59', true, 1, 4),
--        (true, 'Weekly', 110.00, '2024-05-11 00:00:00', '2024-05-17 23:59:59', false, 2, 5),
--        (true, 'Monthly', 220.00, '2024-05-18 00:00:00', '2024-06-17 23:59:59', true, 3, 5),
--        (true, 'Daily', 40.00, '2024-05-19 00:00:00', '2024-05-19 23:59:59', false, 1, 5);

INSERT INTO public.worker (phone, email, role)
VALUES (111111111, 'worker1@example.com', 'Staff'),
       (222222222, 'worker2@example.com', 'Manager'),
       (333333333, 'worker3@example.com', 'Instructor'),
       (444444444, 'worker4@example.com', 'Technician');

INSERT INTO public.lift (name, active, distance)
VALUES ('Beginner Lift', true, 500),
       ('Advanced Lift', true, 1000),
       ('Intermediate Lift', true, 750),
       ('Expert Lift', true, 1500);

INSERT INTO public.ski_schedule (open, close, lift_id)
VALUES ('2024-05-01', '2024-05-31', 1),
       ('2024-06-01', '2024-06-30', 2),
       ('2024-07-01', '2024-07-31', 3),
       ('2024-08-01', '2024-08-31', 4);

-- INSERT INTO public.lift_ticket (lift_id, ticket_id)
-- VALUES (1, 1),
--        (2, 2),
--        (3, 3),
--        (4, 4);
--
-- INSERT INTO public.lift_pass (lift_id, pass_id)
-- VALUES (1, 1),
--        (2, 2),
--        (3, 3),
--        (4, 4);