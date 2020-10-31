import React from "react";
import { Container, Row, Col, Button, Form, Input } from "reactstrap";
import { Link } from 'react-router-dom';

function RoomDetails(props) {
  console.log('RoomDetailsOLD.props: ' ,props);
  console.log('props.rooms: ',typeof(props.rooms));
  
  return(
    <React.Fragment>
      <p>refacatorizacion en curso</p>
      <Container key={props.room.id}>
        <Row className="border border-danger mb-5">
          <Col className="border border-success">
            <img src={require("assets/img/"+props.room.image)}></img>
          </Col>

          <Col>
            <Row><Col><h5>{props.room.roomtypesByFkRoomtypeId.name}</h5></Col><Col className="text-right">{props.room.code}</Col></Row>
            <p>{props.room.pricePerNight}€/night</p>
            <p>{props.room.description}</p>
            <p>Maximum {props.room.guests} guests</p>
            <Row className="justify-content-center">
              <Form  id={props.room.id} onSubmit={props.handelBookNow}>
                <Input readOnly  type="hidden" name="roomid" value={props.room.id}></Input>
                <Button type="submit" id={props.room.id} name={props.room.id} className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now!</Button>
                <Link to={`/booknow/${props.room.id}`}>
                  <button type="button">
                    Click Me!
                  </button>                    
                </Link>

              </Form>
            </Row>
          </Col>
        </Row>
      </Container>
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
                          <img src={require("assets/img/"+room.image)}></img>
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
