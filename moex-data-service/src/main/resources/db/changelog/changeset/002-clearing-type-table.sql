-- sequence
CREATE SEQUENCE clearing_type_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 5;

--table
CREATE TABLE IF NOT EXISTS clearing_type (
    id BIGINT PRIMARY KEY,
    clearing VARCHAR(6) NOT NULL UNIQUE
);