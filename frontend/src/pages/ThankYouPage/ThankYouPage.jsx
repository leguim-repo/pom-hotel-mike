import React from "react";

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";

// sections for this page
import PopularSpaces from "../Sections/PopularSpaces";
import GuestsOpinions from "../Sections/GuestsOpinions";
import FindRoomsSimple from "../../components/FindRoomsSimple/FindRoomsSimple";


function ThankYouPage(props) {
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
            <p>datos de la reservar y gracias</p>

        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default ThankYouPage;
