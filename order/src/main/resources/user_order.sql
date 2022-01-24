CREATE SCHEMA IF NOT EXISTS user_order;

CREATE TABLE IF NOT EXISTS user_order.user_orders
(
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT,
    groomer_id BIGINT,
    pet_name VARCHAR(30),
    service_type VARCHAR(20),
    day DATE,
    time TIME,
    duration TIME
);