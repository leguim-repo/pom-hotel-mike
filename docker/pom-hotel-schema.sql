drop database if exists pom_hotel;
create database pom_hotel character set latin1 collate latin1_spanish_ci;
use pom_hotel;

create table if not exists ROOMTYPES
(
    id          bigint AUTO_INCREMENT,
    name        varchar(100),
    description varchar(300),
    PRIMARY KEY (id)
);

create table if not exists ROOMS
(
    id              bigint AUTO_INCREMENT,
    fk_roomtype_id     bigint,
    code            varchar(100),
    description     varchar(300),
    pricePerNight   double,
    image           varchar(100),
    guests          int,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_roomtype_id) REFERENCES ROOMTYPES (id)
);

create table if not exists CLIENTS
(
    id               bigint AUTO_INCREMENT,
    name           	 varchar(100),
    lastname         varchar(100),
    email            varchar(100),
    PRIMARY KEY (id)
);

create table if not exists BOOKINGS
(
    id              bigint AUTO_INCREMENT,
    fk_client_id	bigint,
    fk_room_id		bigint,
    checkIn         date,
    checkOut        date,
    clientEmail		varchar(100),
    guests			int,
    breakfast		tinyint(1),
    carparking		tinyint(1),
    spa 			tinyint(1),
    laundry			tinyint(1),
    shuttle			tinyint(1),
    codediscount	varchar(100),
    totalPrice      double,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_room_id) REFERENCES ROOMS (id),
    FOREIGN KEY (fk_client_id) REFERENCES CLIENTS (id)
);

create table if not exists LOGINS
(
    id      		bigint AUTO_INCREMENT,
    fk_client_id  		bigint UNIQUE,
    username 		varchar(100) UNIQUE,
    password 		varchar(100),
    role            varchar(100),
    enabled         tinyint(1),
    PRIMARY KEY (id),
    FOREIGN KEY (fk_client_id) REFERENCES CLIENTS (id)
);
