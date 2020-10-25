import React, { useEffect, useCallback, useState} from "react";
import axios from "axios";
import api from "../api/apiEndPoints.json";
import { Container, Row, Col, Button, Form } from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";
import PomHeader from "components/Headers/PomHeader";
import PomFooter from "components/Footers/PomFooter";

import "./RoomsPage.css"

import RoomDetails from "components/RoomDetails";

import { useParams } from "react-router-dom";

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


function Rooms() {
  //https://midu.dev/urlsearchparams-como-leer-la-query-string/
  const querystring = useParams().params;
  
  const params = new URLSearchParams(querystring)
  
  //recovery params for make a request to server
  //console.log('querystring: ',querystring);
  //console.log('params: ',params);
  //console.log('checkin: ',params.get('checkin'));
  //console.log('checkout: ',params.get('checkout'));
  //console.log('guests: ',params.get('guests'));
  
  //hooks
  const [rooms,setRooms] = useState('');
  const [scrollPos, setScrollPos] = useState('');
  
  const handleOnScroll = useCallback(event => {
      //console.log("handleOnScroll.event: ",event);
      setScrollPos(event.target.documentElement.scrollTop);
  }, []);

  var divRoomTitle = {
    background: "#f7f7f7",
    border: "2px solid #f0f0f0",
    margin: "0px 0px 30px",
    padding: '10px 10px 10px 10px'
  };

  useEffect(() => {

    async function fetchAllRooms() {
      const response = await axios.get(api.getAllRooms);
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
                      <Form>
                        <Button type="submit" className="bg-warning" style={{fontSize: '1.2em', padding: '0.5em'}}>Book Now!</Button>
                      </Form>
                    </Row>
                  </Col>
                </Row>
              </Container>
        )

      setRooms(x);
    }

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

    window.addEventListener('scroll', handleOnScroll);

    
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
          <Container fluid className="border border-info">
            <Row style={{margin: '50px'}}>

              <Col md="9" className="order-1"> 
                <div className="title" style={divRoomTitle}>
                  <h2 style={{margin: '0px'}}>Our Rooms</h2>
                </div>

                {rooms}
              
              </Col>

              <Col md="3" className="order-2 bg-piscina">
                <h2>Find Your Room</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quidem ratione molestiae itaque consequuntur id eius obcaecati? Ab ipsum, voluptatum consectetur natus consequuntur minima quia alias nostrum tempore, at quisquam ducimus perferendis quas voluptatibus provident possimus excepturi. Consequatur, cum doloribus, autem adipisci laboriosam optio distinctio numquam, a suscipit molestias labore nam est ducimus dolorem. Laudantium voluptatum ea aspernatur. Ipsa commodi cumque inventore quibusdam dicta repellat id iste velit architecto dolores? Pariatur rerum possimus asperiores laborum expedita. Porro quibusdam nobis sint architecto non voluptatum eius provident facere ipsa exercitationem consectetur in, consequuntur id ducimus molestiae, alias, aliquid nulla facilis nisi quidem error distinctio sunt odit. Pariatur rem velit inventore, eveniet odit voluptatibus provident deleniti quis, amet, incidunt aspernatur? Esse quod, odio adipisci quam earum dolores nihil illum quisquam deleniti? Ipsum ipsam, distinctio ad, sequi rerum sit blanditiis voluptatibus officiis placeat, labore nemo reiciendis consectetur dolore. Itaque voluptas perspiciatis dolore eius cumque temporibus!</p>
                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatibus alias, ea expedita eveniet saepe velit deleniti quo doloremque neque cum eligendi odit earum iste! Assumenda, dolore fugit ratione cum dolor, odit unde, reprehenderit amet voluptas a corrupti tempore praesentium repellendus? Maiores eveniet harum totam explicabo velit voluptas officiis fugit sapiente reiciendis debitis esse nobis eligendi natus laudantium corrupti, illum, ipsa dignissimos eaque eos, saepe ratione qui. Similique, ratione tempora iure maxime nemo voluptate earum rem est reiciendis natus laudantium beatae dolor. Quas velit repudiandae, soluta tempora in perspiciatis illum. Laborum quia totam a atque, iste est doloribus sapiente earum quasi id, veniam dolore, dolores repudiandae. Distinctio pariatur fugit nemo, sequi facere placeat minus nobis. Odit saepe ad itaque provident ullam, expedita adipisci sunt est aliquam, laudantium exercitationem quod velit commodi tempore. Nulla veniam similique velit adipisci itaque eum. Labore ab, eius sunt quibusdam sit laboriosam? Exercitationem repellat necessitatibus veritatis beatae incidunt, sit qui vero inventore consectetur architecto praesentium enim nam voluptates ullam voluptas, quo aut aperiam ducimus, similique autem quae. Perferendis minus nesciunt optio tenetur nobis odit cupiditate necessitatibus fugiat nostrum aperiam error iste porro obcaecati, veritatis aut impedit. Quo illum cum, qui reiciendis autem et dolores hic quibusdam quaerat, velit enim harum iste consectetur totam sed! Est, eligendi? Modi ratione, asperiores quibusdam optio, accusamus deserunt autem velit cum architecto officia animi explicabo maxime odio vero placeat error veniam. A eius exercitationem eveniet ad molestias reprehenderit facere eum repellat ab, deserunt veniam nam, distinctio explicabo ducimus tempora libero asperiores autem officiis reiciendis? Fuga cum impedit itaque. Expedita, ea. Libero aut, doloremque vel, quam quod mollitia impedit ipsa nisi ipsam natus, eveniet quisquam molestias assumenda facere ab earum reiciendis ut quas dolores modi magnam voluptate in incidunt. Alias illum odit numquam soluta. Sit perferendis distinctio sunt pariatur, molestias quam ipsa aliquam! Facilis, inventore molestiae ab numquam ipsum rem omnis, atque odit expedita animi amet, ipsam labore sed voluptatibus! Ratione tempore ex corrupti rerum repellat exercitationem maxime officiis. Ducimus vero at, dolorum doloremque ea non! Quae ex laudantium deserunt. Facilis dignissimos delectus impedit veritatis error, labore inventore modi et voluptas rerum minima dolor voluptatum quibusdam possimus nisi id. Praesentium dolores dolorum culpa temporibus qui esse totam, excepturi explicabo dolorem reprehenderit atque! Nesciunt, quos dicta eum labore velit, ipsa dolores cumque officiis facere minus maxime reiciendis blanditiis quibusdam corporis debitis. Non ex laboriosam, dolore officiis provident, suscipit accusantium, velit eaque dolorem unde a.</p>
              </Col>
            </Row>
          </Container>

        {/*pa arriba*/}
        <div className="text-center gototop active">
          <a href="#top">
            {scrollPos}
            {/*<i className="now-ui-icons arrows-1_minimal-up flecha"/>*/}
          </a>
        </div>

        </div>
        <PomFooter />
      </div>
    </React.Fragment>
  );
}

export default Rooms;
