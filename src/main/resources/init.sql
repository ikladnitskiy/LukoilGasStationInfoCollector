DROP TABLE IF EXISTS fuels;
DROP TABLE IF EXISTS properties;
DROP TABLE IF EXISTS features;
DROP TABLE IF EXISTS payment_types;
DROP TABLE IF EXISTS services;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS stations;

CREATE TABLE stations
(
    id                INTEGER NOT NULL,
    latitude          DOUBLE PRECISION DEFAULT NULL,
    longitude         DOUBLE PRECISION DEFAULT NULL,
    station_number    VARCHAR(16)      DEFAULT NULL,
    region_id         INTEGER          DEFAULT NULL,
    federal_line_id   INTEGER          DEFAULT NULL,
    manager_full_name VARCHAR(128)     DEFAULT NULL,
    email             VARCHAR(128)     DEFAULT NULL,
    phone             VARCHAR(64)      DEFAULT NULL,
    fax               VARCHAR(64)      DEFAULT NULL,
    station_status    INTEGER          DEFAULT NULL,
    name              VARCHAR(256)     DEFAULT NULL,
    display_name      VARCHAR(256)     DEFAULT NULL,
    address           VARCHAR(256)     DEFAULT NULL,
    twenty_four_hour  BOOLEAN          DEFAULT NULL,
    has_story         BOOLEAN          DEFAULT NULL,
    sells_oil         BOOLEAN          DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE companies
(
    id                     INTEGER NOT NULL AUTO_INCREMENT,
    station_id             INTEGER NOT NULL,
    company_id             INTEGER      DEFAULT NULL,
    name                   VARCHAR(256) DEFAULT NULL,
    phone                  VARCHAR(64)  DEFAULT NULL,
    fax                    VARCHAR(64)  DEFAULT NULL,
    email                  VARCHAR(128) DEFAULT NULL,
    database_home_page_url VARCHAR(512) DEFAULT NULL,
    home_page_url          VARCHAR(512) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE services
(
    id               INTEGER NOT NULL AUTO_INCREMENT,
    station_id       INTEGER NOT NULL,
    service_id       INTEGER      DEFAULT NULL,
    name             VARCHAR(256) DEFAULT NULL,
    service_group_id INTEGER      DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE payment_types
(
    id              INTEGER NOT NULL AUTO_INCREMENT,
    station_id      INTEGER NOT NULL,
    payment_type_id INTEGER      DEFAULT NULL,
    name            VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE features
(
    id         INTEGER NOT NULL AUTO_INCREMENT,
    station_id INTEGER NOT NULL,
    feature_id INTEGER      DEFAULT NULL,
    name       VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE properties
(
    id          INTEGER NOT NULL AUTO_INCREMENT,
    station_id  INTEGER NOT NULL,
    property_id INTEGER      DEFAULT NULL,
    name        VARCHAR(256) DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE fuels
(
    id                    INTEGER NOT NULL AUTO_INCREMENT,
    station_id            INTEGER NOT NULL,
    fuel_id               INTEGER      DEFAULT NULL,
    name                  VARCHAR(256) DEFAULT NULL,
    price                 VARCHAR(16)  DEFAULT NULL,
    display_price         VARCHAR(16)  DEFAULT NULL,
    company_price         VARCHAR(16)  DEFAULT NULL,
    company_price_type    VARCHAR(32)  DEFAULT NULL,
    currency_iso_code     VARCHAR(64)  DEFAULT NULL,
    currency_display_name VARCHAR(64)  DEFAULT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE companies
    ADD FOREIGN KEY (station_id) REFERENCES stations (id);
ALTER TABLE services
    ADD FOREIGN KEY (station_id) REFERENCES stations (id);
ALTER TABLE payment_types
    ADD FOREIGN KEY (station_id) REFERENCES stations (id);
ALTER TABLE features
    ADD FOREIGN KEY (station_id) REFERENCES stations (id);
ALTER TABLE properties
    ADD FOREIGN KEY (station_id) REFERENCES stations (id);
ALTER TABLE fuels
    ADD FOREIGN KEY (station_id) REFERENCES stations (id);