CREATE TABLE IF NOT EXISTS public."Client"
(
    id    integer,
    email text,
    phone integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Order"
(
    id        integer,
    total     real,
    client_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Ticket"
(
    id               integer,
    amount_of_rides  integer,
    price_per_ride   real,
    ticket_type_name text,
    order_id         integer,
    price_list_id    integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Pass"
(
    id             integer,
    active         boolean,
    pass_type_name text,
    price          real,
    order_id       integer,
    price_list_id  integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Worker"
(
    id    integer,
    phone integer NOT NULL,
    email text    NOT NULL,
    role  text    NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."SkiSchedule"
(
    id      integer,
    open    date,
    close   date,
    lift_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."Lift"
(
    id       integer,
    name     text,
    active   boolean,
    distance real,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."PriceList"
(
    id           integer,
    time_start   date NOT NULL,
    time_end     date NOT NULL,
    ticket_price real NOT NULL,
    pass_price   real NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."HistorySkiCard"
(
    id          integer,
    lift_id     integer,
    ski_card_id integer,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public."SkiCard"
(
    id         integer,
    time_start date NOT NULL,
    time_end   date NOT NULL,
    discount   boolean,
    PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public."Order"
    ADD CONSTRAINT client_id FOREIGN KEY (client_id)
        REFERENCES public."Client" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."Ticket"
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public."Order" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."Ticket"
    ADD CONSTRAINT price_list_id FOREIGN KEY (price_list_id)
        REFERENCES public."PriceList" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."Pass"
    ADD CONSTRAINT order_fk FOREIGN KEY (order_id)
        REFERENCES public."Order" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."Pass"
    ADD CONSTRAINT price_list_fk FOREIGN KEY (price_list_id)
        REFERENCES public."PriceList" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."SkiSchedule"
    ADD CONSTRAINT lift_fk FOREIGN KEY (lift_id)
        REFERENCES public."Lift" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."HistorySkiCard"
    ADD CONSTRAINT ski_card_fk FOREIGN KEY (ski_card_id)
        REFERENCES public."SkiCard" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."HistorySkiCard"
    ADD CONSTRAINT lift_fk FOREIGN KEY (lift_id)
        REFERENCES public."Lift" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."SkiCard"
    ADD FOREIGN KEY (id)
        REFERENCES public."Ticket" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;

ALTER TABLE IF EXISTS public."SkiCard"
    ADD FOREIGN KEY (id)
        REFERENCES public."Pass" (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
        NOT VALID;
