import React, { useState } from 'react';
import { Button, FormGroup, Label, Input, Col, Row, Form} from 'reactstrap';

import DatePicker from 'react-datepicker';
import "react-datepicker/dist/react-datepicker.css";
import { registerLocale,  } from  "react-datepicker";
import es from 'date-fns/locale/es';
import {parseISO} from 'date-fns';
import { now } from 'moment';

registerLocale('es', es)

const initialFilter = {
  pricefrom: '',
  priceto: '',
  roomtype: '',
  guests: '',
  checkin: '',
  rawCheckin: '',
  checkout: '',
  rawCheckout: '',

};
const SearchForm = (props) => {
  const [filter, setFilter] = useState(initialFilter);


  function handleChangeCheckin(date) {
    const theDate = date.toJSON().split("T")[0];
    console.log('handleChangeCheckin: ',theDate);

    const newFilter = {
      ...filter,
      'checkin': theDate,
      'rawCheckin': date,
    }
    setFilter(newFilter);
    props.onChangeFilter(newFilter);
  }

  function handleChangeCheckOut(date) {
    const theDate = date.toJSON().split("T")[0];
    console.log('handleChangeCheckin: ',theDate);

    const newFilter = {
      ...filter,
      'checkout': theDate,
      'rawCheckout': date,
    }
    setFilter(newFilter);
    props.onChangeFilter(newFilter);
  }

  function handleChange(event) {
    console.log('event: ',event);
    console.log('event.target: ',event.target);
    console.log('event.value: ',event.target.value);
    const newFilter = {
      ...filter,
      [event.target.name]: event.target.value,
    }
    setFilter(newFilter);
    props.onChangeFilter(newFilter);
}
  return (
    <React.Fragment>
    <Col>
    <div className='filter container'>
      <input
        type='number'
        placeholder='price from'
        name='price_from'
        value={filter.price_from}
        onChange={handleChange}
        />
      <input
        type='number'
        placeholder='price to'
        name='price_to'
        value={filter.price_to}
        onChange={handleChange}
        />
      <input
        type='number'
        placeholder='guest_from'
        name='guest_from'
        value={filter.guest_from}
        onChange={handleChange}
        />
      <input
        type='text'
        placeholder='type'
        name='type'
        value={filter.type}
        onChange={handleChange}
        />
      <input
        type='date'
        placeholder='date_from'
        name='date_from'
        value={filter.date_from}
        onChange={handleChange}
        />
      <input
        type='date'
        placeholder='date_to'
        name='date_to'
        value={filter.date_to}
        onChange={handleChange}
        />

  </div>

  </Col>

  <hr/>
  <div className="container">
  <React.Fragment>
            <Form style={{margin: '0px'}} className="formExtend border">
              <Col>
              <FormGroup className="">
                    <Col className="m-auto">
                      <Row className="justify-content-center"><h3 className="mb-0">Find your room</h3></Row>
                    </Col>
                  </FormGroup>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label for="checkin">Checkin: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}>
                      <DatePicker
                        name="checkin"
                        locale="es"
                        dateFormat="dd/MM/yyyy"
                        selected={filter.rawCheckin}
                        onChange={date => handleChangeCheckin(date)}
                        excludeDates={[]}
                        //highlightDates={props.excludeDates}
                        //highlightDates={highlightWithRanges}
                      />
                    </Row>
                  </FormGroup>
                </Col>
                
                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label for="checkout">Checkout: </Label><br></br></Row>
                    <Row style={{fontSize: '1.1em'}}>
                    <DatePicker
                      name="checkin"
                      locale="es"
                      dateFormat="dd/MM/yyyy"
                      selected={filter.rawCheckout}
                      onChange={date => handleChangeCheckOut(date)}
                      excludeDates={[]}
                      //highlightDates={props.excludeDates}
                      //highlightDates={highlightWithRanges}
                    />
                    </Row>
                  </FormGroup>
                </Col>

                <Col className="m-auto">
                  <FormGroup className="m-3">
                    <Row><Label for="guests">Guests: </Label></Row>
                    <Row>
                      <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="guests" name="guests" type="select" value={filter.guests} onChange={handleChange}>
                        <option value="">No filter</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
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
                          <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="pricefrom" name="pricefrom" type="select" value={filter.pricefrom} onChange={handleChange}>
                            <option value="">No filter</option>
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
                        <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="priceto" name="priceto" type="select" value={filter.priceto} onChange={handleChange}> 
                            <option value="">No filter</option>
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
                      <Input className="bg-white" style={{fontSize: '1.0em', padding: '0.45em'}} id="roomtype" name="roomtype" type="select" value={filter.roomtype} onChange={handleChange}>
                        <option value="">All types</option>
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
                    <Button type="submit" onClick={(e)=> {e.preventDefault(); props.onClickClear();}}className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Clear Filter</Button>
                  </Row>
                  <br></br>
                </Col>
              </Col>
            </Form>
        </React.Fragment>
    </div>
    </React.Fragment>

  );
};

export default SearchForm;