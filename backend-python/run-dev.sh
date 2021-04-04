docker run --rm --interactive --tty --name pom-hotel-python-backend-container \
       -p 8080:8080 \
       -v $(pwd)/pomhotel/booking:/booking \
       pom-hotel-python-backend

