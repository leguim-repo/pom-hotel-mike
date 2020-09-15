drop database if exists pom_hotel;
create database pom_hotel character set latin1 collate latin1_spanish_ci;
use pom_hotel;

create table if not exists ROOMTYPES
(
    id          bigint AUTO_INCREMENT,
    name        varchar(100),
    description varchar(200),
    PRIMARY KEY (id)
);

create table if not exists ROOMS
(
    id              bigint AUTO_INCREMENT,
    fk_roomtype_id     bigint,
    code            varchar(100),
    description     varchar(200),
    pricePerNight   double,
    image           blob,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_roomtype_id) REFERENCES ROOMTYPES (id)
);

create table if not exists PREFERENCES
(
    id				bigint AUTO_INCREMENT,
    fk_roomtype_id     bigint,
    priceLastSearch double,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_roomtype_id) REFERENCES ROOMTYPES (id)
);

create table if not exists CLIENTS
(
    id               bigint AUTO_INCREMENT,
    fk_preferences_id   bigint UNIQUE,
    name           	 varchar(100),
    lastname         varchar(100),
    email            varchar(100),
    PRIMARY KEY (id),
    FOREIGN KEY (fk_preferences_id) REFERENCES PREFERENCES (id)
);

create table if not exists BOOKINGS
(
    id              bigint AUTO_INCREMENT,
    fk_client_id		bigint,
    fk_room_id			bigint,
    checkIn         date,
    checkOut        date,
    totalPrice      double,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_room_id) REFERENCES ROOMS (id),
    FOREIGN KEY (fk_client_id) REFERENCES CLIENTS (id)
);


create table if not exists LOGINS
(
    id      		bigint AUTO_INCREMENT,
    fk_client_id  		bigint UNIQUE,
    username 		varchar(100),
    password 		varchar(100),
    PRIMARY KEY (id),
    FOREIGN KEY (fk_client_id) REFERENCES CLIENTS (id)
);