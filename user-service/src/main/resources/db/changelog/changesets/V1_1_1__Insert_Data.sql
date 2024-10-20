-- Create roles
INSERT INTO roles(name)
VALUES ('ROLE_USER');
INSERT INTO roles(name)
VALUES ('ROLE_ADMIN');

-- Create securities
INSERT INTO securities(pair_code)
VALUES ('GBP/RUB');
INSERT INTO securities(pair_code)
VALUES ('USD/RUB');
INSERT INTO securities(pair_code)
VALUES ('EUR/RUB');
INSERT INTO securities(pair_code)
VALUES ('CAD/RUB');
INSERT INTO securities(pair_code)
VALUES ('CHF/RUB');
INSERT INTO securities(pair_code)
VALUES ('KZT/RUB');
INSERT INTO securities(pair_code)
VALUES ('JPY/RUB');
INSERT INTO securities(pair_code)
VALUES ('CNY/RUB');
INSERT INTO securities(pair_code)
VALUES ('TRY/RUB');
INSERT INTO securities(pair_code)
VALUES ('HKD/RUB');

-- Create test admin user
DO
$$
    DECLARE
        user_id INT;
    BEGIN
        -- Insert user and save his ID to variable
        INSERT INTO user_credentials(username, email)
        VALUES ('dimas', 'gohasoxx@gmail.com')
        RETURNING id INTO user_id;

        -- Insert user roles
        INSERT INTO user_roles(user_id, role_id)
        VALUES (user_id, (SELECT r.id FROM roles AS r WHERE r.name = 'ROLE_USER')),
               (user_id, (SELECT r.id FROM roles AS r WHERE r.name = 'ROLE_ADMIN'));

        -- Insert user UUID
        INSERT INTO user_uuids(user_uuid, user_id)
        VALUES ('44723953-084b-427c-a6e5-4f9fe716da75', user_id);

        -- Inserting data into a table securities
        INSERT INTO user_security(user_id, security_id)
        VALUES (user_id, (SELECT s.id FROM securities AS s WHERE s.pair_code = 'USD/RUB'));
    END
$$;

