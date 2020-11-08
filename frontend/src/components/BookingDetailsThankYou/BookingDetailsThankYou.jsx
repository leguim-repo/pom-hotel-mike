import React, { useState } from "react";
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';
import Loader from '../Loader/Loader';

function BookingDetailsThankYou(props) {
  const [room, setRoom] = useState({});
  const [prices, setPrices] = useState({});
  
  console.log('BookingDetailsThankYou.booking: ',props.booking)

  React.useEffect(() => {
    setRoom(props.booking.book);
    setPrices(props.booking.prices);

    return function cleanup() {

    };
  }, [props]);


  if ( 'roomsByFKRoomId' in room) {

    return (
      <React.Fragment>
        <Container fluid className="formExtend border border-danger">
          <FormGroup className="">
            <Col className="m-auto">
              <Row className="justify-content-center"><h3 className="mb-0">Services & Prices</h3></Row>
            </Col>
          </FormGroup>


          <Col className="m-auto">
              <FormGroup className="m-3">
                <Row><Label for="checkin">Checkin: </Label><br></br></Row>
                <Row style={{fontSize: '1.1em'}}>
                <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={room.checkIn} name="checkIn" type="text"/>
                </Row>
              </FormGroup>
          </Col>

          <Col className="m-auto">
              <FormGroup className="m-3">
                <Row><Label for="checkin">Checkout: </Label><br></br></Row>
                <Row style={{fontSize: '1.1em'}}>
                <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={room.checkOut} name="checkOut" type="text"/>
                </Row>
              </FormGroup>
          </Col>


          <Col className="m-auto">
              <FormGroup className="m-3">
                <Row><Label for="guests">Maximum Guests: </Label></Row>
                <Row>
                <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={room.guests} name="guests" type="text"/>
                </Row>
              </FormGroup>
          </Col>

          <Col className="m-auto">
              <FormGroup className="m-3">
                <Row><Label for="roomtype">Room Types: </Label></Row>
                <Row>
                <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={room.roomsByFKRoomId.roomtypesByFkRoomtypeId.name} name="roomtype" type="text"/>
                </Row>
              </FormGroup>
          </Col>

          <Col className="m-auto">
            <h6>Additional Services</h6>
            <FormGroup className="m-3" style={room.breakfast ? {} : { display: 'none' }}>
              <Row className="" style={props.style}>
              <Col><span>Breakfast {prices.breakFastPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.breakFastTotalPrice} €</span></Col>
              </Row>
            </FormGroup>

            <FormGroup className="m-3" style={room.carparking ? {} : { display: 'none' }}>
              <Row className="" style={props.style}>
              <Col><span>Car Parking {prices.carparkingPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.carParkingTotalPrice} €</span></Col>
              </Row>
            </FormGroup>





          </Col>
        </Container>
      </React.Fragment>
    );
  }
  else {
    return(<Loader>load</Loader>);
  }
}

export default BookingDetailsThankYou;



