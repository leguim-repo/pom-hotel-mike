import React from "react";
import Spaces from "../../components/Spaces/Spaces";

//TODO for better performance should be a functional component

class PopularSpaces extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <>
        <div className="container mt-5">

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
            <Spaces img={require("assets/img/high-performance.jpg")}
                    title="Underwater Special Rooms"
                    description="Cooming soon..">        
            </Spaces>
          </div>
        </div>
      </>
    );
  }
}
export default PopularSpaces;
