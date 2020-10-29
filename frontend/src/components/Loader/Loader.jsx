import React, { useEffect, useCallback, useState} from "react";
import { Container } from 'reactstrap';

// todo no es eficiente
function Loader() {

  useEffect(() => {
    console.log('Loader: onMount')
  }, []);

  return (
    <React.Fragment>
      <i className="now-ui-icons loader_gear spin"  style={{fontSize: '4em'}}></i>
    </React.Fragment>
  );
}

export default Loader;
