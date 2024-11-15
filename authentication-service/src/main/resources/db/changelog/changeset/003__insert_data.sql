SET search_path TO auth_schema;

SET search_path TO auth_schema;

DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM role LIMIT 1) THEN
            INSERT INTO role (name)
            VALUES ('ROLE_USER'),
                   ('ROLE_ADMIN');
        END IF;
    END
$$;
