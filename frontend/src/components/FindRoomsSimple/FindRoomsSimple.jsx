import React, { Component } from 'react';
import StorageManager from "components/StorageManager/StorageManager";

import { Link } from 'react-router-dom';
import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';

import { CheckInPicker, CheckOutPicker } from "../CustomDatePicker/CustomDatePicker";
import {parseISO} from 'date-fns';
import { getAllRooms, apiGetBookedDatesByRoomId } from 'api/ApiServices';



//https://reactdatepicker.com/
//https://github.com/Hacker0x01/react-datepicker



class FindRoomsSimple extends Component {
    constructor(props) {
      super(props);
      this.state = {
        rooms: [],
        excludeDates: [],
        checkin: new Date(),
        checkout: new Date(),
        guests: 2,
      }

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.handleGuests = this.handleGuests.bind(this);
    }

    handleCheckIn(checkin) {
      this.setState({ checkin: checkin});
      StorageManager.saveCheckin(checkin);
    }

    handleCheckOut(checkout) {
      this.setState({ checkout: checkout});
      StorageManager.saveCheckout(checkout);

    }

    handleGuests(guests) {
      this.setState({ guests: guests.target.value});
    }


    async componentDidMount(){

      // TODO fetch de todas las rooms y todas las booked dates

      const bookedDatesFromApi = await apiGetBookedDatesByRoomId(1);
      const excludeDates = bookedDatesFromApi.map( (day) => parseISO(day));
      const rooms = await getAllRooms();
      this.setState({excludeDates: excludeDates, rooms: rooms});

    }
    render() {
      console.log("FindRoomSimple.state: ",this.state, "\nRoomFindRoomSimplePage.props: ",this.props);
      return (
        <React.Fragment>
          <Container fluid className="formSimple">
            <Form model='formFindRoomSimple' onSubmit={this.handleSubmit}>
              <Row>
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
                    <Row style={{fontSize: '1.1em'}}>
                      <CheckOutPicker id="checkout" date={this.state.checkout} handle={this.handleCheckOut}></CheckOutPicker>
                    </Row>
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
                  <Row className="justify-content-center">Find your room now!!</Row>
                  <Row className="justify-content-center">
                    <Link to= {{pathname: '/rooms', state: this.state}}>
                      <Button className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Find Rooms</Button>
                    </Link>  
                  </Row>
                </Col>
              </Row>
            </Form>
          </Container>
        </React.Fragment>

      )};
}
export default FindRoomsSimple;


/* 

    this.handleSubmit = this.handleSubmit.bind(this);



    handleSubmit(e){
      //e.preventDefault();
      /*
      console.log('props: ',this.props);
      console.log('state: ',this.state);
      console.log('e: ', e);
      var checkin = this.state.checkin.toJSON().split("T")[0];
      var checkout = this.state.checkout.toJSON().split("T")[0];
      var guests = this.state.guests;
      console.log('checkin: ', checkin, ' checkout: ',checkout, ' guests: ', guests);
      this.props.history.push('/rooms/checkin='+checkin+'&checkout='+checkout+'&guests='+guests);
      
    }

*/