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


INSERT INTO ROOMTYPES VALUES (1, 'Suite','Habitación grande con jacuzzi'),(2, 'Individual','Habitación pequeña con lavabo');

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
INSERT INTO ROOMS VALUES (1, 1, 'S1','Habitación grande con jacuzzi',100,null),(2,2, 'IN1','Habitación pequeña con lavabo',50,null);
create table if not exists PREFERENCES
(
    id				bigint AUTO_INCREMENT,
    fk_roomtype_id     bigint,
    priceLastSearch double,
    PRIMARY KEY (id),
    FOREIGN KEY (fk_roomtype_id) REFERENCES ROOMTYPES (id)
);
INSERT INTO PREFERENCES VALUES (1, 1, 300),(2,2, 200);
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
INSERT INTO CLIENTS VALUES (1, 1, 'Pablo','Garcia','Garcia@seat.es'),(2, 2, 'Oscar','Garcia','OGarcia@seat.com');
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
INSERT INTO BOOKINGS VALUES (1, 1,1, '2020-09-10','2020-09-14',400),(2, 1,2, '2020-10-10','2020-10-14',200);

create table if not exists LOGINS
(
    id      		bigint AUTO_INCREMENT,
    fk_client_id  		bigint UNIQUE,
    username 		varchar(100) UNIQUE,
    password 		varchar(100),
    PRIMARY KEY (id),
    FOREIGN KEY (fk_client_id) REFERENCES CLIENTS (id)
);
INSERT INTO LOGINS VALUES (1, 1, 'Garcia1989','1234'),(2, 2, 'Oscar2000','1234');