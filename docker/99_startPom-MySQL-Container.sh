echo "Starting pom-mysql with persistance volume"
docker run --rm -d -p 3306:3306 --name pom-mysql -e MYSQL_ROOT_PASSWORD=secret1234 --mount src=pom-mysql-db-data,dst=/var/lib/mysql mysql:latest

