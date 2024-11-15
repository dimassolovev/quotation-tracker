SET search_path TO outbox_tasks_schema;

CREATE TABLE IF NOT EXISTS outbox_tasks
(
    id          UUID PRIMARY KEY,
    timestamp   TIMESTAMP    NOT NULL,
    status_type STATUS       NOT NULL,
    topic       VARCHAR(100) NOT NULL,
    payload     JSON         NOT NULL
);