import { combineReducers } from 'redux'
import { createForms } from 'react-redux-form';

import findRoomSimpleReducer from './findRoomSimpleReducer';
import initialFindRoomSimple  from './findRoomSimpleReducer';


const rootReducers = combineReducers({
  findRoomSimple: findRoomSimpleReducer,
  ...createForms({formFindRoomSimple: initialFindRoomSimple,// to store in redux our forms states
  
  })

})

export default rootReducers;