import React from "react";
import axios from "axios";

// reactstrap components
import { Button, Card, CardHeader, CardBody, CardFooter, Form, Input, Row, FormGroup, InputGroupAddon, InputGroupText, InputGroup, Container, Col, Label} from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";



function BookNowPage() {


  React.useEffect(() => {
    //document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;



    return function cleanup() {
      //document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
    };
  }, []);

// https://medium.com/dataseries/reactstrap-forms-a6c70b95f453

  return (
    <>
      <PomNavbar />
      <div className="page-header clear-filter" filter-color="">
        <div
          className="page-header-image"
          style={{
            backgroundImage: "url(" + require("assets/img/high-performance.jpg") + ")",
          }}
        ></div>

        <div className="content " style={{marginTop: '5%'}}>
          <Container className="border border-danger" >
            <Form clasName="border border-secondary" style={{color:'black',backgroundColor: '#f1f1f1', opacity:'0.95',paddingTop: '0px',paddingLeft: '20px',paddingRight: '20px'}}>
            <h4>Confirm your reserve</h4>
              <FormGroup row>
                <Label for="username" md={2}>Username</Label>
                <Col md={3}>
                  <Input type="text" name="username" id="username" placeholder="Username"></Input>
                </Col>

                <Label for="password" sm={2}>Password</Label>
                <Col sm={4}>
                  <Input type="password" name="password" id="password" placeholder="password placeholder"></Input>
                </Col>
              </FormGroup>
              <hr></hr>

              <FormGroup row>
                <Col className="text-left">
                  <Label>Room Code</Label>
                  <Input value="SU1"></Input>
                </Col>
                <Col className="text-left">
                  <Label>Room Type:</Label>
                  <Input value="SU1"></Input>
                </Col>
                <Col className="text-left">
                  <Label>Guests</Label>
                  <Input value="SU1"></Input>
                </Col>
                <Col className="text-left">
                  <Label>Price per night</Label>
                  <Input value="SU1"></Input>
                </Col>
              </FormGroup>

              <FormGroup row>
                <Col>
                  <img src={require("assets/img/"+"camere-9.jpg")}></img>
                </Col>
                <Col className="text-left">
                  <h6>Description</h6>
                  <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Error, eaque. Tempore velit reiciendis eligendi fugit ut nisi. Accusantium animi quos atque veniam nihil minus, nam tempora, obcaecati, ipsam odio officiis ad enim illum? Officia beatae culpa tenetur id sint praesentium nihil illo, sapiente tempore consectetur facilis fuga nostrum harum distinctio.</p>
                </Col>
              </FormGroup>

              <FormGroup row>
                <Col>
                  <Input value="29-10-2020"></Input>
                </Col>
                <Col>
                  <Input value="29-10-2020"></Input>
                </Col>
                <Col>Total Price</Col>
              </FormGroup>
              <Button>Submit</Button>

            </Form>
          </Container>

        </div>

      </div>
    </>
  );
}

export default BookNowPage;
