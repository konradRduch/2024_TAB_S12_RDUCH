CREATE TABLE IF NOT EXISTS public.client
(
    id    SERIAL,
    email text,
    phone integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.order
(
    id        SERIAL,
    total     real,
    client_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.ticket
(
    id               SERIAL,
    amount_of_rides  integer,
    price_per_ride   real,
    ticket_type_name text,
    order_id         integer,
    price_list_id    integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.pass
(
    id             SERIAL,
    active         boolean,
    pass_type_name text,
    price          real,
    order_id       integer,
    price_list_id  integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.worker
(
    id    SERIAL,
    phone integer NOT NULL,
    email text    NOT NULL,
    role  text    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.ski_schedule
(
    id      SERIAL,
    open    date,
    close   date,
    lift_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.lift
(
    id       SERIAL,
    name     text,
    active   boolean,
    distance real,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.price_list
(
    id           SERIAL,
    time_start   date NOT NULL,
    time_end     date NOT NULL,
    ticket_price real NOT NULL,
    pass_price   real NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.history_ski_card
(
    id          SERIAL,
    lift_id     integer,
    ski_card_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.ski_card
(
    id         SERIAL,
    time_start date NOT NULL,
    time_end   date NOT NULL,
    discount   boolean,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.order
    ADD CONSTRAINT client_id FOREIGN KEY (client_id)
        REFERENCES public.client (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public.order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.ticket
    ADD CONSTRAINT price_list_id FOREIGN KEY (price_list_id)
        REFERENCES public.price_list (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.pass
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public.order (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.pass
    ADD CONSTRAINT price_list_fk FOREIGN KEY (price_list_id)
        REFERENCES public.price_list (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.ski_schedule
    ADD CONSTRAINT lift_fk FOREIGN KEY (lift_id)
        REFERENCES public.lift (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.history_ski_card
    ADD CONSTRAINT ski_card_fk FOREIGN KEY (ski_card_id)
        REFERENCES public.ski_card (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.history_ski_card
    ADD CONSTRAINT lift_fk FOREIGN KEY (lift_id)
        REFERENCES public.lift (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.ski_card
    ADD FOREIGN KEY (id)
        REFERENCES public.ticket (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public.ski_card
    ADD FOREIGN KEY (id)
        REFERENCES public.pass (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
