CREATE SCHEMA IF NOT EXISTS groomer;

CREATE TABLE IF NOT EXISTS groomer.service_types
(
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(20),
    duration TIME
);

CREATE TABLE IF NOT EXISTS groomer.groomer_work_times
(
    id BIGSERIAL PRIMARY KEY,
    service_type_id BIGINT REFERENCES groomer.service_types(id),
    groomer_id BIGINT,
    day DATE,
    start_work TIME,
    end_work TIME
);

INSERT INTO groomer.service_types(type, duration)
VALUES ('washing', '00:30'),
       ('hair cutting', '01:00'),
       ('nails cutting','00:15');

INSERT INTO groomer.groomer_work_times(service_type_id, groomer_id, day, start_work, end_work)
VALUES (1,1,'2022-01-15', '09:00', '14:00'),
       (2,1,'2022-01-15', '09:00', '14:00'),
       (3,1,'2022-01-15', '09:00', '14:00'),
       (1,1,'2022-01-16', '09:00', '14:00'),
       (3,1,'2022-01-16', '09:00', '14:00'),
       (2,1,'2022-01-19', '09:00', '14:00');

DROP TABLE groomer.groomer_work_times;
DROP TABLE groomer.service_types;
