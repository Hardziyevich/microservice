CREATE SCHEMA IF NOT EXISTS user_scheme;

CREATE TABLE IF NOT EXISTS user_scheme.users
(
    id         BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(40) NOT NULL,
    last_name  VARCHAR(40) NOT NULL,
    email      VARCHAR(40) NOT NULL UNIQUE,
    password   VARCHAR(128),
    role       VARCHAR(10) NOT NULL,
    UNIQUE (first_name,last_name)
);

INSERT INTO user_scheme.users(first_name, last_name, email, password, role)
VALUES ('alex', 'ivanov', 'alex@gmail.com', '123343', 'GROOMER'),
       ('ivan', 'ivanov', 'ivanov@gmail.com', '12312', 'GROOMER'),
       ('sveta', 'mamut', 'sveta@gmail.com', '12345', 'GROOMER'),
       ('user1','user1','user1@gmail.com','123456','USER'),
       ('user2','user2','user2@gmail.com','123456','USER'),
       ('user3','user3','user3@gmail.com','123456','USER'),
       ('user4','user4','user4@gmail.com','123456','USER');

-- DROP SCHEMA user_scheme;
-- DROP TABLE user_scheme.users;
