SET search_path TO outbox_tasks_schema;

CREATE TYPE STATUS AS ENUM ('REQUESTED', 'FAILED', 'COMPLETED');