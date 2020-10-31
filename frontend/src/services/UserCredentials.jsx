import React, {useState} from "react";


export const AppContext = React.createContext();

export class AppProvider extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            username: 'demo',
            password: 'demo',
            logged: false,
        }
        this.setUsername  = this.setUsername.bind(this);
    }    
    
    setUsername(username) {
        console.log("setUsername: ",username);
    }

	render() {
		return(
			<AppContext.Provider value={{state: this.state, setU: this.setUsername}}>
				{this.props.children}
			</AppContext.Provider>
		);
	}
}



export const LanguageContext = React.createContext({
  language: "en",
  setLanguage: () => {}
})

export const LanguageContextProvider = (props) => {

  const setLanguage = (language) => {
    setState({...state, language: language})
  }

  const initState = {
    language: "en",
    setLanguage: setLanguage
  } 

  const [state, setState] = useState(initState)

  return (
    <LanguageContext.Provider value={state}>
      {props.children}
    </LanguageContext.Provider>
  )
}