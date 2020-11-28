echo "Creating backend package"
mvn package

echo "Launching backend"
java -jar target/booking-0.0.1-SNAPSHOT.jar
