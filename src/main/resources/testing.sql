DROP SCHEMA public CASCADE;
CREATE SCHEMA public;
GRANT ALL ON SCHEMA public TO foobar;
GRANT ALL ON SCHEMA public TO PUBLIC;

CREATE TABLE user_profile
(
    id                 SERIAL PRIMARY KEY,
    name               TEXT,
    email              VARCHAR(255),
    dateMod            DATE,
    dateCreated        DATE,
    deleted            BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE product
(
    id                 SERIAL PRIMARY KEY,
    name               TEXT NOT NULL ,
    quantity           INT,
    priceEuro          INT,
    dateMod            DATE,
    dateCreated        DATE,
    deleted            BOOLEAN NOT NULL DEFAULT FALSE
);
