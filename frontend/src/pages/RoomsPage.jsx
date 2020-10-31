import React from "react";
// core components
import PomNavbar from "../components/Navbars/PomNavbar";
import PomHeader from "../components/Headers/PomHeader";
import PomFooter from "../components/Footers/PomFooter";
import FindRoomsExtend from "../components/FindRoomsExtend";
import RoomDetails from "../components/RoomDetails";
import GotoTop from "../components/GotoTop";
import { getAllRooms } from "../api/ApiServices"
import "./RoomsPage.css"
import { Container, Row, Col, Button, Form, Input } from "reactstrap";
import { Link } from 'react-router-dom';



class RoomsPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {rooms: []};

    console.log('RoomsPage: ',props);
    this.handleBookNow = this.handleBookNow.bind(this);

  }

  async componentDidMount(){
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
    const RenderRooms = this.state.rooms.map( (e) => (
                            <Container className="border border-dark mb-5">
                              <RoomDetails key={e.id} room={e}></RoomDetails>
                              <Row className="mb-2">
                                <Col></Col>
                                <Col className="text-center">
                                <Link to= {{
                                  pathname: '/booknow',
                                  state: {
                                    room: e
                                  }
                                }}><button type="button">Book Now</button></Link>
                                  </Col>
                              </Row>
                            </Container>
    ));

    return (
      <React.Fragment>
      <PomNavbar />
      <div id="top" className="wrapper">
        <PomHeader image={require("assets/img/revato-10251-13112723-111323.jpg")} sloganBig="Find your rest" sloganLittle="in the paradise"/>
        <div className="main">
          <div className="container-fluid">
            <div className="row" style={{margin: '100px'}}>
              <div className="col-9">
                <div className="title divRoomTitle">
                  <h2 style={{margin: '0px'}}>Our Rooms</h2>
                </div>
                <Container>
                  {RenderRooms}
                </Container>
                {/*<RoomDetailsOLD rooms={this.state.rooms} handelBookNow={this.handleBookNow}></RoomDetailsOLD>*/}
              </div>
              <div className="col-3">
                <FindRoomsExtend></FindRoomsExtend>
              </div>
            </div>
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
