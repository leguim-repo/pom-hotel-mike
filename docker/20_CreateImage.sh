echo "Pulling MySQL Image and running pom-mysql container"
docker run -d -p 3306:3306 --name pom-mysql -e MYSQL_ROOT_PASSWORD=secret1234 --mount src=pom-mysql-db-data,dst=/var/lib/mysql mysql:latest
