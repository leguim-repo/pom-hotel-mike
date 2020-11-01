import React, { useState, useEffect} from "react";
import { getMusicLink } from "api/ApiServices";
import Loader from '../Loader/Loader';



const RenderLoader = () => {
  return(
      <div className="col-12 col-md6 col-lg-4">
      <h4 className="text-center">Play Music</h4>
      <p className="text-center">Listen the best dance sessions</p>
      <p className="text-center">
        <Loader></Loader>
      </p>
      <p className="text-center">Click on the icon and enjoy</p>
      </div>
    );
}
const RenderEasterEgg = (props) => {
  //console.log('RenderEasterEgg.props: ',props);
  return(
    <div className="col-12 col-md6 col-lg-4">
    <h4 className="text-center">Play Music</h4>
    <p className="text-center">Listen the best dance sessions</p>
    <p className="text-center">
      <a href={props.link} target="_blank">
        <img
          src={require("assets/img/musica.png")}
          style={{ height: "3.5em", width: "3.5em" }}
          ></img>
      </a>
    </p>
    <p className="text-center">Click on the icon and enjoy</p>
  </div>
  );
}

const EasterEgg = (props) => {
  const [hasError, setHasError] = useState(false)
  const [loading, setLoading] = useState(true)
  const [link, setLink] = useState("#");

  //console.log('EasterEgg.props: ',props);
  
  useEffect( async () => {
    const songLink = await getMusicLink();
    setLink(songLink);
    setLoading(false);
    console.log('link: ',link);
    
  }, []); //Note: If you want the useEffect to behave like the componentDidMount lifecycle event, pass an array as the second argument


  return(
    <React.Fragment>
      { loading ? <RenderLoader></RenderLoader> : hasError ? <RenderLoader></RenderLoader> : <RenderEasterEgg link={link}/>}
    </React.Fragment>
  );
  
}

export default EasterEgg;




// https://medium.com/better-programming/how-to-fetch-data-from-an-api-with-react-hooks-9e7202b8afcd