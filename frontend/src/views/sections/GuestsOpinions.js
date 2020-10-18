import React from 'react';
import { Media } from 'reactstrap';

// https://reactstrap.github.io/components/media/
// https://www.creative-tim.com/learning-lab/reactstrap/images/argon-design-system

const style = { margin: "10px", width: "14rem"};

function renderOpinion(image, name, country, opinion, position) {
  
  //TODO eficaz pero no eficiente
  if (position === 'left') {
    return (
      <Media >
          <Media left top href="#">
              <img alt={name} className=" img-fluid rounded-circle shadow" src={image} style={ style }></img>
          </Media>
          <Media body className="ml-5">
              <Media heading>{name}</Media>
              <p>{country}</p>
              <p>{opinion}</p>
          </Media>
      </Media>
    );
  }
  else {
    return (
      <Media style={{ margin: "10px", }}>
          <Media body className="ml-5">
              <Media heading>{name}</Media>
              <p>{country}</p>
              <p>{opinion}</p>
          </Media>
          <Media left top href="#">
            <img alt={name} className=" img-fluid rounded-circle shadow" src={image} style={ style }></img>
          </Media>
      </Media>
    );
  }
  

}

function GuestsOpinions(props) {
  console.log('GuestsOpinions:',props);
  return (
    <div className="container mt-5">
      <h2 className="text-center">Our Satisfied Guests says</h2>
      <p>We love to tell our successful far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
      {renderOpinion(require("assets/img/person1.jpg"), "Alysha Myers", "Miami Florida, USA", "A small river named Duden flows by their place and supplies it with the necessary regelialia","left")}
      {renderOpinion(require("assets/img/person2.jpg"), "Jacob Web", "Houston Texas, USA", "One day however a small line of blind text by the name of Lorem Ipsum decided to leave for the far World of Grammar.","right")}
      {renderOpinion(require("assets/img/person3.jpg"), "Li Mai", "New York, USA", "Alphabet Village and the subline of her own road, the Line Lane. Pityful a rethoric question ran over her cheek, then she continued her way.","left")}
    </div>
  );
}


export default GuestsOpinions;
