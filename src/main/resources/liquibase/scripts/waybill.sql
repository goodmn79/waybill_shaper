--liquibase formatted sql

--changeset goodmn:1
CREATE TABLE IF NOT EXISTS DRIVERS
(
    ID         bigint primary key,
    LAST_NAME  varchar(50) unique not null,
    FIRST_NAME varchar(50) unique not null,
    MIDL_NAME  varchar(50) unique not null,
    DL         varchar(70) unique not null,
    SSN        varchar(15) unique not null
);

CREATE TABLE IF NOT EXISTS VEHICLES
(
    ID                bigint primary key,
    REGISTRATION_MARK varchar(10) unique,
    MARK              varchar(50) not null,
    TYPE              varchar(50) not null
);

INSERT INTO PUBLIC.DRIVERS (ID, LAST_NAME, FIRST_NAME, MIDL_NAME, DL, SSN) VALUES (1848535798, 'Виноградов', 'Евгений', 'Александрович', '9934 998840 выдано: 05.06.2024 г. категории: A, B, C, D, E', '027-429-672 68');

INSERT INTO PUBLIC.VEHICLES (ID, REGISTRATION_MARK, MARK, TYPE) VALUES (1, 'РТ 673 77', 'Yutong ZK6128H', 'Автобус');