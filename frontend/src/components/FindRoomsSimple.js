import React, { Component, useState } from 'react';
import { Button, FormGroup, Label, Input, FormText, Col, Row, Container } from 'reactstrap';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";

import { Control, Form, Errors } from 'react-redux-form';

//https://github.com/Hacker0x01/react-datepicker
const CheckInPicker = (props) => {
  const [checkin, setCheckinDate] = useState(props.date);
  //console.log('CheckInPicker: ',checkin,' props: ',props);
  return (
    <DatePicker selected={checkin} onChange={date => { setCheckinDate(date); props.handle(date)}}  placeholderText="Click to Check-in date" />
  );
}


const CheckOutPicker = (props) => {
  const [checkout, setCheckoutDate] = useState(props.date);
  //console.log('CheckOutPicker: ',checkout,' props: ',props);
  return (
    <DatePicker selected={checkout} onChange={date => { setCheckoutDate(date); props.handle(date)}}  placeholderText="Click to Check-out date" />
  );
}

class FindRoomsSimple extends Component {
    constructor(props) {
      super(props);
    
      // TODO pasar a redux
      this.state = {
        checkin: new Date(),
        checkout: new Date(),
      }

      this.handleCheckIn = this.handleCheckIn.bind(this);
      this.handleCheckOut = this.handleCheckOut.bind(this);
      this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleCheckIn(checkin) {
      console.log('handleCheckIn: ',checkin);
      this.setState={ checkin: checkin};
    }

    handleCheckOut(checkout) {
      console.log('handleCheckOut: ',checkout);
      this.setState={ checkout: checkout};
    }

    handleSubmit(e){
      e.preventDefault();
      console.log(e);
    }

    render() {
      console.log('checkin: ',this.state.checkin, ' checkout: ',this.state.checkout);
      return (
        <React.Fragment>
          <Container className="border border-danger">
            <Form model='formFindRoomSimple' onSubmit={(values) => this.handleSubmit(values)}>
              <Row>
                <Col>
                  <FormGroup>
                    <Row><h3>Book NOW</h3></Row>
                    <Row><h5>Best Price OnLine</h5></Row>                    
                  </FormGroup>
                </Col>

                <Col>
                  <FormGroup>
                    <Row><Label for="checkin">Checkin: </Label><br></br></Row>
                    <Row><CheckInPicker name="checkin" id="checkin" date={this.state.checkin} handle={this.handleCheckIn}></CheckInPicker></Row>
                  </FormGroup>
                </Col>
                
                <Col>
                  <FormGroup>
                    <Row><Label for="checkout">Checkin: </Label><br></br></Row>
                    <Row><CheckOutPicker id="checkout" date={this.state.checkout} handle={this.handleCheckOut}></CheckOutPicker></Row>
                  </FormGroup>
                </Col>

                <Col>
                  <FormGroup>
                    <Row><Label for="guests">Guests: </Label></Row>
                    <Row>
                      <Input model=".guests" id="guests" name="guests" type="select" name="select" id="guests">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>+5</option>
                      </Input>
                    </Row>
                  </FormGroup>
                </Col>
                <Col>
                  <Row>&nbsp;</Row>
                  <Row><Button type="submit" className="bg-warning">Find Rooms</Button></Row>
                </Col>
              </Row>
            </Form>
          </Container>
        </React.Fragment>

      )};
}
export default FindRoomsSimple;
