import React, { useEffect, useCallback, useState} from "react";

// reactstrap components
// import {
// } from "reactstrap";

// core components
import PomNavbar from "components/Navbars/PomNavbar";
import PomHeader from "components/Headers/PomHeader";
import PomFooter from "components/Footers/PomFooter";

import "./RoomsPage.css"


function Rooms() {

  const [userText, setUserText] = useState('');

  const handleOnScroll = useCallback(event => {
      console.log("handleOnScroll.event: ",event);
      setUserText(event.target.documentElement.scrollTop);
  }, []);


  useEffect(() => {
    document.body.classList.add("index-page");
    document.body.classList.add("sidebar-collapse");
    document.documentElement.classList.remove("nav-open");
    window.scrollTo(0, 0);
    document.body.scrollTop = 0;

    window.addEventListener('scroll', handleOnScroll);

    return function cleanup() {
      document.body.classList.remove("index-page");
      document.body.classList.remove("sidebar-collapse");

      window.removeEventListener('keydown', handleOnScroll);
    };
  }, [handleOnScroll]);

  return (
    <React.Fragment>
      <PomNavbar />
      <div id="top" className="wrapper">
        <PomHeader image={require("assets/img/revato-10251-13112723-111323.jpg")} sloganBig="Find your rest" sloganLittle="in the paradise"/>
        <div className="main">
          <div className="container">
            <div className="row">

              <div className="col-md-9">
                <h1>Our Rooms</h1>
                <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Exercitationem quis explicabo eum quae molestias voluptatem repellendus distinctio cupiditate, dignissimos ab harum iste corrupti sequi rerum expedita nulla quo atque a odio optio. Iste voluptas tenetur nobis porro optio quam quod aliquam iure delectus, debitis ipsa quas neque et, dicta sed! Quos nesciunt magni repellendus reiciendis quidem perferendis amet et provident iure voluptatem labore cupiditate ducimus doloribus consequatur aperiam, eligendi natus excepturi odio aut. Sapiente nihil magni mollitia corrupti culpa ullam earum, dolores, sit porro, itaque inventore sint voluptatem veritatis quisquam maxime impedit commodi aliquid? Dolorem voluptate repellat, quod voluptatem iusto sequi voluptatibus id alias aliquid molestias! Accusamus cumque eveniet quo molestiae. Consequatur accusamus laboriosam perferendis distinctio ducimus minus, non vel maiores quod quas et a sunt sit facilis ad perspiciatis excepturi. Molestias veritatis fugit quisquam esse, suscipit quia sed rem necessitatibus molestiae possimus veniam voluptatibus dolorem eum officiis optio nesciunt!</p>
              </div>

              <div className="col-md-3">
                <h2>Find Your Room</h2>
                <p>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Quidem ratione molestiae itaque consequuntur id eius obcaecati? Ab ipsum, voluptatum consectetur natus consequuntur minima quia alias nostrum tempore, at quisquam ducimus perferendis quas voluptatibus provident possimus excepturi. Consequatur, cum doloribus, autem adipisci laboriosam optio distinctio numquam, a suscipit molestias labore nam est ducimus dolorem. Laudantium voluptatum ea aspernatur. Ipsa commodi cumque inventore quibusdam dicta repellat id iste velit architecto dolores? Pariatur rerum possimus asperiores laborum expedita. Porro quibusdam nobis sint architecto non voluptatum eius provident facere ipsa exercitationem consectetur in, consequuntur id ducimus molestiae, alias, aliquid nulla facilis nisi quidem error distinctio sunt odit. Pariatur rem velit inventore, eveniet odit voluptatibus provident deleniti quis, amet, incidunt aspernatur? Esse quod, odio adipisci quam earum dolores nihil illum quisquam deleniti? Ipsum ipsam, distinctio ad, sequi rerum sit blanditiis voluptatibus officiis placeat, labore nemo reiciendis consectetur dolore. Itaque voluptas perspiciatis dolore eius cumque temporibus!</p>
                <p>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Voluptatibus alias, ea expedita eveniet saepe velit deleniti quo doloremque neque cum eligendi odit earum iste! Assumenda, dolore fugit ratione cum dolor, odit unde, reprehenderit amet voluptas a corrupti tempore praesentium repellendus? Maiores eveniet harum totam explicabo velit voluptas officiis fugit sapiente reiciendis debitis esse nobis eligendi natus laudantium corrupti, illum, ipsa dignissimos eaque eos, saepe ratione qui. Similique, ratione tempora iure maxime nemo voluptate earum rem est reiciendis natus laudantium beatae dolor. Quas velit repudiandae, soluta tempora in perspiciatis illum. Laborum quia totam a atque, iste est doloribus sapiente earum quasi id, veniam dolore, dolores repudiandae. Distinctio pariatur fugit nemo, sequi facere placeat minus nobis. Odit saepe ad itaque provident ullam, expedita adipisci sunt est aliquam, laudantium exercitationem quod velit commodi tempore. Nulla veniam similique velit adipisci itaque eum. Labore ab, eius sunt quibusdam sit laboriosam? Exercitationem repellat necessitatibus veritatis beatae incidunt, sit qui vero inventore consectetur architecto praesentium enim nam voluptates ullam voluptas, quo aut aperiam ducimus, similique autem quae. Perferendis minus nesciunt optio tenetur nobis odit cupiditate necessitatibus fugiat nostrum aperiam error iste porro obcaecati, veritatis aut impedit. Quo illum cum, qui reiciendis autem et dolores hic quibusdam quaerat, velit enim harum iste consectetur totam sed! Est, eligendi? Modi ratione, asperiores quibusdam optio, accusamus deserunt autem velit cum architecto officia animi explicabo maxime odio vero placeat error veniam. A eius exercitationem eveniet ad molestias reprehenderit facere eum repellat ab, deserunt veniam nam, distinctio explicabo ducimus tempora libero asperiores autem officiis reiciendis? Fuga cum impedit itaque. Expedita, ea. Libero aut, doloremque vel, quam quod mollitia impedit ipsa nisi ipsam natus, eveniet quisquam molestias assumenda facere ab earum reiciendis ut quas dolores modi magnam voluptate in incidunt. Alias illum odit numquam soluta. Sit perferendis distinctio sunt pariatur, molestias quam ipsa aliquam! Facilis, inventore molestiae ab numquam ipsum rem omnis, atque odit expedita animi amet, ipsam labore sed voluptatibus! Ratione tempore ex corrupti rerum repellat exercitationem maxime officiis. Ducimus vero at, dolorum doloremque ea non! Quae ex laudantium deserunt. Facilis dignissimos delectus impedit veritatis error, labore inventore modi et voluptas rerum minima dolor voluptatum quibusdam possimus nisi id. Praesentium dolores dolorum culpa temporibus qui esse totam, excepturi explicabo dolorem reprehenderit atque! Nesciunt, quos dicta eum labore velit, ipsa dolores cumque officiis facere minus maxime reiciendis blanditiis quibusdam corporis debitis. Non ex laboriosam, dolore officiis provident, suscipit accusantium, velit eaque dolorem unde a.</p>
              </div>
            </div>
          </div>

        {/*pa arriba*/}
        <div className="text-center gototop active">
          <a href="#top">
            {userText}
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
