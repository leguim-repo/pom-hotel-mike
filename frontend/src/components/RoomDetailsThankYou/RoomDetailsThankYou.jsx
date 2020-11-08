import React from "react";
import { Container, Row, Col, Button, Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle } from "reactstrap";
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBath, faTv, faWifi, faSmokingBan } from '@fortawesome/free-solid-svg-icons'
import '../../assets/css/ribbon.css';


function RoomDetailsThankYou(props) {
  console.log('RoomDetailsThankYou.room: ',props.room)
  const ShowDetails = () => {
              return(
                <Row className="justify-content-center">
                  <span className="m-2"><FontAwesomeIcon icon={faBath} size="lg"/> Complete</span>
                  <span className="m-2"><FontAwesomeIcon icon={faTv} size="lg"/> Flat Screen (Satellite & Terrestrial)</span>
                  <span className="m-2"><FontAwesomeIcon icon={faWifi} size="lg"/> Free WiFi</span>
                  <span className="m-2"><FontAwesomeIcon icon={faSmokingBan} size="lg"/> No Smoking</span>
                </Row>
              )}

  return(
    <React.Fragment>
      <Card>
        <div >
          <CardImg top width="100%" src={require("assets/img/"+props.room.image)} alt="Card image cap" />
          <div className="ribbon ribbon-top-left"><span>{props.room.pricePerNight} €</span></div>
        </div>
        <CardBody>
          <CardTitle tag="h5">{props.room.roomtypesByFkRoomtypeId.name}<span className="float-right">{props.room.code}</span></CardTitle>
          <CardSubtitle tag="h6" className="mb-2 text-muted">Card subtitle</CardSubtitle>
          <CardText>{props.room.description}</CardText>
          {props.showdetails ? <ShowDetails/> : <div></div>}
        </CardBody>
      </Card>
    </React.Fragment>


  );
}

export default RoomDetailsThankYou;

