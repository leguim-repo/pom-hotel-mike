#!/bin/bash
function waitinXsec() {
    echo "Waiting $1 seconds"
    sleep $1
}
source 10_CreatePersitenceVolume.sh
source 20_CreateImage.sh
source 98_stopPom-MySQL-Container.sh
waitinXsec 10
source 99_startPom-MySQL-Container.sh
waitinXsec 10
source 50_CreateDB.sh
source 51_PopulateDB.sh
docker ps
source 98_stopPom-MySQL-Container.sh
waitinXsec 10
docker ps
