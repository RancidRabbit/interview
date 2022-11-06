create table transfers
(
    id            bigserial primary key,
    source_id     integer not null ,
    target_id     integer not null ,
    amount       integer not null ,
    transfer_time date not null ,
    account_id    bigint references accounts (id)
);

insert into transfers (source_id, target_id, amount, transfer_time, account_id)
VALUES
(1, 2, 1500, '01-04-2020', 1),
(2, 4, 900, '04-08-2021', 2),
(3, 1, 1200, '23-01-2018', 3),
(4, 3, 400, '11-01-2019', 4),
(5, 1, 600, '12-04-2020', 5);


drop table transfers;