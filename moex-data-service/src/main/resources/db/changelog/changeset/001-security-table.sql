-- sequence
CREATE SEQUENCE security_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 5;

--table
CREATE TABLE IF NOT EXISTS security (
    id BIGINT PRIMARY KEY,
    pair_code VARCHAR(7) NOT NULL UNIQUE
);