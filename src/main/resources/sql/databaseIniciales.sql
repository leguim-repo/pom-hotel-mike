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


INSERT INTO ROOMTYPES VALUES
(1, 'Suite room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
(2, 'Individual room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
(3, 'Family room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
(4, 'Luxur room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
(5, 'Double room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?');

create table if not exists ROOMS
(
    id              bigint AUTO_INCREMENT,
    fk_roomtype_id     bigint,
    code            varchar(100),
    description     varchar(300),
    pricePerNight   double,
    image           varchar(100),
    PRIMARY KEY (id),
    FOREIGN KEY (fk_roomtype_id) REFERENCES ROOMTYPES (id)
);
INSERT INTO ROOMS VALUES
(1, 1, 'SU1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Cum doloribus sed, magnam corrupti sapiente aspernatur dolorum ullam sequi officia dignissimos et neque maiores dolore provident. Illo rem ex labore ut?',300,'room-3.jpg'),
(2, 1, 'SU2','Lorem ipsum dolor sit amet consectetur adipisicing elit. Expedita libero porro incidunt error recusandae possimus necessitatibus iure assumenda suscipit? Ratione!',320, 'room-5.jpg'),
(3, 1, 'SU3','Lorem ipsum dolor sit amet consectetur adipisicing elit. Id dolorum aspernatur suscipit aut rem earum perferendis laboriosam architecto, numquam voluptates officia nulla quam! Praesentium, earum iure cumque veniam perspiciatis id illum culpa ipsum repudiandae molestias eaque minus nostrum, quo ut?',290,'room-3.jpg'),
(4, 1, 'SU4','Lorem ipsum dolor sit amet consectetur, adipisicing elit. Commodi, necessitatibus facere debitis odit dolor animi laboriosam dolorum facilis eaque nulla illo cupiditate deleniti sit? Ratione reiciendis hic doloribus illum labore laboriosam ut, dolorum inventore est aperiam quis omnis aut architecto.',285,'room-5.jpg'),
(5,2, 'IN1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad id facilis nesciunt animi perferendis! Aliquid, modi sint! Molestiae, minima mollitia aliquid velit quidem pariatur vero reprehenderit voluptatibus sunt ex ullam adipisci cupiditate eveniet corporis non dolore laudantium accusantium.',50, 'camere-10.jpg'),
(6,2, 'IN2','Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio labore dolor molestiae dolores at voluptates, ad rerum culpa veritatis. Rem adipisci voluptatibus rerum. Aliquam quia recusandae distinctio ut.',65,'room-1.jpg'),
(7,2, 'IN3','Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur at tenetur fuga non totam quas sapiente odio ullam. Obcaecati ab eius aliquam qui facilis eligendi ipsa impedit laborum consequuntur ut!',65,'room-1.jpg'),
(8,2, 'IN4','Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum quas accusamus nobis illo, autem illum nisi, distinctio corporis dolore reprehenderit nam voluptatum rem? Fugiat, deleniti voluptatum! Minus cupiditate voluptatibus totam beatae ducimus velit obcaecati, mollitia sint illum.',55,'camere-10.jpg'),
(9,3, 'FAM1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur, nam pariatur? Minus, praesentium itaque ex quam mollitia dicta obcaecati necessitatibus!',155,'camere-9.jpg'),
(10,3, 'FAM2','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Fuga reiciendis, veritatis maiores ex numquam voluptatum facere natus a accusamus nesciunt alias nulla tempora quaerat autem laudantium quibusdam quo excepturi velit laboriosam obcaecati qui, optio illo explicabo.',160,'camere-9.jpg'),
(11,4, 'LUX1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis iure molestias, recusandae dolorum officiis quia ex architecto, deserunt tempore laudantium quae veritatis provident corrupti nihil sequi delectus dolores quas accusamus.',450,'camere-4.jpg'),
(12,4, 'LUX2','Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus iure a quod cum. Voluptatum repellat similique facilis nisi libero corrupti natus repellendus, sit, eaque quis eos ut odit molestias voluptas.',550,'camere-4.jpg'),
(13,4, 'LUX3','Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, sunt laboriosam saepe earum nemo ullam veniam corrupti ratione dolorum eaque, distinctio sed maxime sequi ea provident amet aut optio atque!',500,'camere-4.jpg'),
(14,5, 'DOU1','Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio labore dolor mo...',80,'room-6.jpg'),
(15,5, 'DOU2','Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus iure a quod cum. Voluptatum repellat similique facilis nisi libero co',70,'room-4.jpg'),
(16,5, 'DOU3','Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad id facilis nesciunt animi perferendis!',85,'room-6.jpg'),
(17,5, 'DOU4','Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad id facilis nesciunt animi perferendis!',90,'room-4.jpg');

create table if not exists CLIENTS
(
    id               bigint AUTO_INCREMENT,
    name           	 varchar(100),
    lastname         varchar(100),
    email            varchar(100),
    PRIMARY KEY (id)
);
INSERT INTO CLIENTS VALUES (1, 'Pablo','Garcia','Garcia@seat.es'),(2, 'Oscar','Garcia','OGarcia@seat.com');
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
