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

