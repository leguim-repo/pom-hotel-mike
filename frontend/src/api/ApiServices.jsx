import axios from "axios";
//import api from "../api/apiEndPoints.json";

const apiBaseURL = "http://pom-hotel.code:8080/api/v1"
const apiGetAllRooms = apiBaseURL+"/getallrooms";
const apiGetRoomById = apiBaseURL+"/getroombyid";
const apiCalculateBook = apiBaseURL+"/calculatepriceofbook";
const apiBookedDatesByRoomId = apiBaseURL+"/getallreserveddatesbyroomid";
const apiRoomsAndBookedDates = apiBaseURL+"/getallroomswithreserveddates";
const apiGetDataBookingById = apiBaseURL+"/getbookingbyidandprices";
const apiBookRoomNow = apiBaseURL+"/bookingroomnow";
export const apiGetMusicLink = apiBaseURL+"/music";


export async function getRoomById(id) {
  const response = await axios(apiGetRoomById+'/'+id);
  const data = await response.data;
  console.log('getRoomById.response: ',response)
  console.log('getRoomById.data: ',data)
  return data;
}


export async function getAllRooms() {
  const response = await axios(apiGetAllRooms);
  const data = await response.data;
  console.log('getAllRooms: ',data);
  return data;
}

export async function getAllRoomsAndBookedDates() {
  const response = await axios(apiRoomsAndBookedDates);
  const data = await response.data;
  console.log('getAllRoomsAndBookedDates: ',data);
  //metemos el objecto de bookedDates dentro de rooms
  let roomsAndBookedDates = [];
  for (let i=0; i<data.length; i++){
    let objRoom = data[i].room;
    let objDates = data[i].bookedDates;
    objRoom.bookedDates = objDates;
    roomsAndBookedDates.push(objRoom);
  }
  return roomsAndBookedDates;
}

export async function apiGetBookPrice(book) {
  var config = {
    method: 'POST',
    url: apiCalculateBook,
    headers: { 
      'Content-Type': 'application/json'
    },
    data : book
  };

  const response = await axios(config);
  const data = await response.data;
  console.log('getBookPrice.book: ',book)
  console.log('getBookPrice.data: ',data);
  return data;
}

export async function apiGetBookedDatesByRoomId(roomId) {
  const response = await axios(apiBookedDatesByRoomId+'/'+roomId);
  const data = await response.data;
  console.log('apiGetBookedDatesByRoomId.response: ',response)
  console.log('apiGetBookedDatesByRoomId.data: ',data)
  return data;
}

export async function apiPostBookRoomNow(book) {
  var config = {
    method: 'POST',
    url: apiBookRoomNow,
    headers: { 
      'Content-Type': 'application/json'
    },
    data : book
  };

  const response = await axios(config);
  const data = await response.data;
  console.log('apiPostBookRoomNow.book: ',book)
  console.log('apiPostBookRoomNow.data: ',data);
  return data;
}

export async function apiGetBookingById(bookingId) {
  const response = await axios(apiGetDataBookingById+'/'+bookingId);
  const data = await response.data;
  console.log('apiGetBookingById.response: ',response)
  console.log('apiGetBookingById.data: ',data)
  return data;
}