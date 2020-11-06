import React, { useState } from "react";
import { Container, Row, Col } from "reactstrap";

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import Loader from "../../components/Loader/Loader";

import RoomDetails from "../../components/RoomDetails/RoomDetails";
import { getRoomById } from "../../api/ApiServices";

import BookingServices from "../../components/BookingServices/BookingServices";

function DetailAndBookPage(props) {
  const [room, setRoom] = useState({});
  console.log("DetailAndBookPage.props: ", props, " room: ", room);

  React.useEffect(() => {
    getRoomById(props.match.params.room).then((data) => setRoom(data));

    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;

    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, [props.match.params.room]);

  return (
    <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader
          image={require("assets/img/high-performance.jpg")}
          sloganBig="POM HOTEL & SPA"
          sloganLittle="By Z-Devs Team"
        />
        <div className="main">
          <Container fluid>
            <Row>
              <Col>
                <h2 className="text-center H2Title">
                  Room details and Book Now
                </h2>
              </Col>
            </Row>

            <Row>
              <Col lg={4} xl={9} className="border border-danger">
                <Container fluid className="border border-dark">
                  {/* Doble render -> https://reactjs.org/docs/strict-mode.html#detecting-unexpected-side-effects*/}
                  {room.image === undefined ? (<Loader />) : (<RoomDetails showdetails room={room} />)}
                </Container>
              </Col>
              <Col lg={4} xl={3} className="border border-danger">
                {room.image === undefined ? (<Loader />) : (<BookingServices room={room}></BookingServices>)}
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
