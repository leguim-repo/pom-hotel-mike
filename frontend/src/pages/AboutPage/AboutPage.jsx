/* eslint-disable jsx-a11y/anchor-is-valid */
import React, { useState } from "react";
import { Col, Row, Container } from "reactstrap";

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";

import YouTube from "@u-wave/react-youtube";
import "./AboutPage.css";
const videos = [
  { id: "mL7Gb6rcAAo", name: "Felicità Italiano Ragazzi Dance Remix" },
  { id: "GJkuTx1DQzg", name: "Amelie Lens at Atomium in Brussels, Belgium for Cercle" },
  { id: "Y6A_Czw8TFU", name: "Amelie Lens lockdown session at home" },
  { id: "m30QEZG8aQA", name: "Carl Cox Techno DJ Set Live From The Off Sonar Closing Party Barcelona" },
  { id: "vy-k0FopsmY", name: "Carl Cox Boiler Room Ibiza Villa Takeovers DJ Set" },
  { id: "kwKrNtq9gHI", name: "Carl Cox Epic House Set From DJ Mag HQ Ibiza" },
  { id: "k1d1twnD7Tk", name: "Bob Sinclar live from Studio Ibiza" },
  { id: "P867JatlH18", name: "Bob Sinclar - Live from Bob's Studio (Heineken powered by Defected)" },
  { id: "yj2bHkkBS-Q", name: "Gabry Ponte - Live Felicità (di Albano & Romina Power) - (Full HD) - 31.08.2020 - Battiti live" },
  { id: "A8gdkWOsc6Q", name: "Gabry Ponte - Medley Live - Battiti Live - estate 2020 - 31.08.2020" },
];

function AboutPage(props) {
  const [video, setVideo] = useState("mL7Gb6rcAAo");

  function handleClick(e) {
    //e.preventDefault();
    setVideo(e);
  }

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
  }, [props]);

  console.log("AboutPage: ", props);
  console.log("videos: ", videos);

  return (
    <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
        <PomHeader thankyoupage image={require("assets/img/img_bg_1.jpg")} sloganBig="About Page" sloganLittle="Z-Devs Team"/>
        <div className="main" style={{backgroundImage: "url(" + require("assets/img/pattern.png") + ")",}}>
          <Container fluid className=""> 
            <Row>
              <Col>
                <h2 className="text-center H2Title">Our Dance Sessions</h2>
              </Col>
            </Row>
            <Row className="mb-2 justify-content-center ">
              <Col md={4} className="justify-content-center ">
                <Container className="bg-blank">
                  <div className="collection">
                    {videos.map((choice, index) => (
                      <Container fluid key={index}>
                        <a  href="javascript:void(0)"
                            key={choice.id}
                            onClick={() => handleClick(choice.id)}
                            className={`collection-item ${video === choice.id ? "active" : ""}`}>
                            {choice.name}
                        </a>
                      </Container>
                    ))}
                  </div>
                </Container>
              </Col>
              <Col md={6} className="">
                <YouTube
                  video={video}
                  width={640}
                  height={480}
                  controls={true}
                  modestBranding
                />
              </Col>
            </Row>
          </Container>
        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}
export default AboutPage;
