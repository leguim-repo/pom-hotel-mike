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


export async function apiGetBookPrice(book) {
  var config = {
    method: 'post',
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
