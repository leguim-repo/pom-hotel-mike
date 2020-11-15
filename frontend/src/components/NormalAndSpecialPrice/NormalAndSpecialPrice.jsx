import React from "react";
import { Col, Row } from 'reactstrap';
import Loader from '../Loader/Loader';
export function NormalPrice(props) {
  console.log('NormalPrice.props: ',props);

  if ( 'book' in props) {
      return(
        <Row className="" style={props.style}>
          <Col>
            <span>{props.book.roomPricePerNight}€ x {props.book.totalNights} nights</span>
          </Col>
          <Col md={3}>
            <span className="pull-right">{props.book.roomTotalPrice} €</span>
          </Col>
        </Row>
      );
    }
    else {
      return(<Loader></Loader>)
    }
  }
  
export function SpecialPrice(props) {
    console.log('SpecialPrice.props: ',props);
    if ( 'book' in props) {

      return(
        <React.Fragment>
        <Row className="" style={props.style}>
          <Col>
            <span style={{textDecoration: 'line-through'}}>{props.book.roomPricePerNight}€ x {props.book.totalNights} nights</span>
          </Col>
          <Col md={3}>
            <span style={{textDecoration: 'line-through'}} className="pull-right">{props.book.roomTotalPrice} €</span>
          </Col>
        </Row>
        <Row><Col><span>20% Discount of room price for long stay</span></Col></Row>
        <Row className="" style={props.style}>
          <Col>
            <span>{props.book.roomSpecialPricePerNight}€ x {props.book.totalNights} nights</span>
          </Col>
          <Col md={3}>
            <span className="pull-right">{props.book.roomSpecialTotalPrice} €</span>
          </Col>
        </Row>
      </React.Fragment>
      );
    }
    else {
      return(<Loader></Loader>)
    }    
  }