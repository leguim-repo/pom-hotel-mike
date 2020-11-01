import React, { Component, useState } from 'react';

import { Button, FormGroup, Label, Input, FormText, Col, Row, Container } from 'reactstrap';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale,  } from  "react-datepicker";
import es from 'date-fns/locale/es';
import { parseISO} from 'date-fns';

import { Control, Form, Errors } from 'react-redux-form';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCcVisa, faCcAmex,  faCcApplePay, faCcPaypal, faCcAmazonPay, faCcDiscover, } from '@fortawesome/free-brands-svg-icons';


//https://reactdatepicker.com/
//https://github.com/Hacker0x01/react-datepicker

const CheckInPicker = (props) => {
  registerLocale('es', es)
  const [checkin, setCheckinDate] = useState(props.date);
  //console.log('CheckInPicker: ',checkin,' props: ',props);

  return (
    <DatePicker
        //inline
        readOnly
        locale="es"
        minDate={new Date()}
        dateFormat="dd/MM/yyyy"
        selected={checkin} 
        onChange={date => { setCheckinDate(date); props.handle(date)}}
        excludeDates={props.excludeDates}

        //highlightDates={props.excludeDates}
        //highlightDates={highlightWithRanges}
        />
  );
}


const CheckOutPicker = (props) => {
  const [checkout, setCheckoutDate] = useState(props.date);
  //console.log('CheckOutPicker: ',checkout,' props: ',props);
  return (
    <DatePicker 
        //inline
        readOnly
        locale="es"
        minDate={new Date()}
        dateFormat="dd/MM/yyyy"
        selected={checkout} 
        onChange={date => { setCheckoutDate(date); props.handle(date)}}
        excludeDates={props.excludeDates}
        />
  );
}



function PricePerNight(props) {

  return(
    <Row className="" style={props.style}>
    <Col><span>{props.pricePerNight}€ x {props.nights} nights</span></Col>
    <Col md={3}><span className="pull-left">price €</span></Col>
    </Row>
  );
}

function BreakfastService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Breakfast service</span></Col>
    <Col md={3}><span className="pull-left">{props.price} €</span></Col>
  </Row>
  );
}

function CarParkingService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Car Parking</span></Col>
    <Col md={3}><span className="pull-left">{props.price} €</span></Col>
  </Row>
  );
}

function SpaService(props) {
  return();
}


class BookingServices extends Component {
    constructor(props) {
      super(props);
    
      this.state = {
        checkin: new Date(),
        checkout: new Date(),
        guests: 2,
        pricemin: 1,
        pricemax: 100,
        roomtype: 0,
        excludeDates: [],
        nights: 3,

        showBreakfast: false,
        showCarParking: false,
        showSpaService: false,
        showLaundryService: false,
        showShuttleService: false,

      }
      console.log('BookingServices.props: ',props);
      console.log('BookingServices.state: ',this.state);

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.handleGuests = this.handleGuests.bind(this);

      this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleCheckIn(checkin) {
      this.setState({ checkin: checkin});
    }

    handleCheckOut(checkout) {
      this.setState({ checkout: checkout});
    }

    handleGuests(guests) {
      console.log('guests: ',guests.target.value)
      this.setState({ guests: guests.target.value});
    }

    handleSubmit(){
      //e.preventDefault();
      console.log('props: ',this.props);
      console.log('state: ',this.state);
      var checkin = this.state.checkin.toJSON().split("T")[0];
      var checkout = this.state.checkout.toJSON().split("T")[0];
      var guests = this.state.guests;
      console.log('checkin: ', checkin, ' checkout: ',checkout, ' guests: ', guests);
      //this.props.history.push('/rooms/checkin='+checkin+'&checkout='+checkout+'&guests='+guests);
      
    }

    componentDidMount(){
      //recoger de la api que dias ya estan reservados y por lo tanto la habitacion no se puede reservar
      this.setState({excludeDates: [parseISO('2020-10-27')]})
      console.log(new Date(),parseISO('2020-10-27'))
    }
    render() {
      console.log('checkin: ',this.state.checkin, ' checkout: ',this.state.checkout);
      return (
        <React.Fragment>
            <Form style={{margin: '0px'}} className="formExtend border" model='formFindRoomExtend' onSubmit={this.handleSubmit}>
              <Col>
              <FormGroup className="">
                    <Col className="m-auto">
                      <Row><h3 className="mb-0">Book Room & Services</h3></Row>
                    </Col>
                  </FormGroup>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label htmlFor="checkin">Checkin: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}>
                      <CheckInPicker 
                            name="checkin" 
                            id="checkin" 
                            date={this.state.checkin} 
                            excludeDates={this.state.excludeDates} 
                            handle={this.handleCheckIn}>
                        </CheckInPicker>
                    </Row>
                  </FormGroup>
                </Col>
                
                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label htmlFor="checkout">Checkin: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}><CheckOutPicker id="checkout" date={this.state.checkout} handle={this.handleCheckOut}></CheckOutPicker></Row>
                  </FormGroup>
                </Col>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label htmlFor="guests">Guests: </Label></Row>
                    <Row>
                      <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="guests" name="guests" type="select" onChange={this.handleGuests} defaultValue="2">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>+5</option>
                      </Input>
                    </Row>
                  </FormGroup>
                </Col>

                
                <Col className="m-auto">
                <h6>Additional Services</h6>

                  <FormGroup className="m-3">
                    <input type="checkbox" className="form-check-input" id="breakfast"/>
                    <label className="form-check-label" htmlFor="breakfast">Breakfast service at room</label>
                  </FormGroup>

                  <FormGroup className="m-3">
                    <input type="checkbox" className="form-check-input" id="parking"/>
                    <label className="form-check-label" htmlFor="parking">Car Parking</label>
                  </FormGroup>

                  <FormGroup className="m-3">
                    <input type="checkbox" className="form-check-input" id="spa"/>
                    <label className="form-check-label" htmlFor="spa">SPA Service</label>
                  </FormGroup>

                  <FormGroup className="m-3">
                    <input type="checkbox" className="form-check-input" id="laundry"/>
                    <label className="form-check-label" htmlFor="laundry">Laundry Service</label>
                  </FormGroup>

                  <FormGroup className="m-3">
                    <input type="checkbox" className="form-check-input" id="shuttle"/>
                    <label className="form-check-label" htmlFor="shuttle">Shuttle to Airport</label>
                  </FormGroup>

                  <FormGroup className="m-3">
                    <Row>
                      <label className="form-check-label" htmlFor="codediscount">Code Discount</label><br/>
                    </Row>
                    <Row>
                      <input type="text" className="form-text-input" id="codediscount"/>
                    </Row>
                  </FormGroup>

                </Col>

                <hr className="border border-light"/>

                <PricePerNight pricePerNight={this.props.room.pricePerNight} nights={this.state.nights} />
                <BreakfastService style={this.state.showBreakfast ? {} : { display: 'none' }} />
                <CarParkingService style={this.state.showCarParking ? {} : { display: 'none' }} />



                <Row className="">
                  <Col><span>SPA Facilities</span></Col>
                  <Col md={3}><span className="pull-left">price €</span></Col>
                </Row>


                <Row className="">
                  <Col><span>Laundry service</span></Col>
                  <Col md={3}><span className="pull-left">price €</span></Col>
                </Row>

                <Row className="">
                  <Col><span>Shuttle to Airport</span></Col>
                  <Col md={3}><span className="pull-left">price €</span></Col>
                </Row>

                <Row className="">
                  <Col><span>Discount</span></Col>
                  <Col md={3}><span className="pull-left">price €</span></Col>
                </Row>

                <Row className="mb-4">
                  <Col><span></span></Col>
                  <Col md={3}><span className="pull-left"></span></Col>
                </Row>

                <Row>
                  <Col><span>Total</span></Col>
                  <Col md={3}><span className="pull-left">price €</span></Col>
                </Row>
                

                <Col className="m-auto">
                  <Row className="justify-content-center">
                    <Button type="submit" className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now</Button>
                  </Row>
                  <br></br>
                </Col>

            <Container className="mb-2 ">
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

      )};
}
export default BookingServices;
