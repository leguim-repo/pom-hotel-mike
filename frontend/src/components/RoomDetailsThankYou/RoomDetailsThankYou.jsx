import React, { useState }from "react";
import { Container, Row, Col, Button, Card, CardImg, CardText, CardBody, CardTitle, CardSubtitle } from "reactstrap";
import { Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faBath, faTv, faWifi, faSmokingBan } from '@fortawesome/free-solid-svg-icons'
import '../../assets/css/ribbon.css';


function RoomDetailsThankYou(props) {
  const [room, setRoom] = useState({});

  console.log('RoomDetailsThankYou.booking: ',props.booking)

  React.useEffect(() => {
    setRoom(props.booking.book);
    return function cleanup() {

    };
  }, [props]);
  const ShowDetails = () => {
              return(
                <Row className="justify-content-center">
                  <span className="m-2"><FontAwesomeIcon icon={faBath} size="lg"/> Complete</span>
                  <span className="m-2"><FontAwesomeIcon icon={faTv} size="lg"/> Flat Screen (Satellite & Terrestrial)</span>
                  <span className="m-2"><FontAwesomeIcon icon={faWifi} size="lg"/> Free WiFi</span>
                  <span className="m-2"><FontAwesomeIcon icon={faSmokingBan} size="lg"/> No Smoking</span>
                </Row>
              )}

  if ( 'roomsByFKRoomId' in room) {
    return(
      <React.Fragment>
        <Card>
          <div >
            <CardImg top width="100%" src={require("assets/img/"+room.roomsByFKRoomId.image)} alt="Card image cap" />
            <div className="ribbon ribbon-top-left"><span>{room.roomsByFKRoomId.pricePerNight} €</span></div>
          </div>
          <CardBody>
            <CardTitle tag="h5">{room.roomsByFKRoomId.roomtypesByFkRoomtypeId.name}<span className="float-right">{room.roomsByFKRoomId.code}</span></CardTitle>
            <CardSubtitle tag="h6" className="mb-2 text-muted">Card subtitle</CardSubtitle>
            <CardText>{room.roomsByFKRoomId.description}</CardText>
            {props.showdetails ? <ShowDetails/> : <div></div>}
          </CardBody>
        </Card>    </React.Fragment>
    );
  }
  else {
    return(<div>load</div>)
  }
}

export default RoomDetailsThankYou;

/*



*/

