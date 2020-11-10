import React from 'react';
import { Col, Row, Container } from 'reactstrap';

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import Loader from "../../components/Loader/Loader";

const BookingNotFound = () => {
    return (
        <React.Fragment>
        <PomNavbar />
        <div className="wrapper">
          <PomHeader image={require("assets/img/fsm.jpg")}  sloganBig="Sorry, booking not found" sloganLittle="Please contact with us in order to solution the problem"/>
          <div className="main" style={{ backgroundImage: "url(" + require("assets/img/pattern.png") + ")",}}>
          </div>
          <PomFooter />
        </div>
        </React.Fragment>
      );
}

export default BookingNotFound;