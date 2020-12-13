use pom_hotel;

INSERT INTO roomtypes VALUES
    (1, 'Suite room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
    (2, 'Individual room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
    (3, 'Family room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
    (4, 'Luxury room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?'),
    (5, 'Double room','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Vero eum at qui, deserunt repudiandae sint sunt quam accusamus ipsam unde! Aliquid odio doloremque illo perferendis cum eaque magni eius harum?')
    ;


INSERT INTO rooms VALUES
    (1, 1, 'SU1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Cum doloribus sed, magnam corrupti sapiente aspernatur dolorum ullam sequi officia dignissimos et neque maiores dolore provident. Illo rem ex labore ut?',300,'room-3.jpg',2),
    (2, 1, 'SU2','Lorem ipsum dolor sit amet consectetur adipisicing elit. Expedita libero porro incidunt error recusandae possimus necessitatibus iure assumenda suscipit? Ratione!',320, 'room-5.jpg',2),
    (3, 1, 'SU3','Lorem ipsum dolor sit amet consectetur adipisicing elit. Id dolorum aspernatur suscipit aut rem earum perferendis laboriosam architecto, numquam voluptates officia nulla quam! Praesentium, earum iure cumque veniam perspiciatis id illum culpa ipsum repudiandae molestias eaque minus nostrum, quo ut?',290,'room-3.jpg',2),
    (4, 1, 'SU4','Lorem ipsum dolor sit amet consectetur, adipisicing elit. Commodi, necessitatibus facere debitis odit dolor animi laboriosam dolorum facilis eaque nulla illo cupiditate deleniti sit? Ratione reiciendis hic doloribus illum labore laboriosam ut, dolorum inventore est aperiam quis omnis aut architecto.',285,'room-5.jpg',2),
    (5,2, 'IN1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad id facilis nesciunt animi perferendis! Aliquid, modi sint! Molestiae, minima mollitia aliquid velit quidem pariatur vero reprehenderit voluptatibus sunt ex ullam adipisci cupiditate eveniet corporis non dolore laudantium accusantium.',50, 'camere-10.jpg',1),
    (6,2, 'IN2','Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio labore dolor molestiae dolores at voluptates, ad rerum culpa veritatis. Rem adipisci voluptatibus rerum. Aliquam quia recusandae distinctio ut.',65,'room-1.jpg',1),
    (7,2, 'IN3','Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur at tenetur fuga non totam quas sapiente odio ullam. Obcaecati ab eius aliquam qui facilis eligendi ipsa impedit laborum consequuntur ut!',65,'room-1.jpg',1),
    (8,2, 'IN4','Lorem ipsum dolor sit amet consectetur adipisicing elit. Rerum quas accusamus nobis illo, autem illum nisi, distinctio corporis dolore reprehenderit nam voluptatum rem? Fugiat, deleniti voluptatum! Minus cupiditate voluptatibus totam beatae ducimus velit obcaecati, mollitia sint illum.',55,'camere-10.jpg',1),
    (9,3, 'FAM1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Consequuntur, nam pariatur? Minus, praesentium itaque ex quam mollitia dicta obcaecati necessitatibus!',155,'camere-9.jpg',4),
    (10,3, 'FAM2','Lorem ipsum dolor sit, amet consectetur adipisicing elit. Fuga reiciendis, veritatis maiores ex numquam voluptatum facere natus a accusamus nesciunt alias nulla tempora quaerat autem laudantium quibusdam quo excepturi velit laboriosam obcaecati qui, optio illo explicabo.',160,'camere-9.jpg',5),
    (11,4, 'LUX1','Lorem ipsum dolor sit amet consectetur adipisicing elit. Corporis iure molestias, recusandae dolorum officiis quia ex architecto, deserunt tempore laudantium quae veritatis provident corrupti nihil sequi delectus dolores quas accusamus.',450,'camere-4.jpg',2),
    (12,4, 'LUX2','Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus iure a quod cum. Voluptatum repellat similique facilis nisi libero corrupti natus repellendus, sit, eaque quis eos ut odit molestias voluptas.',550,'camere-4.jpg',3),
    (13,4, 'LUX3','Lorem ipsum, dolor sit amet consectetur adipisicing elit. Expedita, sunt laboriosam saepe earum nemo ullam veniam corrupti ratione dolorum eaque, distinctio sed maxime sequi ea provident amet aut optio atque!',500,'camere-4.jpg',2),
    (14,5, 'DOU1','Lorem ipsum dolor, sit amet consectetur adipisicing elit. Optio labore dolor mo...',80,'room-6.jpg',2),
    (15,5, 'DOU2','Lorem ipsum dolor sit amet consectetur adipisicing elit. Temporibus iure a quod cum. Voluptatum repellat similique facilis nisi libero co',70,'room-4.jpg',2),
    (16,5, 'DOU3','Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad id facilis nesciunt animi perferendis!',85,'room-6.jpg',2),
    (17,5, 'DOU4','Lorem ipsum dolor sit amet consectetur adipisicing elit. Ad id facilis nesciunt animi perferendis!',90,'room-4.jpg',3)
    ;


INSERT INTO clients VALUES 
    (1, 'Pablo','Garcia','Garcia@seat.es'),
    (2, 'Oscar','Garcia','OGarcia@seat.com'),
    (3, 'Miguel','de Pablos','mdpablos@seat.com')
    ;


INSERT INTO bookings VALUES
    (1, 1, 1, '2020-11-10','2020-11-11','demo@demo.dot',2,1,1,1,1,1,"code",0),
    (2, 1, 1, '2020-10-13','2020-11-14','demo@demo.dot',2,1,1,1,1,1,"code",0),
    (3, 1, 1, '2020-10-15','2020-11-16','demo@demo.dot',2,1,1,1,1,1,"code",0),
    (4, 1, 2, '2020-10-18','2020-11-20','demo@demo.dot',2,1,1,1,1,1,"code",0)
    ;


INSERT INTO logins VALUES 
    (1, 1, 'Garcia1989','1234','ROLE_CLIENT',1),
    (2, 2, 'Oscar2000','1234','ROLE_CLIENT',1),
    (3, 3, 'Miguel','1234','ROLE_CLIENT',1)
    ;
