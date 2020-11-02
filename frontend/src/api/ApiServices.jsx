import axios from "axios";
//import api from "../api/apiEndPoints.json";

const apiBaseURL = "http://pom-hotel.code:8080/api"
const apiGetAllRooms = apiBaseURL+"/rooms";
const apiGetRoomById = apiBaseURL+"/roomdetail";
const apiCalculateBook = apiBaseURL+"/calculatebook";
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


export async function calculateBook(book) {
  const response = await axios(apiCalculateBook);
  const data = await response.data;
  console.log('calculateBook.book: ',book)
  console.log('calculateBook.data: ',data);
  return data;
}

