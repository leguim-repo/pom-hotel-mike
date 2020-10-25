import React, { useEffect, useCallback, useState} from "react";

function FunctionalComponent() {

  const [hookValue, setHookValue] = useState('Soy un hook');
//https://stackoverflow.com/questions/60516300/how-to-use-in-reactjs-functional-component-history-push
  useEffect(() => {
    console.log('useEffect: onMount')
    setHookValue('Que pasa peña')
    return function cleanup() {
      console.log('useEffect: onDismount')

    };
  }, []);

  return (
    <React.Fragment>
      <p>Functional Component!!</p>
      <p>{hookValue}</p>
    </React.Fragment>
  );
}

export default FunctionalComponent;
