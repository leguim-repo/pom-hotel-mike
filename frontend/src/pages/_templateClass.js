import React from 'react';
import { withRouter } from "react-router";
import { Loading } from '../components';

class MiClass extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            loading: true,
        }
    }

    componentDidMount() {
        //fetches aqui


        //this.setState({ loading: false});
    }

    render() {
        if (this.state.loading == false) {
            return(
                <div className="container">
                    <p>Loaded</p>
                </div>
            );
        }
        else {
            return(
                <div><Loading></Loading></div>
            );
        }

    }

}
export default withRouter(MiClass);
