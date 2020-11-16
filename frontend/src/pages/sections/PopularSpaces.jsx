import React from "react";
import { Button, Col } from "reactstrap";
import Spaces from "../../components/Spaces/Spaces";
import { Link } from 'react-router-dom';

import{
  Card,
  CardImg,
  CardBody,
  CardTitle,
  CardText,
} from "reactstrap";

class PopularSpaces extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <React.Fragment>
        <div className="container mt-2">

            <h2 className="text-center">Popular Spaces</h2>
            <p>We love to tell our successful, behind the roof of our hotel, far from the current world, here live the blind texts.</p>
        </div>

        <div className="container-fluid">
          <div className="row  justify-content-center ">
            <Spaces img={require("assets/img/tour-1.jpg")}
                    title="Mediterranean Views"
                    description="Enjoy these amazing views">        
            </Spaces>
            <Spaces img={require("assets/img/camere-4.jpg")}
                    title="Luxury Rooms"
                    description="Enjoy your sensations">        
            </Spaces>
            <Spaces img={require("assets/img/ok10.jpg")}
                    title="SPA"
                    description="Enjoy of our SPA">        
            </Spaces>
            <Card className="text-center" style={{ margin: "2px",width: "18rem" }}>
              <CardBody className="p-5">
                <h3>Book Now</h3>
                <h5>Best Price OnLine</h5>
                <Link to= {{pathname: '/rooms', state: this.state}}>
                    <Button className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Find Rooms</Button>
                </Link>  
              </CardBody>
            </Card>
          </div>
        </div>
      </React.Fragment>
    );
  }
}
export default PopularSpaces;
