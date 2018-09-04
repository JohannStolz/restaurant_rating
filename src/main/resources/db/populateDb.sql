DELETE FROM votes;
DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM restaurants;

INSERT INTO users (id, name, age, email, password, sex) VALUES
  (100000,'admin', '23', 'admin@rating.com', 'adminpass', 'SEX_WHATEVER'), --100000
  (100001,'user1', '18', 'user1@rating.com', 'user1pass', 'SEX_MALE'), --100001
  (100002, 'user2', '125', 'user2@rating.com', 'user2pass', 'SEX_FEMALE'); --100002

INSERT INTO user_roles (roles, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100002);

INSERT INTO restaurants (id, name , address, email) VALUES
  (100003, 'CrumbPotato', 'Bus station', 'CP@gmail.com'),   --100003
  (100004, 'Belyash for gentlemen', 'Sub', 'Sub@gmail.com'),      --100004
  (100005, 'Maxim', 'Paris', 'Maxim@gmail.com');           --100005

INSERT INTO dishes (id, date, description, restaurant_id, price ) VALUES
  (100006, NOW(), 'CrumbPotato', 100003, 10000 ),   --100006
  (100007, NOW(), 'BelyashVIP', 100004, 5500), --100007
  (100008, NOW(), 'LeBigMak', 100005, 15000 );  --100008

INSERT INTO votes (id, restaurant_id, user_id, dish_id) VALUES
  (100009, 100003,  100000, 100008),--100009
  (100010, 100004, 100001, 100008);--100010