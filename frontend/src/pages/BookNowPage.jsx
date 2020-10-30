import React, { useState }from "react";
import { useLocation } from "react-router-dom";
// reactstrap components
// import {
// } from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";
import PomHeader from "components/Headers/PomHeader";
import PomFooter from "components/Footers/PomFooter";

import RoomDetails from "components/RoomDetails";
import { getRoomById } from "../api/ApiServices"

import DatePicker from 'react-datepicker';


function useQuery() {
  return new URLSearchParams(useLocation().search);
}
function BookNowPage(props) {
  const [room, setRoom] = useState([]);

  let query = useQuery();

  React.useEffect(() => {
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;


    console.log('BookNowPage.props: ',props)
    const roomId = query.get("room");
    const targetRoom = async() => await getRoomById(roomId);
    
    console.log('targetRoom: ',roomId, ' ', targetRoom,' ', typeof(targetRoom))
    setRoom(targetRoom);
    

    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, []);

  console.log('room: ',room);
  return (
    <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader image={require("assets/img/high-performance.jpg")} sloganBig="POM HOTEL & SPA" sloganLittle="By Z-Devs Team"/>
        <div className="main">
          <p>booknow here</p>
        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default BookNowPage;
