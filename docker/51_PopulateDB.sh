echo "Populate pom_hotel database with datas as rooms, rooms types, clients, logins..."
docker exec -i pom-mysql /bin/bash -c "/usr/bin/mysql --password=secret1234" <<<  $(cat pom-hotel-populate.sql)
