import React from "react";
import ReactDOM from "react-dom";
import { BrowserRouter, Route, Switch, Redirect } from "react-router-dom";

// styles for this kit
import "assets/css/bootstrap.min.css";
import "assets/scss/now-ui-kit.scss?v=1.4.0";
import "assets/demo/demo.css?v=1.4.0";
import "assets/demo/nucleo-icons-page-styles.css?v=1.4.0";
import './App.css';

// pages for this project
import HomePage from "pages/HomePage";
import RoomsPage from "pages/RoomsPage"
import BookNowPage from "pages/BookNowPage";
import BookNowPageOLD from "pages/BookNowPageOLD";
import LoginPomPage from "pages/LoginPomPage";

import NucleoIcons from "pages/examples/NucleoIcons";
import LandingPage from "pages/examples/LandingPage";
import ProfilePage from "pages/examples/ProfilePage";

import { Provider } from "react-redux";
import { ConfigureStoreDev } from "./redux/configureStores";

const store_dev = ConfigureStoreDev();

const env = 'development';
//const env = 'production';
console.warn=()=>{};
if (env === 'production') {
  console.log('Bye bye console')
  console.log = function () {};
}

ReactDOM.render(
  <Provider store={store_dev}>
    <BrowserRouter>
      <Switch>
        <Switch>
          <Route exact path="/" render={(props) => <HomePage {...props} />} />
          <Route exact path="/rooms" render={(props) => <RoomsPage {...props} />} />
          <Route path="/rooms/:room?" render={(props) => <RoomsPage {...props} />} />
          <Route path="/booknow/:room?" render={(props) => <BookNowPage {...props} />} />
          <Route path="/booknowold/:room?" render={(props) => <BookNowPageOLD {...props} />} />
          <Route
            path="/nucleo-icons"
            render={(props) => <NucleoIcons {...props} />}
          />
          <Route
            path="/landing-page"
            render={(props) => <LandingPage {...props} />}
          />
          <Route
            path="/profile-page"
            render={(props) => <ProfilePage {...props} />}
          />
          <Route
            path="/login"
            render={(props) => <LoginPomPage {...props} />}
          />
          {/** redirect for 404 */}
          <Redirect to="/" />
          <Redirect from="/" to="/" />
        </Switch>
      </Switch>
    </BrowserRouter>
  </Provider>
  ,document.getElementById("root")
);
