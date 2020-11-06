
import { getAllRooms } from "api/ApiServices";


class StorageManager {
  async setupApplication(){
    const data =localStorage.getItem("pom-hotel");
    if (data === null ) {
      const rooms = await getAllRooms();
      const dataset = {
        rooms: rooms,
        checkin: new Date(),
        checkout: new Date(),
      }
      console.log('pom-hotel not found');
      localStorage.setItem('pom-hotel',JSON.stringify(dataset));
    }
    else {
      console.log('pom-hotel found')
    }
  }

  getCheckin(checkin) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkin = checkin
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }

  saveCheckin(checkin) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkin = checkin
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }
  saveCheckout(checkout) {
    const storage = JSON.parse(localStorage.getItem('pom-hotel'));
    storage.checkout = checkout
    localStorage.setItem('pom-hotel',JSON.stringify(storage));
  }
}


export default new StorageManager();