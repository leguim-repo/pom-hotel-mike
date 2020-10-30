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

        <div className="content">
          <Container className="border border-danger" >

            <Form style={{color:'black',backgroundColor: '#f1f1f1', opacity:'0.95'}}>

              <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                <Label for="username" className="mr-sm-2">Username</Label><br/>
                <Input type="username" name="text" id="username" placeholder="something@idk.cool" />
              </FormGroup>
              
              <FormGroup className="mb-2 mr-sm-2 mb-sm-0">
                <Label for="examplePassword" className="mr-sm-2">Password</Label>
                <Input type="password" name="password" id="examplePassword" placeholder="don't tell!" />
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
