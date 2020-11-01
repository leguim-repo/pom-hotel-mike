import React from "react";
import { Container, Row, Col, Button, Form, Input } from "reactstrap";


// core components
import PomNavbar from "components/Navbars/PomNavbar";
import PomHeader from "components/Headers/PomHeader";
import PomFooter from "components/Footers/PomFooter";

import RoomDetails from "components/RoomDetails";
import { getRoomById } from "../api/ApiServices"

import DatePicker from 'react-datepicker';
import BookingServices from "components/BookingServices";


function BookNowPage(props) {
  const { room } = props.location.state
  console.log('BookNowPage.props: ',props, ' id:',props.match.params.room)
  console.log('room: ',room);




  React.useEffect(() => {
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
  

  return (
    <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader image={require("assets/img/high-performance.jpg")} sloganBig="POM HOTEL & SPA" sloganLittle="By Z-Devs Team"/>
        <div className="main">
          <h2 className="text-center H2Title">Book Now</h2>
          <Container  className="mb-5">
            <Row>
            <Col md={8}>
              <Container className="border border-dark">
                <RoomDetails room={room}></RoomDetails>
              </Container>
            </Col>
            <Col md={4}>
            <BookingServices room={room}></BookingServices>
            </Col>
            </Row>
          </Container>
        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default BookNowPage;
