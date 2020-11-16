import React from 'react';
import "./404.css";
const Error404 = () => {
    return(
        <div id="oopss">
            <div id="error-text">
                <img src={require("assets/img/sad404.svg")} alt="404"></img>
                <span>Error 404</span>
                <p className="p-a">Sorry</p>
                <p className="p-b">The page requested not found</p>
                <a href="/" class="back">Go Home</a>
            </div>
        </div>
    );
}

export default Error404;