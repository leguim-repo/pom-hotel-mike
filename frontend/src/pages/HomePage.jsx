import React from "react";

// reactstrap components
// import {
// } from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";
import PomHeader from "components/Headers/PomHeader";
import PomFooter from "components/Footers/PomFooter";

// sections for this page
import PopularSpaces from "./sections/PopularSpaces";
import GuestsOpinions from "./sections/GuestsOpinions";
import FindRoomsSimple from "components/FindRoomsSimple";


function Index(props) {
  React.useEffect(() => {
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;
    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  });

  return (
    <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader image={require("assets/img/chica_piscina.jpg")} sloganBig="POM HOTEL & SPA" sloganLittle="By Z-Devs Team"/>
        <div className="main">
        <FindRoomsSimple {...props} ></FindRoomsSimple>
          <PopularSpaces></PopularSpaces>
          <GuestsOpinions></GuestsOpinions>
        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default Index;
