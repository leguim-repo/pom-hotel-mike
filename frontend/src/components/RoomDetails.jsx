import React, { useEffect, useCallback, useState} from "react";

function RoomDetails(props) {

  const [hookValue, setHookValue] = useState('Soy un hook');
  console.log('RoomDetails.props: ',props);
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
