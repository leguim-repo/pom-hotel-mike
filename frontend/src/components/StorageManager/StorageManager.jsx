
import { getAllRooms, getAllRoomsAndBookedDates } from "api/ApiServices";
import {parse, parseISO} from 'date-fns';


class StorageManager {
  async setupApplication(){
    const data =localStorage.getItem("pom-hotel");
    if (data === null ) {
      const rooms = await getAllRooms();
      const roomsAndBookedDates = await getAllRoomsAndBookedDates();
      const dataset = {
        rooms: rooms,
        checkin: new Date(),
        checkout: new Date(),
        guests: "3",
        roomsAndBookedDates: roomsAndBookedDates,

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
    if (storage === null) {
      return((new Date()))
    }
    else {
      return parseISO(storage.checkin);
    }
  }
  
  saveCheckin(checkin) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkin = checkin;
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }

  getCheckout() {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    if (storage === null) {
      return((new Date()))
    }
    else {
      return parseISO(storage.checkout);
    }
  }
  saveCheckout(checkout) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkout = checkout;
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }


  getGuests() {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    if (storage === null) {
      return("2")
    }
    else {
      return parseISO(storage.guests);
    } 
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