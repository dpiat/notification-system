create table mail_worker.notification_logs
(
    id              varchar(255) not null primary key,
    notification_id varchar(255) not null unique,
    status          varchar(255) not null
);
