import React, { useEffect, useCallback, useState} from "react";
import axios from "axios";
import api from "../api/apiEndPoints.json";
import { getRoomById } from "../api/ApiServices"
import { Container, Row, Col, Button, Form } from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";
import PomHeader from "components/Headers/PomHeader";
import PomFooter from "components/Footers/PomFooter";

import "./RoomsPage.css"

import RoomDetails from "components/RoomDetails";

import { useParams } from "react-router-dom";
import FindRoomsExtend from "components/FindRoomsExtend";

function RenderRoomDetails(list) {
  console.log('t: ',typeof(list.list[0]))
  console.log('RenderRoomDetails.rooms: ',list);
  //console.log('RenderRoomDetails.rooms: ',list.list, typeof(list.list));


  
  const persons = ['Carey Mulligan', 'Ana de Armas', 'Katherine Langford', 'Margot Robbie', 'Kiernan Shipka'];
  const listItems = persons.map((person) =>
    <li key={person}>{person}</li>
  );

  console.log(listItems);

  //console.log(Object.entries(list.list))
  //console.log(persons,typeof(persons))
  //console.log(listItems,typeof(listItems))

  return listItems;
  /*
  list.map ( (room)=>{
    console.log(room);
    return(<p>{room.id}</p>);
  });
  */
}


function Rooms(props) {

  const handleSubmit = (evt) => {
    evt.preventDefault();
    console.log('submit: ',roomBookNow);
}


  //https://midu.dev/urlsearchparams-como-leer-la-query-string/
  const querystring = useParams().params;
  
  const params = new URLSearchParams(querystring)
  

  
  //hooks
  const [rooms,setRooms] = useState('');
  const [scrollPos, setScrollPos] = useState('');
  const [roomBookNow, setRoomBookNow] = useState('');
  const [showGotoTop, setShowGotoTop] = useState(false);
  
  const handleOnScroll = useCallback(event => {
      //console.log("handleOnScroll.event: ",event);
      setScrollPos(event.target.documentElement.scrollTop);
      if (event.target.documentElement.scrollTop >= 300) {
        setShowGotoTop(true);
      }
      else {
        setShowGotoTop(false);
      }
  }, []);

  var divRoomTitle = {
    background: "#f7f7f7",
    border: "2px solid #f0f0f0",
    margin: "0px 0px 30px",
    padding: '10px 10px 10px 10px'
  };

  useEffect(() => {
    console.log('rooms.props: ',props);

    if (props.match.isExact) {
      console.log("parametro de la habitancio: ",props.match.params.params)
    }



    async function fetchAllRooms() {
      const response = await axios(api.getAllRooms);
      const data = await response.data;
      //const json = JSON.stringify(data);
      //console.log('json: ',typeof(json));
      //const json = await response.data.json();
      //console.log('response: ',response);
      console.log('data: ',data);
      //console.log(JSON.stringify(response.data));
      //var myJSON = JSON.stringify(json);
      //console.log('json: ',json);
      //console.log('myJSON: ',myJSON);

      const x = data.map( (room)=>
              <Container key={room.id}>
                <Row className="border border-danger mb-5">
                  <Col>
                    <img src={require("assets/img/"+room.image)}></img>
                  </Col>
                  <Col>
                    <Row><Col><h5>{room.roomtypesByFkRoomtypeId.name}</h5></Col><Col className="text-right">{room.code}</Col></Row>
                    <p>{room.pricePerNight}€/night</p>
                    <p>{room.description}</p>
                    <p>Maximum {room.guests} guests</p>
                    <Row className="justify-content-center">
                      <Form onSubmit={handleSubmit}>
                        <Button type="submit" onClick={ () => setRoomBookNow(room)} className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now!</Button>
                      </Form>
                    </Row>
                  </Col>
                </Row>
              </Container>
        )



      setRooms(x);
    }

    getRoomById(1);
    //console.log('params: ',params)
    if (querystring === undefined) {
      fetchAllRooms();

    } else{
      console.log('busqueda personalizada contra la api');
      fetchAllRooms();

    }


    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;

    window.addEventListener("scroll", handleOnScroll);

    
    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");
      
      window.removeEventListener('scroll', handleOnScroll);
    };
  }, [handleOnScroll]);
  


  
  // TODO pendiente ordenar bien las columnas dependiendo del dipositivo
  //const listOfRooms = RenderRoomDetails(rooms);



  return (
    <React.Fragment>
      <PomNavbar />
      <div id="top" className="wrapper">
        <PomHeader image={require("assets/img/revato-10251-13112723-111323.jpg")} sloganBig="Find your rest" sloganLittle="in the paradise"/>
        <div className="main">
          <div className="container-fluid border border-success">
            <div className="row border border-danger" style={{margin: '50px'}}>
              <div className="col-9">
                <div className="title" style={divRoomTitle}>
                  <h2 style={{margin: '0px'}}>Our Rooms</h2>
                </div>
                {rooms}
              </div>
              <div className="col-3">
                <FindRoomsExtend></FindRoomsExtend>
              </div>
            </div>
          </div>

        {/*pa arriba*/}
        <div className={`text-center gototop ${showGotoTop ? 'active' : ''}`}>
          <a href="#top">
            {scrollPos}
            {/*<i className="now-ui-icons arrows-1_minimal-up flecha"/>  +{showGotoTop ? "active" : ""}>  */}
          </a>
        </div>

        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default Rooms;
