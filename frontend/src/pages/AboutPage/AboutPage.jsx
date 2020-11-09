import React, { useState } from "react";
import { Col, Row, Container } from 'reactstrap';

// core components
import PomNavbar from "../../components/Navbar/PomNavbar";
import PomHeader from "../../components/Header/PomHeader";
import PomFooter from "../../components/Footer/PomFooter";

import YouTube from '@u-wave/react-youtube';

const videos = [
  { id: 'mL7Gb6rcAAo', name: 'Felicità Italiano Ragazzi Dance Remix' },
  { id: '4ollgP4iCm0', name: 'Amelie Lens all night long @ Labyrinth Club 2017 (8 hours session)' },
  { id: 'ld8ugY47cps', name: 'SLCHLD - I can\'t love you anymore' },
  { id: null, name: '<none>' },
];

const qualities = ['auto', '240', '380', '480', '720', '1080', '1440', '2160'];

const hashVideoRx = /^#!\/video\/(\d)$/;
const hash = typeof window.location !== 'undefined'
  ? window.location.hash : ''; // eslint-disable-line no-undef



function AboutPage(props) {
  const [videoIndex, setVideoIndex] = useState(1);
  const [video, setVideo] = useState(videos[videoIndex]);
  const [volume, setVolume] = useState(1);
  const [paused, setPaused] = useState(false);
  const [quality, setQuality] = useState('auto');



  function handleClick(e) {
    e.preventDefault();
    console.log('The link was clicked.');
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


  console.log('AboutPage: ',props);
  console.log('videos: ',videos);
  console.log('videoIndex: ', videoIndex);

  return (
      <React.Fragment>
      <PomNavbar />
      <div className="wrapper">
      <PomHeader image={require("assets/img/img_bg_1.jpg")} sloganBig="About Page" sloganLittle="Z-Devs"/>
      <div className="main" style={{ backgroundImage: "url(" + require("assets/img/pattern.png") + ")",}}>
          <Container fluid>
          <h1>Pom Hotel Seesions</h1>
          <Container>

          <Row>
            <Col className="border border-dark">
              <div className="collection">
                <ul>
                  {videos.map((choice, index) => (
                    <li>

                      <span onClick={handleClick}>{choice.name}</span>

                      <a  href={`#!/video/${index}`}
                          key={choice.id}
                          className={`collection-item ${video === choice ? 'active' : ''}`}
                          onClick={() => setVideoIndex(index)}
                          >
                          {choice.name}
                      </a>
                    </li>
                ))}
                </ul>

            </div>
            </Col>

            <Col className="border border-dark">
                <YouTube
                video='mL7Gb6rcAAo'
                autoplay
                width={640}
                height={480}
                controls={false}
              />
            </Col>
          </Row>
          </Container>

          </Container>
      </div>
      <PomFooter />
      </div>
      </React.Fragment>
  );
}
export default AboutPage;

