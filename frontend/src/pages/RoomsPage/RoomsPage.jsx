import React, { useEffect, useState } from 'react';
import { Button, Container, Row, Col } from "reactstrap";

import FindRoomsExtend from '../../components/FindRoomsExtend/FindRoomsExtend';
import { getAllRoomsAndBookedDates } from '../../api/ApiServices';
import { isDateBetween } from '../../services/date.service';
import RoomDetails from "../../components/RoomDetails/RoomDetails";

import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import GotoTop from "../../components/GotoTop/GotoTop";
import Loading from "../../components/Loader/Loader";
import "./RoomsPage.css"
import { Link } from 'react-router-dom';



const initialFilter = {
  pricefrom: '',
  priceto: '',
  roomtype: '',
  guests: '',
  checkin: '',
  checkout:  '',
};

function RoomsPage() {
  const [rooms, setRooms] = useState([]);
  const [filter, setFilter] = useState(initialFilter);
  useEffect(() => {
    getAllRoomsAndBookedDates().then((data) => setRooms(data));
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;

    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, []);


  function resetFilter(event) {
    setFilter(initialFilter);
  }

  const roomsFiltered = rooms.filter((room) => {
    let validPricePerNightFrom = filter.pricefrom
      ? room.pricePerNight >= +filter.pricefrom
      : true;
    let validPricePerNightTo = filter.priceto
      ? room.pricePerNight <= +filter.priceto
      : true;
    let validGuest = filter.guests
      ? room.guests === +filter.guests
      : true;
    let validType = filter.roomtype ? room.roomtypesByFkRoomtypeId.id === +filter.roomtype : true;
    //let validType = room.code.includes(filter.roomtype);
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



  const RenderRooms = roomsFiltered.map( (room) => (
                        <Container fluid key={room.id} className="border border-dark mb-5">
                          <RoomDetails key={room.id} room={room}></RoomDetails>
                          <div className='booked border border-danger'>
                            <p>Booked Dates. Only for development purposes</p>
                            {room.bookedDates.map((date) => (
                              <div key={date}>{date}</div>
                              ))}
                          </div>
                          <Row className="mb-2">
                            <Col></Col>
                            <Col className="text-center">
                              <Link to= {{pathname: '/roomdetail/'+room.id}} onClick={() => {console.log('click button link')}} >
                                <Button className="bg-warning mb-3 " style={{fontSize: '1.2em', padding: '0.5em'}}>Details & Book</Button>
                              </Link>
                            </Col>
                          </Row>
                        </Container>
                      ));
  
  console.log('RoomsPage.filter: ',filter);

  return (
    <React.Fragment>
      <PomNavbar />
        <div className="wrapper">
          <PomHeader image={require("assets/img/revato-10251-13112723-111323.jpg")} sloganBig="Find your rest" sloganLittle="in the paradise"/>
          <div className="main">
            <Container fluid>
            <Row>
              <Col>
                <h2 className="text-center H2Title">Our Rooms</h2>
              </Col>
            </Row>
            <Row >
              <Col md={9} className="">
                {RenderRooms}
              </Col>
              <Col md={3} className="">
                <FindRoomsExtend onChangeFilter={setFilter} onClickClear={resetFilter}/>
              </Col>
            </Row>
            </Container>
            <GotoTop></GotoTop>
          </div>
          <PomFooter />
        </div>
    </React.Fragment>
  );
}

export default RoomsPage;


/*


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

*/