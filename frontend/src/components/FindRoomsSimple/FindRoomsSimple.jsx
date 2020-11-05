import React, { Component, useState } from 'react';

import { Button, FormGroup, Label, Input, Col, Row, Container,Form } from 'reactstrap';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale,  } from  "react-datepicker";
import es from 'date-fns/locale/es';
import {parseISO} from 'date-fns';
import { apiGetBookedDatesByRoomId } from 'api/ApiServices';


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

class FindRoomsSimple extends Component {
    constructor(props) {
      super(props);
    
      // TODO pasar a redux

      this.state = {
        checkin: new Date(),
        checkout: new Date(),
        guests: 2,
        excludeDates: [],
      }

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

    handleSubmit(e){
      //e.preventDefault();
      console.log('props: ',this.props);
      console.log('state: ',this.state);
      console.log('e: ', e);
      var checkin = this.state.checkin.toJSON().split("T")[0];
      var checkout = this.state.checkout.toJSON().split("T")[0];
      var guests = this.state.guests;
      console.log('checkin: ', checkin, ' checkout: ',checkout, ' guests: ', guests);
      this.props.history.push('/rooms/checkin='+checkin+'&checkout='+checkout+'&guests='+guests);
      
    }

    async componentDidMount(){
      //recoger de la api que dias ya estan reservados y por lo tanto la habitacion no se puede reservar
      //this.setState({excludeDates: [parseISO('2020-10-27')]})
      const bookedDatesFromApi = await apiGetBookedDatesByRoomId(1);
      const bookedDates = bookedDatesFromApi.map( (day) => parseISO(day));
      this.setState({excludeDates: bookedDates})
    }
    render() {
      console.log('checkin: ',this.state.checkin, ' checkout: ',this.state.checkout,' excludeDates: ',this.state.excludeDates);
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
                  <Row className="justify-content-center">Find your room now!!</Row>
                  <Row className="justify-content-center"><Button type="submit" className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Find Rooms</Button></Row>
                </Col>
              </Row>
            </Form>
          </Container>
        </React.Fragment>

      )};
}
export default FindRoomsSimple;
