CREATE SCHEMA IF NOT EXISTS user_order;

CREATE TABLE IF NOT EXISTS user_order.user_orders
(
    id           BIGSERIAL PRIMARY KEY,
    user_id      BIGINT,
    groomer_id   BIGINT,
    pet_name     VARCHAR(30),
    service_type VARCHAR(20),
    day          DATE,
    time         TIME,
    duration     TIME
);

INSERT INTO user_order.user_orders(user_id, groomer_id, pet_name, service_type, day, time, duration)
VALUES ('4','1','pet1','washing','2022-01-15','09:15','00:30'),
       ('5','1','pet1','washing','2022-01-15','11:15','00:30'),
       ('6','1','pet1','washing','2022-01-15','14:15','00:30');

DROP TABLE user_order.user_orders;