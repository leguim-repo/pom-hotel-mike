import React from 'react';
import { withRouter } from "react-router";
import { Loading } from '../components';
import { Container, Button, FormGroup, Input, Label, Form } from "reactstrap";
class Login extends React.Component {
  constructor(props) {
      super(props);

      this.state = {
          loading: true,
          username: '',
          password: '',
      }

      this.handleLogin = this.handleLogin.bind(this);

  }

  componentDidMount() {
      //fetches aqui


      this.setState({ loading: false});
  }

  handleLogin(event) {
    event.preventDefault();
    alert("Username: "+this.username.value+" Password: "+this.password.value);
  }

  render() {
    if (this.state.loading == false) {
      return(
        <Container>
          <Form onSubmit={this.handleLogin}>
            <FormGroup>
              <Label htmlFor="username">Username</Label>
              <Input type="text" id="username" name="username" innerRef={(input) => this.username = input}></Input>
            </FormGroup>
            <FormGroup>
              <Label htmlFor="password">Password</Label>
              <Input type="password" id="password" name="password" innerRef={(input) => this.password = input}></Input>
            </FormGroup>
            <Button type="submit" value="submit" color="primary">Login</Button>
          </Form>
        </Container>
      );
    }
    else {
      return(
        <div><Loading></Loading></div>
      );
    }

  }

}
export default withRouter(Login);
