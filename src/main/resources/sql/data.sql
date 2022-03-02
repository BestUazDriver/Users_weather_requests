create table user_requests
(
    id      serial primary key,
    user_id varchar(40),
    city    varchar(30)
);