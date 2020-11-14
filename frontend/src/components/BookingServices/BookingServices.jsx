import React, { Component, useState } from 'react';
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';

import {NormalPrice, SpecialPrice } from '../NormalAndSpecialPrice/NormalAndSpecialPrice';

import { CheckInPicker, CheckOutPicker } from '../CustomDatePicker/CustomDatePicker';
import { parseISO} from 'date-fns';


import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCcVisa, faCcAmex,  faCcApplePay, faCcPaypal, faCcAmazonPay, faCcDiscover, } from '@fortawesome/free-brands-svg-icons';
import { apiGetBookPrice, apiPostBookRoomNow } from 'api/ApiServices';
import { Redirect } from 'react-router';

//https://reactdatepicker.com/
//https://github.com/Hacker0x01/react-datepicker


class BookingApiDTO {
  constructor() {
    console.log('BookingApiDTO.constructor: ');
    this.roomId = 0;
    this.checkIn = new Date();
    this.checkOut = new Date();
    this.guests = 2;
    this.breakfastService = false;
    this.carParkingService = false;
    this.spaService = false;
    this.laundryService = false;
    this.shuttleService = false;
    this.codeDiscount = "";
    this.email = "email@email.dot";
    this.errors = {email: "Fill with a valid email"}
    console.info('this: ',this);
  }

  setRoomId(value) {
    this.roomId = value;
  }
  setCheckIn(value) {
    this.checkIn = value;
  }
  setCheckOut(value) {
    this.checkOut = value;
  }
  setGuests(value) {
    this.guests = value;
  }
  setBreakfastService(value) {
    this.breakfastService = value;
  }
  setCarParkingService(value) {
    this.carParkingService = value;
  }
  setSpaService(value) {
    this.spaService = value;
  }
  setLaundryService(value) {
    this.laundryService = value;
  }
  setShuttleService(value) {
    this.shuttleService = value;
  }
  setCodeDiscount(value) {
    this.codeDiscount = value;
  }
  setEmail(value) {
    this.email = value;
  }
}


class BookingServices extends Component {
    constructor(props) {
      super(props);
    
      this.state = {
        calculate: false,
        roomId: props.room.id,
        checkin: props.checkin,
        checkout: props.checkout,
        guests: props.room.guests,
        excludeDates: [],

        showBreakfast: false,
        showCarParking: false,
        showSpaService: false,
        showLaundryService: false,
        showShuttleService: false,
        discountCode: "",
        email: "",
        bookCalculate: {},
        bookNowResult: {},
        errors: {email: ""},

      }
      console.log('BookingServices.constructor.props: ',props);
      console.log('BookingServices.constructor.state: ',this.state);

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.onChangeGuests = this.onChangeGuests.bind(this);

      this.onChangeBreakfast = this.onChangeBreakfast.bind(this);
      this.onChangeCarParking = this.onChangeCarParking.bind(this);
      this.onChangeSpaService = this.onChangeSpaService.bind(this);
      this.onChangeLaundryService = this.onChangeLaundryService.bind(this);
      this.onChangeShuttleService = this.onChangeShuttleService.bind(this);
      this.onChangeCodeDiscount = this.onChangeCodeDiscount.bind(this);
      this.onChangeEmail = this.onChangeEmail.bind(this);


      this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleCheckIn(checkin) {
      this.setState({ checkin: checkin, calculate: true});
    }

    handleCheckOut(checkout) {
      this.setState({ checkout: checkout, calculate: true});
    }

    onChangeGuests(event) {
      this.setState({ guests: event.target.value, calculate: true});
    }

    onChangeBreakfast() {
      this.setState({showBreakfast: !this.state.showBreakfast, calculate: true});

    }
    onChangeCarParking() {
      this.setState({showCarParking: !this.state.showCarParking, calculate: true});

    }
    onChangeSpaService() {
      this.setState({showSpaService: !this.state.showSpaService, calculate: true});

    }
    onChangeLaundryService() {
      this.setState({showLaundryService: !this.state.showLaundryService, calculate: true});

    }
    onChangeShuttleService() {
      this.setState({showShuttleService: !this.state.showShuttleService, calculate: true});
    }

    onChangeCodeDiscount(event) {
      this.setState({discountCode: event.target.value.toUpperCase(), calculate: true});

    }

    onChangeEmail(event) {
      this.setState({email: event.target.value});
    }

    async getPriceOfCurrentBook() { 
      const bookToCalculate = new BookingApiDTO();
      bookToCalculate.setRoomId(this.state.roomId);
      bookToCalculate.setCheckIn(this.state.checkin.toJSON().split("T")[0]);
      bookToCalculate.setCheckOut(this.state.checkout.toJSON().split("T")[0]);
      bookToCalculate.setGuests(this.state.guests);
      bookToCalculate.setBreakfastService(this.state.showBreakfast);
      bookToCalculate.setCarParkingService(this.state.showCarParking);
      bookToCalculate.setSpaService(this.state.showSpaService);
      bookToCalculate.setLaundryService(this.state.showLaundryService);
      bookToCalculate.setShuttleService(this.state.showShuttleService);
      bookToCalculate.setCodeDiscount(this.state.discountCode);
      bookToCalculate.setEmail(this.state.email);

      const bookCalculated = await apiGetBookPrice(bookToCalculate);
      return bookCalculated;
    }


    isValidEmail(email) {
      const reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
      if(!email || reg.test(email) === false){
          this.setState({errors: {email:"Email Field is Invalid"} });
          return false;
      } else {
          this.setState({errors: {email:""} });
      
          return true;
      }
    }

    async handleSubmit(e){
      e.preventDefault();

      if (this.isValidEmail(this.state.email) ){
        console.log('props: ',this.props);
        console.log('state: ',this.state);
        var checkin = this.state.checkin.toJSON().split("T")[0];
        var checkout = this.state.checkout.toJSON().split("T")[0];
        var guests = this.state.guests;
        console.log('checkin: ', checkin, ' checkout: ',checkout, ' guests: ', guests);
        console.log('handle.state: ',this.state);
        const finalBook = new BookingApiDTO();
        finalBook.setRoomId(this.state.roomId);
        finalBook.setCheckIn(this.state.checkin.toJSON().split("T")[0]);
        finalBook.setCheckOut(this.state.checkout.toJSON().split("T")[0]);
        finalBook.setGuests(this.state.guests);
        finalBook.setBreakfastService(this.state.showBreakfast);
        finalBook.setCarParkingService(this.state.showCarParking);
        finalBook.setSpaService(this.state.showSpaService);
        finalBook.setLaundryService(this.state.showLaundryService);
        finalBook.setShuttleService(this.state.showShuttleService);
        finalBook.setCodeDiscount(this.state.discountCode);
        finalBook.setEmail(this.state.email);
        console.log('handleSubmitBookNow: ',finalBook);

        const bookNowResult = await apiPostBookRoomNow(finalBook);
        this.setState({bookNowResult: bookNowResult});
      }
      
    }


    // https://www.valentinog.com/blog/await-react/
    async componentDidMount(){
      //recoger de la api que dias ya estan reservados y por lo tanto la habitacion no se puede reservar
      const datos= await this.getPriceOfCurrentBook();
      this.setState({bookCalculate: datos});
      this.setState({excludeDates: [parseISO('2020-10-27')]});
    }


    //https://sebastienlorber.com/handling-api-request-race-conditions-in-react
    // https://medium.com/javascript-in-plain-english/understanding-react-16-8-life-cycles-hooks-context-api-lazy-and-suspense-d80760f1b8f2
    async componentDidUpdate(){
      console.log('componentDidUpdate')
      if (this.state.calculate === true) {
        const datos=await this.getPriceOfCurrentBook();
        this.setState({bookCalculate: datos});
        this.setState({calculate: false});
      }
    }
    render() {

      console.log('BookingServices.props: ',this.props);
      console.log('BookingServices.state: ',this.state);


      if ('bookNowResult' in this.state.bookNowResult) {
        console.log('Redirect by.bookNowResult: ',this.state.bookNowResult)

        return(<Redirect push to={this.state.bookNowResult.bookLink}></Redirect>)
      }
      else {
        console.log('no redirect need');
      }

      return (
        <React.Fragment>
            <Form style={{margin: '0px'}} className="formExtend border mb-5" model='formFindRoomExtend' onSubmit={this.handleSubmit}>
              <Col>
                <FormGroup className="">
                  <Col className="m-auto">
                    <Row className="justify-content-center">
                      <h3 className="mb-0 ">Book Room & Services</h3>
                    </Row>
                  </Col>
                </FormGroup>

                <Col className="m-auto">
                  <FormGroup className="mt-1 ml-3">
                    <Row><Label htmlFor="checkin">Checkin: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}>
                      <CheckInPicker 
                            name="checkin" 
                            id="checkin" 
                            date={this.state.checkin} 
                            excludeDates={this.props.excludeDates} 
                            handle={this.handleCheckIn}>
                        </CheckInPicker>
                    </Row>
                  </FormGroup>
                </Col>
                
                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label htmlFor="checkout">Checkin: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}>
                      <CheckOutPicker 
                            id="checkout" 
                            date={this.state.checkout} 
                            excludeDates={this.props.excludeDates} 
                            handle={this.handleCheckOut}
                      ></CheckOutPicker></Row>
                  </FormGroup>
                </Col>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row>
                      <Label htmlFor="guests">Maximum Guests:&nbsp;<span>{this.props.room.guests}&nbsp;pax</span></Label>
                    </Row>
                  </FormGroup>
                </Col>

                
                <Col className="m-auto">
                <h6>Additional Services</h6>

                  <FormGroup className="mb-1 mt-1 ml-3">
                    <input type="checkbox" className="form-check-input" id="breakfast" onClick={this.onChangeBreakfast}/>
                    <label className="form-check-label" htmlFor="breakfast">Breakfast service at room</label>
                  </FormGroup>

                  <FormGroup className="mb-1 mt-1 ml-3">
                    <input type="checkbox" className="form-check-input" id="parking" onClick={this.onChangeCarParking}/>
                    <label className="form-check-label" htmlFor="parking">Car Parking</label>
                  </FormGroup>

                  <FormGroup className="mb-1 mt-1 ml-3">
                    <input type="checkbox" className="form-check-input" id="spa" onClick={this.onChangeSpaService}/>
                    <label className="form-check-label" htmlFor="spa">SPA Service</label>
                  </FormGroup>

                  <FormGroup className="mb-1 mt-1 ml-3">
                    <input type="checkbox" className="form-check-input" id="laundry" onClick={this.onChangeLaundryService}/>
                    <label className="form-check-label" htmlFor="laundry">Laundry Service</label>
                  </FormGroup>

                  <FormGroup className="mb-1 mt-1 ml-3">
                    <input type="checkbox" className="form-check-input" id="shuttle" onClick={this.onChangeShuttleService}/>
                    <label className="form-check-label" htmlFor="shuttle">Shuttle to Airport</label>
                  </FormGroup>

                  <FormGroup className="mb-1 mt-1 ml-3">
                    <Row>
                      <label className="form-check-label" htmlFor="codediscount">Code Discount</label><br/>
                    </Row>
                    <Row>
                      <input type="text" className="form-text-input" id="codediscount" onChange={this.onChangeCodeDiscount}/>
                    </Row>
                  </FormGroup>

                </Col>

                <hr className="border border-light"/>
                <h6>Services & Prices</h6>


                <PricePerNight book={this.state.bookCalculate} />
                <BreakfastService book={this.state.bookCalculate} style={this.state.showBreakfast ? {} : { display: 'none' }} />
                <CarParkingService book={this.state.bookCalculate} style={this.state.showCarParking ? {} : { display: 'none' }} />
                <SpaService book={this.state.bookCalculate} style={this.state.showSpaService ? {} : { display: 'none' }} />
                <LaundryService book={this.state.bookCalculate} style={this.state.showLaundryService ? {} : { display: 'none' }} />
                <ShuttleService book={this.state.bookCalculate} style={this.state.showShuttleService ? {} : { display: 'none' }} />
                <Discount book={this.state.bookCalculate} style={this.state.bookCalculate.codeDiscountPrice<0 ? {} : { display: 'none' }} />

                <hr className="border border-light"/>
                <Row>
                  <Col><span>Total</span></Col>
                  <Col md={3}><span className="pull-right">{this.state.bookCalculate.totalBookingPrice} €</span></Col>
                </Row>


                <Col className="m-auto">
                  <Row className="mt-2 mb-0 justify-content-center">
                    <p>Payment in our facilites</p>
                  </Row>
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

                <Col className="m-auto ">
                  <FormGroup className="mt-5 ml-3">
                    <Row className="">
                      <Label htmlFor="checkin">Please fill with your email for confirm your booking: </Label>
                      <br></br></Row>
                    <Row className="" style={{fontSize: '1.1em'}}>
                      <input type="email" className="" id="email" aria-describedby="emailHelp" placeholder="Enter email" onChange={this.onChangeEmail}/>
                      <div className="text-danger">{this.state.errors.email}</div>
                    </Row>
                    <Row className="justify-content-center mb-2">
                    <Button type="submit" className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now</Button>
                  </Row>
                  </FormGroup>
                </Col>
          </Col>
        </Form>
      </React.Fragment>

      )};
}



function PricePerNight(props) { 

  return(
    <React.Fragment>
      { props.book.longStay ? <SpecialPrice {...props} /> : <NormalPrice {...props} /> }
    </React.Fragment>

  );
}

function BreakfastService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Breakfast {props.book.breakFastPricePerNight}€ x {props.book.totalNights} nights</span></Col>
    <Col md={3}><span className="pull-right">{props.book.breakFastTotalPrice} €</span></Col>
    </Row>
  );
}

function CarParkingService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Car Parking {props.book.carParkingPricePerNight}€ x {props.book.totalNights} nights</span></Col>
    <Col md={3}><span className="pull-right">{props.book.carParkingTotalPrice} €</span></Col>
    </Row>
  );
}

function SpaService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>SPA Facilities {props.book.spaPricePerNight}€ x {props.book.totalNights} nights</span></Col>
    <Col md={3}><span className="pull-right">{props.book.spaTotalPrice} €</span></Col>
    </Row>
  );
}

function LaundryService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Laundry Service {props.book.laundryPricePerNight}€ x {props.book.totalNights} nights</span></Col>
    <Col md={3}><span className="pull-right">{props.book.laundryTotalPrice} €</span></Col>
    </Row>
  );
}

function ShuttleService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Shuttle to Airport {props.book.shuttlePricePerNight}€</span></Col>
    <Col md={3}><span className="pull-right">{props.book.shuttleTotalPrice} €</span></Col>
    </Row>

  );
}

function Discount(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Code Discount</span></Col>
    <Col md={3}><span className="pull-right">{props.book.codeDiscountPrice} €</span></Col>
    </Row>
  );
}

export default BookingServices;
