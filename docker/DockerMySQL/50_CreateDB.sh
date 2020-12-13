echo "Creating pom_hotel database"
docker exec -i pom-mysql /bin/bash -c "/usr/bin/mysql --password=secret1234" <<<  $(cat pom-hotel-schema.sql)


