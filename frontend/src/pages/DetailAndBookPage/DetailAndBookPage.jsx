import React, { useContext, useState } from "react";
import { Container, Row, Col } from "reactstrap";

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import Loader from "../../components/Loader/Loader";

import RoomDetails from "../../components/RoomDetails/RoomDetails";
import { getRoomById, apiGetBookedDatesByRoomId } from "../../api/ApiServices";

import BookingServices from "../../components/BookingServices/BookingServices";

import StorageManager from "../../components/StorageManager/StorageManager";


function DetailAndBookPage(props) {
  const [room, setRoom] = useState({});
  const [checkin, setCheckin] = useState(new Date());
  const [checkout, setCheckout] = useState(new Date());
  const [bookedDates, setBookedDates] = useState([]);

  console.log("DetailAndBookPage.props: ", props, " room: ", room);
  
  React.useEffect(() => {
    getRoomById(props.match.params.room).then((data) => setRoom(data));
    apiGetBookedDatesByRoomId(props.match.params.room).then((data) => setBookedDates(data));

    setCheckin(StorageManager.getCheckin());
    setCheckout(StorageManager.getCheckout());


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

  if (room !== undefined) {
    console.log("DetailAndBookPage.room: ",room, ' bookedDate: ',bookedDates);
  }
  return (
    <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader
          image={require("assets/img/high-performance2.jpg")}
          sloganBig="POM HOTEL & SPA"
          sloganLittle="By Z-Devs Team"
        />
        <div className="main" style={{ backgroundImage: "url(" + require("assets/img/pattern.png") + ")",}}>
          <Container fluid>
            <Row>
              <Col>
                <h2 className="text-center H2Title">
                  Room details and Book Now
                </h2>
              </Col>
            </Row>

            <Row>
              <Col lg={4} xl={9} className="">
                <Container fluid className="border border-dark">
                  {/* Doble render -> https://reactjs.org/docs/strict-mode.html#detecting-unexpected-side-effects*/}
                  {room.image === undefined ? (<Loader />) : (<RoomDetails showdetails room={room} />)}
                </Container>
              </Col>
              <Col lg={4} xl={3} className="">
                {room.image === undefined ? (<Loader />) : (<BookingServices room={room} checkin={checkin} checkout={checkout} excludeDates={bookedDates}></BookingServices>)}
              </Col>
            </Row>
          </Container>
        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default DetailAndBookPage;
