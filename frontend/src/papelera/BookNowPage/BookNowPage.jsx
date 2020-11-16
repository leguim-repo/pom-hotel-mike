import React from "react";
import { Container, Row, Col } from "reactstrap";
import { Redirect }from "react-router";

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";

import RoomDetails from "../../components/RoomDetails/RoomDetails";

import BookingServices from "../../components/BookingServices/BookingServices";


function BookNowPage(props) {
  console.log('BookNowPage.props: ',props, ' id:',props.match.params.room)


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
  

  if (props.location.state === undefined) {
    console.info('user try booknow without room selected')
    return(<Redirect push to="/" />);
  }
    
  const { room } = props.location.state;
  console.log('room: ',room);

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
