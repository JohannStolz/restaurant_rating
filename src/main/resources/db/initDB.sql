DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;


DROP TABLE IF EXISTS user_roles;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE GLOBAL_SEQ
  AS INTEGER
  START WITH 100000;

CREATE TABLE users
(
  id              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name            VARCHAR(255)       NOT NULL,
  email           VARCHAR(255)       NOT NULL,
  age             INTEGER            NOT NULL,
  registered_date DATE DEFAULT now() NOT NULL,
  enabled         BOOLEAN             DEFAULT TRUE,
  sex             VARCHAR(255),
  password        VARCHAR(255)       NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  roles   VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, roles),
  FOREIGN KEY (user_id) REFERENCES USERS (id)
  ON DELETE CASCADE
);
CREATE TABLE restaurants
(
  id      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  address VARCHAR(255),
  email   VARCHAR(255),
  name    VARCHAR(255)
);

CREATE TABLE dishes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date          DATE DEFAULT now()   NOT NULL,
  description   VARCHAR(255)         NOT NULL,
  price         FLOAT                NOT NULL,
  restaurant_id INTEGER              NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
  ON DELETE CASCADE
);
CREATE TABLE votes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  date          TIMESTAMP DEFAULT now() NOT NULL,
  dish_id       INTEGER                 NOT NULL,
  restaurant_id INTEGER                 NOT NULL,
  user_id       INTEGER                 NOT NULL,
  FOREIGN KEY (dish_id) REFERENCES DISHES (id)
  ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES RESTAURANTS (id)
  ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES USERS (id)
  ON DELETE CASCADE
);

