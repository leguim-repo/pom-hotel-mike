import React from "react";
// core components
import PomNavbar from "../components/Navbars/PomNavbar";
import PomHeader from "../components/Headers/PomHeader";
import PomFooter from "../components/Footers/PomFooter";
import "./RoomsPage.css"
import FindRoomsExtend from "../components/FindRoomsExtend";
import RoomDetails from "../components/RoomDetails";

import GotoTop from "../components/GotoTop";

import { getAllRooms } from "../api/ApiServices"

class RoomsPage extends React.Component {
  constructor(props) {
    super(props);
    this.state = {rooms: []};

    console.log('RoomsPage: ',props);
  }


  async componentDidMount(){
    const rooms = await getAllRooms();
    console.log('rooms: ',rooms)
    this.setState({rooms: rooms});
  }

  render() {
    return (
      <React.Fragment>
      <PomNavbar />
      <div id="top" className="wrapper">
        <PomHeader image={require("assets/img/revato-10251-13112723-111323.jpg")} sloganBig="Find your rest" sloganLittle="in the paradise"/>
        <div className="main">
          <div className="container-fluid border border-success">
            <div className="row border border-danger" style={{margin: '50px'}}>
              <div className="col-9">
                <div className="title" style={this.props.divRoomTitle}>
                  <h2 style={{margin: '0px'}}>Our Rooms</h2>
                </div>
                <RoomDetails rooms={this.state.rooms}></RoomDetails>
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
