
import { getAllRooms } from "api/ApiServices";
import {parseISO} from 'date-fns';


class StorageManager {
  async setupApplication(){
    const data =localStorage.getItem("pom-hotel");
    if (data === null ) {
      const rooms = await getAllRooms();
      const dataset = {
        rooms: rooms,
        checkin: new Date(),
        checkout: new Date(),
        guests: 2,

      }
      console.log('pom-hotel not found');
      localStorage.setItem('pom-hotel',JSON.stringify(dataset));
    }
    else {
      console.log('pom-hotel found')
    }
  }

  getCheckin() {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    return parseISO(storage.checkin);
  }
  
  saveCheckin(checkin) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkin = checkin;
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }

  getCheckout() {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    return parseISO(storage.checkout);
  }
  saveCheckout(checkout) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkout = checkout;
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }


  getGuests() {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    return parseISO(storage.guests);
  }
  saveGuests(guests) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    console.log('storage: ',storage);
    storage.guests = guests;
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }

  getValues() {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    return storage;
  }
}


export default new StorageManager();