/*eslint-disable*/
import React from "react";

// reactstrap components
import { Container } from "reactstrap";
// core components

function IndexHeader(props) {

  console.log('image: ',props.image);

  let pageHeader = React.createRef();

  React.useEffect(() => {
    if (window.innerWidth > 991) {
      const updateScroll = () => {
        let windowScrollTop = window.pageYOffset / 3;
        pageHeader.current.style.transform =
          "translate3d(0," + windowScrollTop + "px,0)";
      };
      window.addEventListener("scroll", updateScroll);
      return function cleanup() {
        window.removeEventListener("scroll", updateScroll);
      };
    }
  });

  return (
    <React.Fragment>
      <div className="page-header page-header-small clear-filter" filter-color="blue">
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

export default IndexHeader;
