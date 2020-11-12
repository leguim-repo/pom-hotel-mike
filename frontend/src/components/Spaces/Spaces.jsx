import React from "react";
import{
  Card,
  CardImg,
  CardBody,
  CardTitle,
  CardText,
} from "reactstrap";

//TODO for better performance should be a functional component
class Spaces extends React.Component {
  constructor(props) {
    super(props);
  }
  render() {
    return (
      <Card style={{ margin: "2px",width: "18rem" }}>
        <CardImg
          alt="..."
          src={this.props.img}
          top >
        </CardImg>
        <CardTitle className="ml-2 mt-2">{this.props.title}</CardTitle>
        <CardBody>
          <CardText>{this.props.description}</CardText>
        </CardBody>
      </Card>
      
    );
  }
}
export default Spaces;

