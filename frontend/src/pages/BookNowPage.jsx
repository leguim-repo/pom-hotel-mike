import React from "react";
import axios from "axios";

// reactstrap components
import {
  Button,
  Card,
  CardHeader,
  CardBody,
  CardFooter,
  Form,
  Input,
  InputGroupAddon,
  InputGroupText,
  InputGroup,
  Container,
  Col,
} from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";



function BookNowPage() {
  const [firstFocus, setFirstFocus] = React.useState(false);
  const [lastFocus, setLastFocus] = React.useState(false);
  React.useEffect(() => {
    document.body.classList.add("login-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;



    return function cleanup() {
      document.body.classList.remove("login-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, []);


  return (
    <>
      <PomNavbar />
      <div className="page-header clear-filter" filter-color="blue">
        <div
          className="page-header-image"
          style={{
            backgroundImage: "url(" + require("assets/img/high-performance.jpg") + ")",
          }}
        ></div>
        <div className="content">
        <Container>
            <Col className="ml-auto mr-auto" md="4">
              <Card className="card-login card-plain border border-success">
                <Form action="" className="form" method="">
                  <CardHeader className="text-center">
                    <h1>Confirm your booking</h1>
                  </CardHeader>
                  <CardBody>

                  </CardBody>
                  <CardFooter className="text-center">
                    <Button
                      block
                      className="btn-round"
                      color="info"
                      href="#pablo"
                      onClick={(e) => {e.preventDefault()}}
                      size="lg"
                    >
                      Confirm Book
                    </Button>

                  </CardFooter>
                </Form>
              </Card>
            </Col>
          </Container>
        </div>

      </div>
    </>
  );
}

export default BookNowPage;
