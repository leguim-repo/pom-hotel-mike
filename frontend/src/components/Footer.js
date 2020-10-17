import React from 'react';

class Footer extends React.Component {
    constructor(props) {
        super(props)
    }

    render() {
        return(

        <footer className="footer">
            <p>Copyright chachi molon aqui</p>
            <i className="fa fa-phone fa-lg"></i>: +852 1234 5678<br />

        </footer>
        );
    }
}

export default Footer;