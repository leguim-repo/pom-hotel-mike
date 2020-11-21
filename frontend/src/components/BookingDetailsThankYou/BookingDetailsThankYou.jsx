import React, { useState } from "react";
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';
import Loader from '../Loader/Loader';
import { NormalPrice, SpecialPrice } from '../NormalAndSpecialPrice/NormalAndSpecialPrice';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCcVisa, faCcAmex,  faCcApplePay, faCcPaypal, faCcAmazonPay, faCcDiscover, } from '@fortawesome/free-brands-svg-icons';

import '@deckdeckgo/qrcode';
import { defineCustomElements as deckDeckGoElement } from '@deckdeckgo/qrcode/dist/loader';
deckDeckGoElement();


function BookingDetailsThankYou(props) {
  const [book, setBook] = useState({});
  const [prices, setPrices] = useState({});
  const [global, setGlobal] = useState({});
  console.log('BookingDetailsThankYou.booking: ',props.booking)

  React.useEffect(() => {
    setBook(props.booking.book);
    setPrices(props.booking.prices);
    let dummyObj = {};
    dummyObj.book = {...props.booking.book, ...props.booking.prices}; //chapu

    setGlobal(dummyObj);
    return function cleanup() {

    };
  }, [props]);


  var estiloQR = { "--deckgo-qrcode-size": "250px",
                  };

  // https://github.com/kazuhikoarase/qrcode-generator
  // npm install @deckdeckgo/qrcode --save
  // https://docs.deckdeckgo.com/components/qrcode/
  // https://kazuhikoarase.github.io/qrcode-generator/js/demo/

  // calendario chulisimo Blocking Dates and Time
  // https://ej2.syncfusion.com/react/demos/#/material/schedule/block-events


  // https://github.com/zxing/zxing
  // https://zxing.appspot.com/generator

  if ( 'roomsByFKRoomId' in book) {
    console.log('global: ',global);

    const checkInCal = book.checkIn.replace(/-/g,"");
    const checkOutCal = book.checkOut.replace(/-/g,"");
    const eol = "  ";
    const evento =  "BEGIN:VEVENT\r" +
                    "SUMMARY:Booking POM Hotel & SPA\n" +
                    "DTSTART;VALUE=DATE:" + checkInCal + "\n" +
                    "DTEND;VALUE=DATE:" + checkOutCal + "\n" +
                    "LOCATION:Las Ramblas 124 Barcelona 080002\n" +
                    "URL:http://pom-hotel.code:3000/thankyou/" + book.id + "\n" +
                    "DESCRIPTION:" +
                          "Room Name: "+book.roomsByFKRoomId.roomtypesByFkRoomtypeId.name + eol +
                          "Max Guests: "+book.guests + eol +
                          "Total Nights: " + global.book.totalNights + eol +
                          "Total Price: " + prices.totalBookingPrice + "\n" +
                    "END:VEVENT";
    

    return (
      <React.Fragment>
        <Form style={{margin: '0px'}} className="formExtend border mb-5">
          <FormGroup className="">
            <Col className="m-auto">
              <Row className="justify-content-center"><h3 className="mt-3 mb-1">Booking in Detail</h3></Row>
            </Col>
          </FormGroup>

          <Col>CheckIn:&nbsp; <span>{new Date(book.checkIn).toLocaleDateString('es-ES')}</span></Col>
          <Col>CheckOut:&nbsp; <span>{new Date(book.checkOut).toLocaleDateString('es-ES')}</span></Col>
          <Col>Maximum Guests:&nbsp; <span>{book.guests}</span></Col>
          <Col>Maximum Guests:&nbsp; <span>{book.roomsByFKRoomId.roomtypesByFkRoomtypeId.name}</span></Col>


          <Col className="m-auto mb-5">
            <hr className="border border-light"/>
            <h6>Services & Prices</h6>

            <Row className="">
              <Col>
                { prices.longStay ? <SpecialPrice {...global} /> : <NormalPrice {...global} /> }
              </Col>
            </Row>

            <Row className="" style={book.breakfast ? {} : { display: 'none' }}>
              <Col><span>Breakfast {prices.breakFastPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.breakFastTotalPrice} €</span></Col>
            </Row>

            <Row className="" style={book.carparking ? {} : { display: 'none' }}>
              <Col><span>Car Parking {prices.carParkingPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.carParkingTotalPrice} €</span></Col>
              </Row>

            <Row className="" style={book.spa ? {} : { display: 'none' }}>
              <Col><span>SPA Service {prices.spaPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.spaTotalPrice !== 0 ? <>{prices.spaTotalPrice} €</> : <>included</>}</span></Col>
            </Row>

            <Row className="" style={book.laundry ? {} : { display: 'none' }}>
              <Col><span>Laundry Service {prices.laundryPricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.laundryTotalPrice} €</span></Col>
            </Row>

            <Row className="" style={book.shuttle ? {} : { display: 'none' }}>
              <Col><span>Shuttle to Airport {prices.shuttlePricePerNight}€ x {prices.totalNights} nights</span></Col>
              <Col md={3}><span className="pull-right">{prices.shuttleTotalPrice} €</span></Col>
            </Row>

            <Row className="" style={prices.codeDiscountPrice < 0 ? {} : { display: 'none' }}>
            <Col><span>Code Discount: {book.codediscount}</span></Col>
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

            <Container className="mb-0">
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

            <Col className="m-auto">
              <Row className="mt-5 mb-1 justify-content-center">
                <p>Your current booking</p>
              </Row>
              <Row className="justify-content-center mb-4 bg-white">
                <deckgo-qrcode 
                  style={estiloQR}
                  qrCellSize = {20}
                  type={"svg"}
                  content={evento}
                  deckgo-qrcode-size="300px"
                  >
                  <img slot="logo" src={require("assets/img/Z-500x500.png")} alt="logo" />
                </deckgo-qrcode>
              </Row>
            </Col>

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



