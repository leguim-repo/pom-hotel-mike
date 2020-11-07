import React, {useState} from "react";

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";
import { apiGetBookingById } from "api/ApiServices";
// sections for this page



function ThankYouPage(props) {
  const [booking, setBooking] = useState({});
 
  React.useEffect(() => {
    apiGetBookingById(props.match.params.booking).then((data) => setBooking(data));

    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;
    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, [props]);

  console.log('ThankYouPage.booking: ',booking);
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
