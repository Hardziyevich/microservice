CREATE SCHEMA IF NOT EXISTS user_scheme;
CREATE TABLE IF NOT EXISTS user_scheme.users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    email      VARCHAR(40) NOT NULL UNIQUE,
    password   VARCHAR(128),
    role       VARCHAR(10) NOT NULL
);

INSERT INTO user_scheme.users(first_name, last_name, email, password, role)
VALUES ('alex', 'ivanov', 'alex@gmail.com', '123343', 'GROOMER'),
       ('ivan', 'ivanov', 'ivanov@gmail.com', '12312', 'GROOMER'),
       ('sveta', 'mamut', 'sveta@gmail.com', '12345', 'GROOMER');

DROP SCHEMA user_scheme;
DROP TABLE user_scheme.users;
