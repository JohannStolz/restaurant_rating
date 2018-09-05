DELETE
FROM votes;
DELETE
FROM user_roles;
DELETE
FROM users;
DELETE
FROM dishes;
DELETE
FROM restaurants;

ALTER SEQUENCE GLOBAL_SEQ
RESTART WITH 100000;

INSERT INTO users (name, age, email, password, sex)
VALUES ('admin', '23', 'admin@rating.com', 'adminpass', 'SEX_WHATEVER'), --100000
       ('user1', '18', 'user1@rating.com', 'user1pass', 'SEX_MALE'), --100001
       ('user2', '125', 'user2@rating.com', 'user2pass', 'SEX_FEMALE'); --100002

INSERT INTO user_roles (roles, user_id)
VALUES ('ROLE_USER', 100000),
       ('ROLE_ADMIN', 100000),
       ('ROLE_USER', 100001),
       ('ROLE_USER', 100002);

INSERT INTO restaurants (name, address, email)
VALUES ('CrumbPotato', 'Bus station', 'CP@gmail.com'),   --100003
       ('Belyash for gentlemen', 'Sub', 'Sub@gmail.com'),      --100004
       ('Maxim', 'Paris', 'Maxim@gmail.com'); --100005

INSERT INTO dishes (date, description, restaurant_id, price)
VALUES (NOW(), 'CrumbPotatoshka', 100003, 10000),   --100006
       (NOW(), 'BelyashVIP', 100004, 5500), --100007
       (NOW(), 'LeBigMac', 100005, 15000); --100008

INSERT INTO votes (restaurant_id, user_id, dish_id)
VALUES (100003, 100000, 100008),--100009
       (100004, 100001, 100008); --100010