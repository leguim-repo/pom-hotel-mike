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
import HomePage from "pages/HomePage/HomePage";
import RoomsPage from "pages/RoomsPage/RoomsPage"
import AboutPage from "pages/AboutPage/AboutPage";
import DetailAndBookPage from "pages/DetailAndBookPage/DetailAndBookPage";
import LoginPomPage from "pages/LoginPomPage/LoginPomPage";

import StorageManager from "components/StorageManager/StorageManager";

import { Provider } from "react-redux";
import { ConfigureStoreDev } from "./redux/configureStores";
import ThankYouPage from "pages/ThankYouPage/ThankYouPage";
import Error404 from "pages/Errors/404";

const store_dev = ConfigureStoreDev();



const env = 'development';
//const env = 'production';
console.warn=()=>{};
if (env === 'production') {
  console.log('Bye bye console')
  console.log = function () {};
}

StorageManager.setupApplication();

ReactDOM.render(
  <Provider store={store_dev}>
    <BrowserRouter>
      <Switch>
        <Switch>
          <Route exact path="/" render={(props) => <HomePage {...props} />} />
          <Route path="/rooms" render={(props) => <RoomsPage {...props} />} />
          <Route path="/roomdetail/:room?" render={(props) => <DetailAndBookPage {...props} />} />
          <Route path="/thankyou/:id?" render={(props) => <ThankYouPage {...props} />} />
          <Route path="/login" render={(props) => <LoginPomPage {...props} />} />
          <Route path="/about" render={(props) => <AboutPage {...props} />} />

          {/** redirect for 404 */}
          <Route component={Error404} />
        </Switch>
      </Switch>
    </BrowserRouter>
  </Provider>
  ,document.getElementById("root")
);
