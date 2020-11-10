import React from "react";
import { Container, Row, Col, Button, Form, Input } from 'reactstrap';
import { Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle, CardFooter } from 'reactstrap';
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBath, faTv, faWifi, faSmokingBan } from '@fortawesome/free-solid-svg-icons'
// https://fontawesome.com/icons?d=gallery&m=free
import '../../assets/css/ribbon.css';

function RoomDetails(props) {
  //console.log('RoomDetails.props: ' ,props);

  const ShowDetails = () => {
              return(
                <React.Fragment>
                  <span className="m-2"><FontAwesomeIcon icon={faBath} size="lg"/> Complete</span>
                  <span className="m-2"><FontAwesomeIcon icon={faTv} size="lg"/> Satellite & Terrestrial</span>
                  <span className="m-2"><FontAwesomeIcon icon={faWifi} size="lg"/> Free WiFi</span>
                  <span className="m-2"><FontAwesomeIcon icon={faSmokingBan} size="lg"/> No Smoking</span>
                </React.Fragment>
              )}

  //const ButtonLink = props.buttonLink ? props.buttonLink : <div></div>

  return(
    <React.Fragment>
      <Row className="" style={{backgroundColor: 'white'}}>
          <Col md={6} >
            <div className="mt-auto mx-auto">
              <img src={require("assets/img/"+props.room.image)} alt={props.room.image} />
              <div className="ribbon ribbon-top-left"><span>{props.room.pricePerNight} €</span></div>
            </div>
          </Col>

          <Col md={6} > 
            <Row className="h-100">
              <Container >
                <CardTitle tag="h5">
                  <Row><Col>{props.room.roomtypesByFkRoomtypeId.name}</Col><Col className="text-right">{props.room.code}</Col></Row></CardTitle>
                <CardSubtitle tag="h6" className="mb-2 text-muted">{props.room.pricePerNight}€/night</CardSubtitle>
                <CardText>{props.room.description}</CardText>
                <CardText>Maximum {props.room.guests} guests</CardText>
              </Container>
              <div className="pull-right">{props.linkButton}</div>
              <div className="mt-auto mx-auto p-2 align-text-bottom">
                {props.showdetails ? <ShowDetails/> : <></>}
              </div>
            </Row>
          </Col>
      </Row>
    </React.Fragment>
  );
}



export function RoomDetailsOLD(props) {
  console.log('RoomDetailsOLD.props: ' ,props);
  console.log('props.rooms: ',typeof(props.rooms));
  const rooms = props.rooms.map( (room)=>
                    <Container key={room.id}>
                      <Row className="border border-danger mb-5">
                        <Col className="border border-success">
                          <img alt={room.image} src={require("assets/img/"+room.image)}></img>
                        </Col>

                        <Col>
                          <Row><Col><h5>{room.roomtypesByFkRoomtypeId.name}</h5></Col><Col className="text-right">{room.code}</Col></Row>
                          <p>{room.pricePerNight}€/night</p>
                          <p>{room.description}</p>
                          <p>Maximum {room.guests} guests</p>
                          <Row className="justify-content-center">
                            <Form  id={room.id} onSubmit={props.handelBookNow}>
                              <Input readOnly  type="hidden" name="roomid" value={room.id}></Input>
                              <Button type="submit" id={room.id} name={room.id} className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now!</Button>
                              <Link to={`/booknow/${room.id}`}>
                                <button type="button">
                                  Click Me!
                                </button>                    
                              </Link>
                            </Form>
                          </Row>
                        </Col>
                      </Row>
                    </Container>
                  );
  return (
    <React.Fragment>
        {rooms}
    </React.Fragment>
  );
}

export default RoomDetails;


/*

        <Row className="">
          <Col className="">
            <img alt={props.room.image} src={require("assets/img/"+props.room.image)}></img>
          </Col>

          <Col>
            <Row><Col><h5>{props.room.roomtypesByFkRoomtypeId.name}</h5></Col><Col className="text-right">{props.room.code}</Col></Row>
            <p>{props.room.pricePerNight}€/night</p>
            <p>{props.room.description}</p>
            <p>Maximum {props.room.guests} guests</p>
          </Col>
        </Row>
        {props.showdetails ? <ShowDetails/> : <div></div>}

*/