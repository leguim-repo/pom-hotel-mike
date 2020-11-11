import React from 'react';
import { Card, CardImg, CardText, CardBody,CardTitle, CardSubtitle, Button } from 'reactstrap';

const EmptyRoom = () => {
  return (
      <React.Fragment>
        <Card>
          <CardImg top width="100%" src={require("assets/img/empty-room.jpg")} alt="Card image cap" />
          <CardBody>
            <CardTitle tag="h5">Unnamed Room</CardTitle>
              <CardSubtitle tag="h6" className="mb-2 text-muted">Price per Night undefined</CardSubtitle>
            <CardText>Sorry your room is under construction</CardText>
          </CardBody>
        </Card>
      </React.Fragment>
    );
}

export default EmptyRoom;

