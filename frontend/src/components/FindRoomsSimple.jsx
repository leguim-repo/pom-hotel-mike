import React, { Component, useState } from 'react';
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

class FindRoomsSimple extends Component {
    constructor(props) {
      super(props);
    
      // TODO pasar a redux
      this.state = {
        checkin: new Date(),
        checkout: new Date(),
        excludeDates: [],
      }

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleCheckIn(checkin) {
      console.log('handleCheckIn: ', checkin);
      this.setState={ checkin: checkin};
    }

    handleCheckOut(checkout) {
      console.log('handleCheckOut: ', checkout);
      this.setState={ checkout: checkout};
    }

    handleSubmit(e){
      //e.preventDefault();
      console.log('e: ',e,'\ns: ',this.state);
    }

    componentDidMount(){
      //recoger de la api que dias ya estan reservados y por lo tanto la habitacion no se puede reservar
      this.setState({excludeDates: [parseISO('2020-10-27')]})
    }
    render() {
      console.log('checkin: ',this.state.checkin, ' checkout: ',this.state.checkout);
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
                    <Row style={{fontSize: '1.1em'}}><CheckInPicker name="checkin" id="checkin" date={this.state.checkin} excludeDates={this.state.excludeDates} handle={this.handleCheckIn}></CheckInPicker></Row>
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
                      <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="guests" name="guests" type="select" name="select" id="guests">
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
