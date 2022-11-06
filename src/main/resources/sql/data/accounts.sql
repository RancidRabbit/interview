create schema task;

create table accounts
(
    id         bigserial primary key,
    first_name varchar(50) not null ,
    last_name  varchar(50) not null ,
    balance    numeric not null
);

insert into accounts (first_name, last_name, balance)
VALUES ('Joshua', 'Taylor', 100),
       ('John', 'Hughes', 150),
       ('William', 'Colon', 250),
       ('James', 'Ward', 300),
       ('Paul', 'Garcia', 400);


select acc.first_name, acc.last_name, t.amount, t.transfer_time from accounts acc
join transfers t on acc.id = t.account_id
where t.transfer_time >= '01-01-2019' and t.amount > 1000;
