import React, { useEffect, useCallback, useState} from "react";

function RoomDetails() {

  const [hookValue, setHookValue] = useState('Soy un hook');

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

export default RoomDetails;
