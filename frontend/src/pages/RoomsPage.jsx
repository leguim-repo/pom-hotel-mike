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
                            <Container key={e.id} className="border border-dark mb-5">
                              <RoomDetails key={e.id} room={e}></RoomDetails>
                              <Row className="mb-2">
                                <Col></Col>
                                <Col className="text-center">
                                  <Link to= {{
                                    pathname: '/booknow',
                                    state: {
                                      room: e
                                    }
                                    }}>
                                    <button className="mb-3" type="button">Book Now</button>
                                  </Link>
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

            <div className="row">
              <div className="col">
                <h2 className="text-center H2Title">Our Rooms</h2>
              </div>
            </div>

            <div className="row" >
              <div className="col-9">
                <Container>
                  {RenderRooms}
                </Container>
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
