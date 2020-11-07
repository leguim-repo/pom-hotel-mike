import React from "react";
// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import FindRoomsExtend from "../../components/FindRoomsExtend/FindRoomsExtend";
import RoomDetails from "../../components/RoomDetails/RoomDetails";
import GotoTop from "../../components/GotoTop/GotoTop";
import Loading from "../../components/Loader/Loader";
import { getAllRooms } from "../../api/ApiServices"
import { Button, Container, Row, Col } from "reactstrap";
import { Link } from 'react-router-dom';
import "./RoomsPage.css"

import StorageManager from "../../components/StorageManager/StorageManager";


class RoomsPage extends React.Component {
  constructor(props) {
    super(props);
    console.log('RoomsPage.constructor: ',props);
    if (props.location.state !== undefined) {

      this.state = {
        rooms: props.location.state.rooms,
        excludeDates: props.location.state.excludeDates,
        checkin: props.location.state.checkin,
        checkout: props.location.state.checkout,
        guests: props.location.state.guests,
      };
    }
        
    this.handleBookNow = this.handleBookNow.bind(this);

  }

  async componentDidMount(){
    StorageManager.setupApplication();

    console.log("RoomsPage.componentDidMount")
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");

    const rooms = await getAllRooms();
    this.setState({rooms: rooms});
  }

  handleBookNow(event) {
    event.preventDefault();
    console.log('handleBookNow:', event.target.roomid.value);
    this.props.history.push('/booknowold?room='+event.target.roomid.value);
  }

  render() {
    console.log("Romspage.props: ", this.props,"\nRoomsPage.state: ",this.state);
    if (this.state === null) {
      return(<div><Loading></Loading></div>)
    }
    const RenderRooms = this.state.rooms.map( (e) => (
                            <Container fluid key={e.id} className="border border-dark mb-5">
                              <RoomDetails key={e.id} room={e}></RoomDetails>
                              <Row className="mb-2">
                                <Col></Col>
                                <Col className="text-center">
                                  <Link to= {{pathname: '/roomdetail/'+e.id, state: this.state}} onClick={() => {console.log('click button link')}} >
                                    <Button className="bg-warning mb-3 " style={{fontSize: '1.2em', padding: '0.5em'}}>Details & Book</Button>
                                  </Link>
                                </Col>
                              </Row>
                            </Container>
    ));

    console.log("RoomPage.state: ",this.state, "\nRoomPage.props: ",this.props);
    return (
      <React.Fragment>
      <PomNavbar />
      <div id="top" className="wrapper">
        <PomHeader image={require("assets/img/revato-10251-13112723-111323.jpg")} sloganBig="Find your rest" sloganLittle="in the paradise"/>
        <div className="main">
          <div className="container-fluid">     
            <Row>
              <Col>
                <h2 className="text-center H2Title">Our Rooms</h2>
              </Col>
            </Row>
            <Row >
              <Col md={9} className="border border-danger">
                  {RenderRooms}
              </Col>
              <Col md={3} className="border border-danger">
                <FindRoomsExtend parameters={this.state}></FindRoomsExtend>
              </Col>
            </Row>
          </div>
          <GotoTop></GotoTop>
        </div>
        <PomFooter />
      </div>
      </React.Fragment>
    );
  }
}
export default RoomsPage;
