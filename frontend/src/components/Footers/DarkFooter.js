/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container } from "reactstrap";

function DarkFooter() {
  return (
    <footer className="footer" data-background-color="black">
      <Container>
        <div className="row">
          <div className="col-12 col-md6 col-lg-4">
            <h4>Pom Dreams</h4>
            <p>
              Lorem, ipsum dolor sit amet consectetur adipisicing elit. Quasi
              voluptatem quisquam, labore nesciunt modi officiis alias
              architecto blanditiis, accusamus, aut eius?
            </p>
            <p>
              Dicta necessitatibus neque perferendis aperiam iste sint cum dolor
              fugiat nisi temporibus aut!
            </p>
          </div>

          <div className="col-12 col-md6 col-lg-4">
            <h4 className="text-center">Play Music</h4>
            <p className="text-center">Listen the best dance sessions</p>
            <p className="text-center">
              <a href="#">
                <img
                  src={require("assets/img/musica.png")}
                  style={{ height: "3.5em", width: "3.5em" }}
                  target="_blank"
                ></img>
              </a>
            </p>
            <p className="text-center">Click on the icon and enjoy</p>
          </div>

          <div className="col-12 col-md6 col-lg-4">
            <h4>Contact Information</h4>
            <address>
              Aulas Formacion B-3,<br/>
              Carretera Nacional II (Seat Complex A-2 Highway)<br/>
              KM 585 , Martorell , 08760 , Barcelona<br/>
              <i className="fa fa-phone"></i>: +034 931 234 567<br/>
              <i className="fa fa-fax"></i>: +034 931 234 567<br/>
              <i className="fa fa-envelope"></i>:&nbsp;
              <a href="mailto:pomhotel@pomhotel.dot">pomhotel@pomhotel.dot</a>
            </address>
          </div>
        </div>

        <div className="text-center">
          Copyright © {new Date().getFullYear()}, All rights reserved to @Z-Devs
          <br />
          Backend by: @Pablo, @Oscar, @Miguel
          <br />
          Fronted by: @Miguel
        </div>
      </Container>
    </footer>
  );
}

export default DarkFooter;
