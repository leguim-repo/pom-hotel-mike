import React, { useState } from "react";
import { Button, Form } from "react-bootstrap";
import axios from "axios";
//import "./Login.css";

export default function Login() {

  //hooks de estado
  const [username, setUsername] = useState("demo");
  const [password, setPassword] = useState("demo");

  function validateForm() {
    return username.length > 0 && password.length > 0;
  }

  function test1() {
    axios.get('http://localhost:8080/', {
          headers:{
              'Access-Control-Allow-Origin':'*',
              'Content-Type': 'application/json;charset=UTF-8',
          }
        })
        .then(res => {
          console.log(res);
          console.log(res.data);
        })
  }

  function test2() {
    axios.get('http://localhost:8080/api/', {
          headers:{
            'Access-Control-Allow-Origin':'*',
            'Content-Type': 'application/json;charset=UTF-8',
          }
        })
        .then(res => {
          console.log(res);
          console.log(res.data);
        })
  }

  //https://stackoverflow.com/questions/54268012/how-to-add-csrf-token-in-axios-post-request-in-react-and-spring-boot
  function test3() {
    axios.post('http://localhost:8080/api/',
      JSON.stringify({username: username, password: password}), {
        headers: { 
          xsrfCookieName: 'XSRF-TOKEN',
          xsrfHeaderName: 'X-XSRF-TOKEN',
          "X-XSRF-TOKEN": 'csrf',
         // Authorization: 'Bearer ' + csrf,
          "Content-Type": "application/x-www-form-urlencoded"
        }, 
        credentials: 'include',
        })
      .then(function (response) {
        console.log(response);
        
      }) 
      .catch(function (error) {
        console.log(error);
      });
      
  }

  function handleSubmit(event) {
    event.preventDefault();
    console.log(username,':',password)
    test2()


  }

  
  return (
    <div className="Login">
      <form onSubmit={handleSubmit}>
      <Form>
        <Form.Group controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control type="text" placeholder="Enter username" value="demo" onChange={e => setUsername(e.target.value)}/>
            <Form.Text className="text-muted">
            We'll never share your email with anyone else. para el register client
            </Form.Text>
        </Form.Group>

        <Form.Group controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password" value="demo" onChange={e => setPassword(e.target.value)} />
        </Form.Group>
        <Button block bsSize="large" disabled={!validateForm()} type="submit">Login</Button>
      </Form>
      </form>
    </div>
  );
}