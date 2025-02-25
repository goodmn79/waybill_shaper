--liquibase formatted sql

--changeset goodmn:1
create table drivers
(
    id         serial primary key,
    last_name  varchar(50) unique not null,
    first_name varchar(50) unique not null,
    midl_name  varchar(50) unique not null,
    DL         varchar(70) unique not null,
    SSN        varchar(15) unique not null
);

create table vehicles
(
    id                serial primary key,
    registration_mark varchar(10) unique,
    mark              varchar(50) not null,
    type              varchar(50) not null
);
