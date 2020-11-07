import React, { useEffect, useState } from 'react';
import { Button, FormGroup, Label, Input, Col, Row, Form} from 'reactstrap';

import SearchForm from '../../components/SearchForm/SearchForm';
import { getAllRoomsAndBookedDates } from '../../api/ApiServices';

import { isDateBetween } from '../../services/date.service';

import RoomDetails from "../../components/RoomDetails/RoomDetails";

const initialFilter = {
  price_from: '',
  price_to: '',
  type: '',
  guest_from: '',
  date_from:'',
  checkin: '',
  date_to:  '',
};

function RoomPageFilter() {
  const [rooms, setRooms] = useState([]);

  const [filter, setFilter] = useState(initialFilter);
  useEffect(() => {
    getAllRoomsAndBookedDates().then((data) => setRooms(data));

  }, []);


  function resetFilter(event) {
    setFilter(initialFilter);
  }

  const roomsFiltered = rooms.filter((room) => {
    let validPricePerNightFrom = filter.price_from
      ? room.pricePerNight >= +filter.price_from
      : true;
    let validPricePerNightTo = filter.price_to
      ? room.pricePerNight <= +filter.price_to
      : true;
    let validGuest = filter.guest_from
      ? room.guests >= +filter.guest_from
      : true;
    //  let validType = filter.type ? room.code === filter.type : true;
    let validType = room.code.includes(filter.type);

    let validDate = !room.bookedDates.some(
      (date) =>
        isDateBetween(date, filter.checkin, filter.checkout),
    );

    return (
      validPricePerNightFrom &&
      validPricePerNightTo &&
      validGuest &&
      validType &&
      validDate
    );
  });

  console.log('filter: ',filter);
  return (
    <div className='main'>

          <div className="container-fluid">     
            <Row>
              <Col>
                <h2 className="text-center H2Title">Our Rooms</h2>
              </Col>
            </Row>
            <Row >
              <Col md={9} className="border border-danger">
              {roomsFiltered.map((room) => (
                <div key={room.id}>
                  <span>{room.id}</span>
                  <RoomDetails key={room.id} room={room}></RoomDetails>
                  <div className='booked'>
                    {room.bookedDates.map((date) => (
                      <div key={date}>{date}</div>
                      ))}
                  </div>
              </div>
              ))}
              </Col>
              <Col md={3} className="border border-danger">
                <SearchForm onChangeFilter={setFilter}/>
                <button onClick={resetFilter}>reset</button>
              </Col>
            </Row>
          </div>
    </div>
  );
}

export default RoomPageFilter;

/*

          
          <div className='room' key={room.id}>
            <div className='id'>{room.id}</div>
            <div className='price'>{room.pricePerNight}</div>
            <div className='type'>{room.code}</div>
            <div className='guest'>{room.guests}</div>
            <div className='booked'>
              {room.bookedDates.map((date) => (
                <div key={date}>{date}</div>
                ))}
            </div>
          </div>
*/