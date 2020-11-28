#!/bin/bash
echo "Launching POM Hotel & SPA Project. Cross the fingers"
echo "The backend run in background. Remember kill it (ps command is your friend)"
read -n 1 -r -s -p $'Press enter to continue...\n'

cd docker
source 99_startPom-MySQL-Container.sh
cd ..

cd backend
source upJar.sh &
cd ..

echo "Launching frontend"
sleep 5
cd frontend
npm run start
cd ..

