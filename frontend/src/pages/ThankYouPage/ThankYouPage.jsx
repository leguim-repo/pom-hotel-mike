import React, {useState} from "react";
import { Col, Row, Container } from 'reactstrap';

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import RoomDetailsThankYou from "../../components/RoomDetailsThankYou/RoomDetailsThankYou";
import BookingDetailsThankYou from "../../components/BookingDetailsThankYou/BookingDetailsThankYou";
import Loader from "../../components/Loader/Loader";
import { apiGetBookingById } from "api/ApiServices";



function ThankYouPage(props) {
  const [booking, setBooking] = useState({});
 
  React.useEffect(() => {
    apiGetBookingById(props.match.params.id).then((data) => setBooking(data));

    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;
    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, [props]);


  console.log('ThankYouPage.booking: ',booking);
  if ( 'roomsByFKRoomId' in booking) {
    return (
      <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader image={require("assets/img/img_bg_1.jpg")} thankyoupage sloganBig="Thank you for Booking" sloganLittle="See you soon!!"/>
        <div className="main" style={{ backgroundImage: "url(" + require("assets/img/pattern.png") + ")",}}>
          <Container fluid>
            <Row>
              <Col>
                <h2 className="text-center H2Title">Your Booking Details</h2>
              </Col>
            </Row>
            <Row >
              <Col md={9} className="border border-dark">
                <RoomDetailsThankYou showdetails room={booking.roomsByFKRoomId}/>
              </Col>
              <Col md={3} className="border border-dark">
                <BookingDetailsThankYou booking={booking}/>
              </Col>
            </Row>
          </Container>
        </div>
        <PomFooter />
      </div>
      </React.Fragment>
    );
  }
  else {
    return(<Loader></Loader>)
  }
}

export default ThankYouPage;


/*
*/