import * as actions from '../ActionTypes';

// valores iniciales del form
export const initialFindRoomSimple = {
    loading: false,
    hasErrors: false,
    error: "",
    checkin: "",
    checkout: "",
    guests: 2,
};


//reducer
export default function findRoomSimpleReducer(state = initialFindRoomSimple, action) {
    switch (action.type) {
      case actions.FIND_ROOM_SIMPLE_PENDING:
        return { ...state, loading: true }
      case actions.FIND_ROOM_SIMPLE_SUCCES:
        return {  comments: action.payload, loading: false, hasErrors: false, error: ""  }
      case actions.FIND_ROOM_SIMPLE_FAILURE:
          return {
              comments: [],
              isLoading: false, 
              hasErrors: true, 
              error: action.payload.error.message
          }
      default:
        return state
    }
  }