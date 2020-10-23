import React, { useState, useEffect} from "react";

// https://youtu.be/mL7Gb6rcAAo

const RenderEasterEgg = (props) => {
  console.log('RenderEasterEgg.props: ',props);
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
  const [data, setData] = useState("");
  
  console.log('EasterEgg.props: ',props);
  
  useEffect(() => {

    var requestOptions = {
      method: 'GET',
      redirect: 'follow'
    };
    async function fetchData() {
      //const response = await fetch("https://swapi.dev/api/people/1/");
      const response = await fetch("http://localhost:8080/api/music", requestOptions);

      response
        .json()
        .then(response => {
                      setData(response); 
                      setLoading(false); 
                      setLink(response.link)})
        .catch(err => { setHasError(err); console.log("e: ",err,"\nr: ",response)});
    }
    fetchData();
  }, []); //Note: If you want the useEffect to behave like the componentDidMount lifecycle event, pass an array as the second argument
  
  return(
    <React.Fragment>
      { loading ? <div>Loading...</div> : hasError ? <div>Error loading song...</div> : <RenderEasterEgg link={link} data={data}/>}
    </React.Fragment>
  );
  
}

export default EasterEgg;




// https://medium.com/better-programming/how-to-fetch-data-from-an-api-with-react-hooks-9e7202b8afcd