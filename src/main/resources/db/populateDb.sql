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
VALUES ('admin',
        '23',
        'admin@rating.com',
        '$2a$10$Mu2KicDLBQI2yFSFYyBpEeQETvUw4KXbAMnBzMlwj0aEac8LrHZ4S',
        'SEX_WHATEVER'), --100000
       ('user1',
        '18',
        'user1@rating.com',
        '$2a$10$cVf5XvpJeff6MrwClQxTtuzJ.G.mDf7pe/9.hGbZioTXwXou1ccvm',
        'SEX_MALE'), --100001
       ('user2',
        '40',
        'user2@rating.com',
        '$2a$10$vz.RZszVreNhNDdvDkMJBuP6ALImsFyI26bh1aVABCgtJAbkYO8dG',
        'SEX_FEMALE'); --100002
/*admin password - adminpass, user1 password - user1pass, user2 password - user2pass */
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
VALUES ('31.05.2018', 'CrumbPotatoshka', 100003, 1000),   --100006
       ('30.05.2018', 'BelyashVIP', 100004, 800), --100007
       ('01.06.2018', 'LeBigMac', 100005, 10000); --100008

INSERT INTO votes (restaurant_id, user_id, dish_id)
VALUES (100003, 100000, 100006),--100009
       (100004, 100001, 100007),--100010
       (100005, 100002, 100008) --100011
;