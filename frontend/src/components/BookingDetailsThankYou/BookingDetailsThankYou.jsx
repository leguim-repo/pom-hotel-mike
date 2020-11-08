import React from "react";
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';

function BookingDetailsThankYou(props) {
  console.log('BookingDetailsThankYou.booking: ',props.booking)
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
              <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={props.booking.checkIn} name="checkIn" type="text"/>
              </Row>
            </FormGroup>
        </Col>

        <Col className="m-auto">
            <FormGroup className="m-3">
              <Row><Label for="checkin">Checkout: </Label><br></br></Row>
              <Row style={{fontSize: '1.1em'}}>
              <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={props.booking.checkOut} name="checkOut" type="text"/>
              </Row>
            </FormGroup>
        </Col>


        <Col className="m-auto">
            <FormGroup className="m-3">
              <Row><Label for="guests">Maximum Guests: </Label></Row>
              <Row>
              <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={props.booking.guests} name="guests" type="text"/>
              </Row>
            </FormGroup>
        </Col>

        <Col className="m-auto">
            <FormGroup className="m-3">
              <Row><Label for="roomtype">Room Types: </Label></Row>
              <Row>
              <input readOnly className="" style={{fontSize: '1.0em', padding: '0.30em'}} value={props.booking.roomsByFKRoomId.roomtypesByFkRoomtypeId.name} name="roomtype" type="text"/>
              </Row>
            </FormGroup>
        </Col>

        <Col className="m-auto">
          <h6>Additional Services</h6>
          <FormGroup className="m-3" style={props.booking.breakfast ? {} : { display: 'none' }}>
            <Row className="" style={props.style}>
              {/*
            <Col><span>Breakfast {props.book.breakFastPricePerNight}€ x {props.book.totalNights} nights</span></Col>
            <Col md={3}><span className="pull-right">{props.book.breakFastTotalPrice} €</span></Col>

              */}
            </Row>
          </FormGroup>







        </Col>
      </Container>
    </React.Fragment>
  );
}

export default BookingDetailsThankYou;
