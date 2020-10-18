import rootReducers from './reducers';
import {createStore, applyMiddleware} from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';


import thunk from 'redux-thunk';
import logger from 'redux-logger';


export const ConfigureStoreDev = () => {
    const store_dev = createStore(
        rootReducers,
        composeWithDevTools(applyMiddleware(thunk)),
        //window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
      );
    return store_dev;
}



export const ConfigureStorePro = () => {
    const store = createStore(
        rootReducers,
        applyMiddleware(thunk),
    );

    return store;
}