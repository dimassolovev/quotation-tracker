CREATE TABLE IF NOT EXISTS security
(
    id        BIGINT PRIMARY KEY,
    pair_code VARCHAR(7) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS clearing_type
(
    id       BIGINT PRIMARY KEY,
    clearing VARCHAR(6) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS currency_data
(
    id               BIGINT PRIMARY KEY,
    trade_date       DATE NOT NULL,
    trade_time       TIME NOT NULL,
    rate             REAL NOT NULL,
    security_id      INT  NOT NULL,
    clearing_type_id INT  NOT NULL,
    UNIQUE (trade_date, trade_time, rate, security_id, clearing_type_id),
    FOREIGN KEY (security_id) REFERENCES security (id) ON DELETE CASCADE,
    FOREIGN KEY (clearing_type_id) REFERENCES clearing_type (id) ON DELETE CASCADE
);