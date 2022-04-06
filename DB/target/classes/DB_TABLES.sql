create table tours
(
    id serial
        constraint tours_pk
            primary key,
    name varchar(25) not null,
    description varchar(1024),
    start varchar(25) not null,
    destination varchar(25) not null,
    "transportType" varchar(10) not null,
    distance real,
    duration real,
    "mapPath" varchar(255)
);

alter table tours owner to postgres;

create unique index tours_id_uindex
    on tours (id);

create unique index tours_name_uindex
    on tours (name);

create table logs
(
    id serial
        constraint logs_pk
            primary key,
    "tourId" integer not null,
    action varchar(25) not null,
    date date not null,
    time timestamp not null
);

alter table logs owner to postgres;

create unique index logs_id_uindex
    on logs (id);

