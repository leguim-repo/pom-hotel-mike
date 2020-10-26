import React, { Component, useState } from 'react';
import { useHistory } from "react-router";

import { Button, FormGroup, Label, Input, FormText, Col, Row, Container } from 'reactstrap';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale,  } from  "react-datepicker";
import es from 'date-fns/locale/es';
import {parseISO} from 'date-fns';

import { Control, Form, Errors } from 'react-redux-form';

//https://reactdatepicker.com/
//https://github.com/Hacker0x01/react-datepicker

const CheckInPicker = (props) => {
  registerLocale('es', es)
  const [checkin, setCheckinDate] = useState(props.date);
  //console.log('CheckInPicker: ',checkin,' props: ',props);

  return (
    <DatePicker
        //inline
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
        locale="es"
        minDate={new Date()}
        dateFormat="dd/MM/yyyy"
        selected={checkout} 
        onChange={date => { setCheckoutDate(date); props.handle(date)}}
        excludeDates={props.excludeDates}
        />
  );
}

class FindRoomsExtend extends Component {
    constructor(props) {
      super(props);
    
      // TODO pasar a redux

      this.state = {
        checkin: new Date(),
        checkout: new Date(),
        guests: 2,
        pricemin: 1,
        pricemax: 100,
        roomtype: 0,
        excludeDates: [],
      }

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.handleGuests = this.handleGuests.bind(this);
      this.handlePriceMin = this.handlePriceMin.bind(this);
      this.handlePriceMax = this.handlePriceMax.bind(this);
      this.handleRoomTypes = this.handleRoomTypes.bind(this);

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

    handlePriceMin(pricemin) {
      this.setState({ pricemin: pricemin.target.value});
    }

    handlePriceMax(pricemax) {
      this.setState({ pricemax: pricemax.target.value});
    }

    handleRoomTypes(roomtype) {
      console.log(roomtype.target.value)
      this.setState({ roomtype: roomtype.target.value});

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
                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><h3>Book NOW</h3></Row>
                    <Row><h5>Best Price OnLine</h5></Row>
                  </FormGroup>
                </Col>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label for="checkin">Checkin: </Label><br></br></Row>
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
                    <Row><Label for="checkout">Checkin: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}><CheckOutPicker id="checkout" date={this.state.checkout} handle={this.handleCheckOut}></CheckOutPicker></Row>
                  </FormGroup>
                </Col>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label for="guests">Guests: </Label></Row>
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
                  <FormGroup className="m-3">
                    <Row>
                      <Col className="m-2">
                        <Row><Label for="pricemin">Price from: </Label></Row>
                        <Row>
                          <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="pricemin" name="pricemin" type="select" onChange={this.handlePriceMin} defaultValue="1">
                            <option>1</option>
                            <option>50</option>
                            <option>100</option>
                            <option>200</option>
                            <option>300</option>
                          </Input>
                        </Row>
                      </Col>

                      <Col className="m-2">
                      <Row><Label for="pricemax">Price to: </Label></Row>
                      <Row>
                        <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="pricemax" name="pricemax" type="select"  onChange={this.handlePriceMax} defaultValue="100">
                            <option>100</option>
                            <option>200</option>
                            <option>300</option>
                            <option>500</option>
                            <option>1000</option>
                        </Input>
                      </Row>
                    </Col>
                    </Row>
                  </FormGroup>
                </Col>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label for="roomtype">Room Types: </Label></Row>
                    <Row>
                      <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="roomtype" name="roomtype" type="select" onChange={this.handleRoomTypes} defaultValue="0">
                        <option value="0">All types</option>
                        <option value="1">Suite room</option>
                        <option value="2">Individual room</option>
                        <option value="3">Family room</option>
                        <option value="4">Luxury room</option>
                        <option value="5">Double room</option>
                      </Input>
                    </Row>
                  </FormGroup>
                </Col>


                <Col className="m-auto">
                  <Row className="justify-content-center">Find your room now!!</Row>
                  <Row className="justify-content-center">
                    <Button type="submit" className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Find Rooms</Button>
                  </Row>
                  <br></br>
                </Col>
              </Col>
            </Form>
        </React.Fragment>

      )};
}
export default FindRoomsExtend;
