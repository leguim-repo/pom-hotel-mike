/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container } from "reactstrap";
// core components

function PomHeader(props) {

  //console.log('pom-header: ',props)
  //console.log('image: ',props.image);

  let pageHeader = React.createRef();

  if (props.thankyoupage) {
    return(
      <React.Fragment>
        {/*<div className="page-header page-header-small clear-filter" filter-color="blue">*/}
        <div className="page-header " filter-color="">
          <div className="page-header-image" style={{ backgroundImage: "url(" + props.image + ")",}} ref={pageHeader}></div>
          <Container fluid className="">
            <div className="content-center brand">
              <h1 className="h2-seo" style={{color: 'white'}}>{props.sloganBig}</h1>
              <h2 className="h2-seo" style={{color: 'white'}}>{props.sloganLittle}</h2>
            </div>
          </Container>
        </div>
      </React.Fragment>
    );
  }
  else {
    return(
            <React.Fragment>
              {/*<div className="page-header page-header-small clear-filter" filter-color="blue">*/}
              <div className="page-header page-header-small " filter-color="blue">
                <div className="page-header-image" style={{ backgroundImage: "url(" + props.image + ")",}} ref={pageHeader}></div>
                <Container>
                  <div className="content-center brand">
                    <h1 className="h1-seo">{props.sloganBig}</h1>
                    <h3>{props.sloganLittle}</h3>
                  </div>
                </Container>
              </div>
            </React.Fragment>
    );  
  }
}

export default PomHeader;
