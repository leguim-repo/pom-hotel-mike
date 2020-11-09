import React, { useState } from "react";
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';
import Loader from '../Loader/Loader';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCcVisa, faCcAmex,  faCcApplePay, faCcPaypal, faCcAmazonPay, faCcDiscover, } from '@fortawesome/free-brands-svg-icons';
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
        <Form style={{margin: '0px'}} className="formExtend border mb-5">
          <FormGroup className="">
            <Col className="m-auto">
              <Row className="justify-content-center"><h3 className="mb-0">Booking in Detail</h3></Row>
            </Col>
          </FormGroup>


          <Col className="m-auto">
              <FormGroup className="m-3">
                <Row><Label for="checkin">Checkin: </Label><br></br></Row>
                <Row style={{fontSize: '1.1em'}}>
                <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={new Date(room.checkIn).toLocaleDateString('es-ES')} name="checkIn" type="text"/>
                </Row>
              </FormGroup>
          </Col>

          <Col className="m-auto">
              <FormGroup className="m-3">
                <Row><Label for="checkin">Checkout: </Label><br></br></Row>
                <Row style={{fontSize: '1.1em'}}>
                <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={new Date(room.checkOut).toLocaleDateString('es-ES')} name="checkOut" type="text"/>
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



          <Col className="m-auto mb-5">
            <hr className="border border-light"/>
            <h6>Services & Prices</h6>
              <Row className="" style={room.breakfast ? {} : { display: 'none' }}>
              <Col><span>Breakfast {prices.breakFastPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.breakFastTotalPrice} €</span></Col>
              </Row>

            <Row className="" style={room.carparking ? {} : { display: 'none' }}>
              <Col><span>Car Parking {prices.carparkingPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.carParkingTotalPrice} €</span></Col>
              </Row>

            <Row className="" style={room.spa ? {} : { display: 'none' }}>
              <Col><span>SPA Service {prices.spaPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.spaTotalPrice} €</span></Col>
            </Row>

            <Row className="" style={room.laundry ? {} : { display: 'none' }}>
              <Col><span>Laundry Service {prices.laundryPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.laundryTotalPrice} €</span></Col>
            </Row>

            <Row className="" style={room.shuttle ? {} : { display: 'none' }}>
              <Col><span>Shuttle to Airport {prices.shuttlePricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.shuttleTotalPrice} €</span></Col>
            </Row>

            <Row className="" style={room.shuttle ? {} : { display: 'none' }}>
            <Col><span>Code Discount: {room.codediscount}</span></Col>
              <Col md={3}><span className="pull-right">{prices.codeDiscountPrice} €</span></Col>
            </Row>

            <hr className="border border-light"/>


            <Row>
              <Col><span>Total</span></Col>
              <Col md={3}><span className="pull-right">{prices.totalBookingPrice} €</span></Col>
            </Row>

            <Col className="m-auto">
              <Row className="mt-2 mb-0 justify-content-center">
                <p>Pay pending in our facilites</p>
              </Row>
            </Col>

            <Container className="mb-2">
              <Row>We accept:</Row>
              <Row className="justify-content-center">
                <FontAwesomeIcon icon={faCcVisa} size="2x" className="m-2"/>
                <FontAwesomeIcon icon={faCcAmex} size="2x" className="m-2"/>
                <FontAwesomeIcon icon={faCcDiscover} size="2x" className="m-2"/>
                <FontAwesomeIcon icon={faCcApplePay} size="2x" className="m-2"/>
                <FontAwesomeIcon icon={faCcAmazonPay} size="2x" className="m-2"/>
                <FontAwesomeIcon icon={faCcPaypal} size="2x" className="m-2"/>
              </Row>
            </Container>

          </Col>
        </Form>
      </React.Fragment>
    );
  }
  else {
    return(<Loader>load</Loader>);
  }
}

export default BookingDetailsThankYou;



