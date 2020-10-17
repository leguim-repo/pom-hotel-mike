import React from 'react';
import './App.css';
import { Home, Login } from './pages';
import { Header, Footer } from './components';
import { BrowserRouter, Switch, Route} from 'react-router-dom';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
        <Header></Header>
          <Switch>
            <Route path="/" component={Home} exact></Route>
            <Route path="/signin" component={Login} exact></Route>

          </Switch>
        <Footer></Footer>
      </BrowserRouter>
    </div>
  );
}

export default App;
