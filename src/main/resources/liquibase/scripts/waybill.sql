--liquibase formatted sql

--changeset goodmn:1
create table vehicles
(
    id                int primary key,
    registration_mark varchar(10) unique,
    mark              varchar(50) not null,
    type             varchar(50) not null
);

--changeset goodmn:2
insert into vehicles (id, registration_mark, mark, type)
values (1, 'РТ 673 77', 'Yutong ZK6128H', 'Автобус'),
       (2, 'РТ 708 77', 'Yutong ZK6122H9', 'Автобус');