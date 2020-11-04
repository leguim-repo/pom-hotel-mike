import React, { useEffect, useCallback, useState} from "react";

function GotoTop() {
  //const [scrollPos, setScrollPos] = useState('');
  const [showGotoTop, setShowGotoTop] = useState(false);


  const handleOnScroll = useCallback(event => {
    //console.log("handleOnScroll.event: ",event);
    //setScrollPos(event.target.documentElement.scrollTop);
    if (event.target.documentElement.scrollTop >= 300) {
      setShowGotoTop(true);
    }
    else {
      setShowGotoTop(false);
    }
}, []);

  useEffect(() => {
    //console.log('useEffect: onMount')
    window.addEventListener("scroll", handleOnScroll);

    return function cleanup() {
      //console.log('useEffect: onDismount')
      window.removeEventListener('scroll', handleOnScroll);
    };
  }, [handleOnScroll]);

  //console.log('scrollpos:',scrollPos);
  return (
    <React.Fragment>
        <div className={`text-center gototop ${showGotoTop ? 'active' : ''}`}>
          <a href="#top">
            <i className="now-ui-icons arrows-1_minimal-up flecha"/>
            {/*scrollPos*/}
          </a>
        </div>
    </React.Fragment>
  );
}

export default GotoTop;
