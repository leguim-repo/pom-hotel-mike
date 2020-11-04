import React, { Component, useState } from 'react';
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale,  } from  "react-datepicker";
import es from 'date-fns/locale/es';
import { parseISO} from 'date-fns';


import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faCcVisa, faCcAmex,  faCcApplePay, faCcPaypal, faCcAmazonPay, faCcDiscover, } from '@fortawesome/free-brands-svg-icons';
import { apiGetBookPrice } from 'api/ApiServices';

//https://reactdatepicker.com/
//https://github.com/Hacker0x01/react-datepicker

class BookingServices extends Component {
    constructor(props) {
      super(props);
    
      this.state = {
        calculate: false,
        roomId: props.room.id,
        checkin: new Date(),
        checkout: new Date(),
        guests: 2,
        excludeDates: [],

        showBreakfast: false,
        showCarParking: false,
        showSpaService: false,
        showLaundryService: false,
        showShuttleService: false,
        discountCode: "",

        bookCalculate: {},


      }
      console.log('BookingServices.props: ',props);
      console.log('BookingServices.state: ',this.state);

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.onChangeGuests = this.onChangeGuests.bind(this);

      this.onChangeBreakfast = this.onChangeBreakfast.bind(this);
      this.onChangeCarParking = this.onChangeCarParking.bind(this);
      this.onChangeSpaService = this.onChangeSpaService.bind(this);
      this.onChangeLaundryService = this.onChangeLaundryService.bind(this);
      this.onChangeShuttleService = this.onChangeShuttleService.bind(this);
      this.onChangeCodeDiscount = this.onChangeCodeDiscount.bind(this);


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
      this.setState({discountCode: event.target.value.toUpperCase()});

    }

    async getPriceOfCurrentBook() { 
      console.log('getPriceOfCurrentBook.state: ',this.state);
      const book = {
          "roomId" : this.state.roomId,
          "checkIn" : this.state.checkin.toJSON().split("T")[0],
          "checkOut" : this.state.checkout.toJSON().split("T")[0],
          "guests" : this.state.guests,
          "breakfastService": this.state.showBreakfast,
          "carParkingService": this.state.showCarParking,
          "spaService": this.state.showSpaService,
          "laundryService": this.state.showLaundryService,
          "shuttleService": this.state.showShuttleService,
          "codeDiscount" : this.state.discountCode, 
          "totalPrice" : "0",

      }
      console.log('book: ',book);
      const bookCalculate = await apiGetBookPrice(book);
      console.log('getBookPrice: ',bookCalculate);
      return bookCalculate;
    }

    handleSubmit(e){
      e.preventDefault();
      console.log('props: ',this.props);
      console.log('state: ',this.state);
      var checkin = this.state.checkin.toJSON().split("T")[0];
      var checkout = this.state.checkout.toJSON().split("T")[0];
      var guests = this.state.guests;
      console.log('checkin: ', checkin, ' checkout: ',checkout, ' guests: ', guests);
      //this.props.history.push('/rooms/checkin='+checkin+'&checkout='+checkout+'&guests='+guests);

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
      console.log('render.state: ',this.state);

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
                  <FormGroup className="mt-1 ml-3">
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
                      <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.30em'}} id="guests" name="guests" type="select" onChange={this.onChangeGuests} defaultValue="2">
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

                <PricePerNight book={this.state.bookCalculate} />
                <BreakfastService book={this.state.bookCalculate} style={this.state.showBreakfast ? {} : { display: 'none' }} />
                <CarParkingService book={this.state.bookCalculate} style={this.state.showCarParking ? {} : { display: 'none' }} />
                <SpaService book={this.state.bookCalculate} style={this.state.showSpaService ? {} : { display: 'none' }} />
                <LaundryService book={this.state.bookCalculate} style={this.state.showLaundryService ? {} : { display: 'none' }} />
                <ShuttleService book={this.state.bookCalculate} style={this.state.showShuttleService ? {} : { display: 'none' }} />
                <Discount book={this.state.bookCalculate} style={this.state.showDiscount ? {} : { display: 'none' }} />

                <Row>
                  <Col><span>Total</span></Col>
                  <Col md={3}><span className="pull-right">{this.state.bookCalculate.totalBookingPrice} €</span></Col>
                  
                </Row>
                

                <Col className="m-auto">
                  <Row className="justify-content-center">
                    <Button type="submit" className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now</Button>
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
          </Col>
        </Form>
      </React.Fragment>

      )};
}



const CheckInPicker = (props) => {
  registerLocale('es', es)
  const [checkin, setCheckinDate] = useState(props.date);
  //console.log('CheckInPicker: ',checkin,' props: ',props);

  return (
    <DatePicker
        //inline
        //readOnly
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
        //readOnly
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
    <Col><span>{props.book.roomPricePerNight}€ x {props.book.totalNights} nights</span></Col>
    <Col md={3}><span className="pull-right">{props.book.roomTotalPrice} €</span></Col>
    </Row>
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
    <Col><span>Car Parking {props.book.carParkingPerNight}€ x {props.book.totalNights}</span></Col>
    <Col md={3}><span className="pull-right">{props.book.carParkingTotalPrice} €</span></Col>
    </Row>
  );
}

function SpaService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>SPA Facilities {props.book.spaPricePerNight}€ x {props.book.totalNights}</span></Col>
    <Col md={3}><span className="pull-right">{props.book.spaTotalPrice} €</span></Col>
    </Row>
  );
}

function LaundryService(props) {
  return(
    <Row className="" style={props.style}>
    <Col><span>Laundry Service {props.book.laundryPricePerNight}€ x {props.book.totalNights}</span></Col>
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
